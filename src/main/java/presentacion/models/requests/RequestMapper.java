package presentacion.models.requests;

import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import dominio.factoresEmision.Factory.FactoryFE;
import dominio.mediosTransportes.*;
import dominio.organizacion.Clasificacion;
import dominio.organizacion.Miembro;
import dominio.organizacion.Organizacion;
import dominio.organizacion.Sector;
import dominio.usuarios.Persona;
import dominio.usuarios.TipoDocumento;
import dominio.usuarios.Usuario;
import dominio.viajes.Tramo;
import dominio.viajes.Trayecto;
import infraestructura.repositorios.*;
import presentacion.models.requests.PersonaModel;
import presentacion.models.responses.ResponseMapper;
import presentacion.security.UserClaims;

import java.util.List;
import java.util.stream.Collectors;

public class RequestMapper {

  private ResponseMapper responseMapper = new ResponseMapper();
  private RepositorioCombustible repositorioCombustible = new RepositorioCombustible(Combustible.class);
  private RepositorioParadas repositorioParadas = new RepositorioParadas(Parada.class);
  private RepositorioLineas repositorioLineas = new RepositorioLineas(Linea.class);
  private RepositorioFactorEmision repositorioFactorEmision = new RepositorioFactorEmision(FactorEmision.class);
  private RepositorioServicioContratado repositorioServicioContratado = new RepositorioServicioContratado(TipoServContratado.class);
  private RepositorioClasificaciones repositorioClasificaciones = new RepositorioClasificaciones(Clasificacion.class);
  private RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios(Usuario.class);
  private RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones(Organizacion.class);

  public Trayecto toDomain(CrearTrayectoModel crearTrayectoModel) {
    List<Tramo> tramos = crearTrayectoModel.getTramo().stream().map(t -> toDomain(t)).collect(Collectors.toList());
    return new Trayecto(crearTrayectoModel.getNombre(), crearTrayectoModel.getDireccioInicio(), crearTrayectoModel.getDireccionFin(), tramos);
  }

  public MedioTransporte toDomain(CrearMedioTransporteModel medioTransporteModel) {
    switch (medioTransporteModel.getTipo()) {
      case TRANSPORTE_PUBLICO: {
        Linea linea = repositorioLineas.buscar(medioTransporteModel.getLinea().getId());
        Parada inicio = repositorioParadas.buscar(medioTransporteModel.getParadaInicio().getId());
        Parada fin = repositorioParadas.buscar(medioTransporteModel.getParadaFin().getId());
        FactorEmision fe = FactoryFE.getFe(linea.getCombustible(), null, null, medioTransporteModel.getLinea().getTipoTransportePublico(), repositorioFactorEmision.buscarTodos());
        return new TransportePublico(linea, inicio, fin, fe);
      }
      case VEHICULO_PARTICULAR: {
        Combustible combustible = repositorioCombustible.buscar(medioTransporteModel.getCombustible().getId());
        FactorEmision fe = FactoryFE.getFe(combustible, null, medioTransporteModel.getTipoVehiculo(), null, repositorioFactorEmision.buscarTodos());
        return new VehiculoParticular(combustible, medioTransporteModel.getTipoVehiculo(), fe);
      }
      case SERVICIO_CONTRATADO:
        TipoServContratado tipoServContratado = repositorioServicioContratado.buscar(medioTransporteModel.getTipoServicioContratado().getId());
        FactorEmision fe = FactoryFE.getFe(tipoServContratado.getCombustible(), null, tipoServContratado.getTipoVehiculo(), null, repositorioFactorEmision.buscarTodos());
        return new ServicioContratado(tipoServContratado, null);
      case MEDIOS_PROPIOS: {
        return new MediosPropios(medioTransporteModel.getDetalle());
      }
      default:
        return null;
    }
  }

  public Tramo toDomain(CrearTramoModel tramoModel) {
    return new Tramo(tramoModel.getInicio(),
        tramoModel.getFin(),
        tramoModel.getNombre(),
        toDomain(tramoModel.getMedioDeTransporte())
    );
  }

  public FactorEmision toDomain(CrearFactorEmisionModel crearFactorEmisionModel) {
    Combustible combustible = this.repositorioCombustible.buscar(crearFactorEmisionModel.getCombustibleId());
    return new FactorEmision(crearFactorEmisionModel.getNombre(),
        crearFactorEmisionModel.getTipoActividad(),
        combustible,
        crearFactorEmisionModel.getValor(),
        crearFactorEmisionModel.getTipoFactorEmision(),
        crearFactorEmisionModel.getTipoVehiculo(),
        crearFactorEmisionModel.getTipoTransportePublico());
  }

  public Organizacion toDomain(CrearOrganizacionModel model, UserClaims userClaims) {
    Clasificacion clasificacion = this.repositorioClasificaciones.buscar(model.getClasificacion());
    Usuario usuario = this.repositorioUsuarios.buscar(userClaims.getUsuarioId());
    Organizacion organizacion = new Organizacion(usuario, model.getRazonSocial(), model.getEmail(), model.getDireccion(), clasificacion, model.getTipoOrganizacion());
    List<Sector> sectores = model.getSectores().stream().map(nombreSector -> new Sector(nombreSector, organizacion)).collect(Collectors.toList());
    organizacion.setCelular(model.getNumeroDeTelefono());
    organizacion.setSectores(sectores);
    organizacion.setNombre(model.getNombre());
    return organizacion;
  }

  public Miembro toDomain(CrearMiembroModel model, UserClaims userClaims) {
    Persona persona = toDomain(model.getPersona(), userClaims);
    Organizacion organizacion = this.repositorioOrganizaciones.buscar(model.getOrganizacionId());
    Sector sector = organizacion.getSectores().stream().filter(s -> s.getId().equals(model.getSectorId())).findFirst().orElse(null);
    return new Miembro(persona, organizacion, sector, false);
  }

  public Persona toDomain(PersonaModel model, UserClaims userClaims) {
    Usuario usuario = this.repositorioUsuarios.buscar(userClaims.getUsuarioId());
    return new Persona(model.getNombre(), model.getNroDocumento(), model.getApellido(), usuario, model.getMail(), model.getTipoDoc());
  }

}
