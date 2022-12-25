package aplicacion.servicios.calculadoraHc;

import dominio.actividades.Actividad;
import dominio.actividades.enums.Periodicidad;
import dominio.organizacion.Sector;
import infraestructura.APICalculoDistancia.adapter.AdapterDistancia;
import infraestructura.repositorios.interfaces.IRepositorioOrganizaciones;
import dominio.usuarios.AgenteSectorial;
import dominio.organizacion.Miembro;
import dominio.organizacion.Organizacion;
import dominio.viajes.Tramo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CalculadoraHc {
  private IRepositorioOrganizaciones repositorioOrganizaciones;
  private AdapterDistancia adapter;

  public CalculadoraHc(IRepositorioOrganizaciones repositorioOrganizaciones, AdapterDistancia adapter) {
    this.repositorioOrganizaciones = repositorioOrganizaciones;
    this.adapter = adapter;
  }

  public Double hcOrganizacionEnPeriodo(Organizacion organizacion, LocalDate periodo, Periodicidad periodicidad) {
    List<Actividad> actividades;
    if (periodicidad == Periodicidad.ANUAL) {
      actividades = organizacion.getActividades().stream().
          filter(a -> a.getPeriodo().getYear() == periodo.getYear()
              && a.getPeriodicidad() == periodicidad).collect(Collectors.toList());
    } else {
      actividades = organizacion.getActividades().stream().
          filter(a -> a.getPeriodo().getYear() == periodo.getYear()
              && a.getPeriodo().getMonth() == periodo.getMonth()
              && a.getPeriodicidad() == periodicidad).collect(Collectors.toList());
    }

    Double hcDeActividades = actividades.stream().mapToDouble(Actividad::huellaDeCarbono).sum();

    return hcDeActividades + hcTramosOrganizacion(organizacion);
  }

  public Double hcOrganizacionActual(Organizacion organizacion) {
    return hcOrganizacionEnPeriodo(organizacion, LocalDate.now(), Periodicidad.ANUAL);
  }

  public ResultadosHcMiembro hcUnMiembroOrganizacion(Miembro miembro, Organizacion organizacion) {
    Double hcMiembro = hcMiembro(miembro);
   // Double hcOrganizacion = hcOrganizacionActual(organizacion);
    Double hcOrganizacion = 0.0;
    return new ResultadosHcMiembro(miembro, hcMiembro, hcOrganizacion);
  }

  public List<ResultadosHcMiembro> hcTodosLosMiembrosOrganizacion(Organizacion organizacion) {
    Double hcOrganizacion = hcOrganizacionActual(organizacion);
    List<Miembro> miembrosOrganizacion = organizacion.getMiembros();
    return miembrosOrganizacion.stream().map(m ->
        new ResultadosHcMiembro(m, hcMiembro(m), hcOrganizacion)).collect(Collectors.toList());
  }

  public Double hCTerritorial(AgenteSectorial agenteSectorial){
    return hCPorMunicipio(agenteSectorial) + hCPorProvincia(agenteSectorial) ;
  }

  public List<ResultadosHcSector> hcPorSector(Organizacion organizacion) {
    return organizacion.getSectores().stream().map(s ->
        new ResultadosHcSector(s, hcSector(s, organizacion), organizacion.getMiembrosDeSector(s).size())).collect(Collectors.toList());
  }

  private Double hCPorMunicipio(AgenteSectorial agenteSectorial) {
    List<Organizacion> organizanizacionesPorMunicipio =
        this.repositorioOrganizaciones.getOrganizacionesPorMunicipio(agenteSectorial.getIdMunicipio());

    return organizanizacionesPorMunicipio.stream().mapToDouble(org->hcOrganizacionActual(org)).sum();
  }

  private Double hCPorProvincia(AgenteSectorial agenteSectorial){
    List<Organizacion> organizanizacionesPorProvincia =
        this.repositorioOrganizaciones.getOrganizacionesPorProvincia(agenteSectorial.getIdProvincia()).
            stream().filter(organizacion ->
            organizacion.getDireccion().getProvincia().getId().equals(agenteSectorial.getIdProvincia())).collect(Collectors.toList());

    return organizanizacionesPorProvincia.stream().mapToDouble(org->hcOrganizacionActual(org)).sum();
  }
  private Double hcTramosOrganizacion(Organizacion organizacion) {
    List<Tramo> tramosNoCompartidos = organizacion.getMiembros().stream().flatMap(m -> m.getTrayecto().getTramo().stream()
                                      .filter(t -> !t.isEsCompartido())).collect(Collectors.toList());
    List<Tramo> tramosCompartidos   = organizacion.getMiembros().stream().flatMap(m -> m.getTrayecto().getTramo().stream()
                                      .filter(t -> t.isEsCompartido())).collect(Collectors.toList());

    Double hcDeTramosCompartidos = tramosCompartidos.stream().distinct().mapToDouble(t -> t.calcularHC(adapter)).sum();
    Double hcDeTramosNoCompartidos = tramosNoCompartidos.stream().mapToDouble(t -> t.calcularHC(adapter)).sum();

    return hcDeTramosNoCompartidos + hcDeTramosCompartidos;
  }

  private Double hcMiembro(Miembro miembro) {
    return miembro.getTrayecto().getTramo().stream().mapToDouble(t -> t.calcularHC(adapter)).sum();
  }

  private Double hcSector(Sector sector, Organizacion organizacion) {
    return organizacion.getMiembrosDeSector(sector).stream().mapToDouble(m -> hcMiembro(m)).sum();
  }
}


