package huellaDeCarbono;

import dominio.actividades.enums.TipoActividad;
import dominio.ubicacion.Localidad;
import dominio.usuarios.RolesUsuario;
import dominio.usuarios.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import infraestructura.repositorios.interfaces.IRepositorioOrganizaciones;
import aplicacion.servicios.calculadoraHc.CalculadoraHc;
import dominio.usuarios.AgenteSectorial;
import dominio.organizacion.Organizacion;
import dominio.ubicacion.Direccion;
import dominio.ubicacion.Municipio;
import dominio.ubicacion.Provincia;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CalculoHuellaTerritorio extends BaseTestHuella {

    @BeforeEach
    public void init() {
        diesel.setId(1);
        nafta.setId(2);
        electricidad.setId(3);
        gnc.setId(4);
        when(mockRepoFactorEmision.getFeByCombustibleAndActividad(diesel, TipoActividad.COMBUSTION_FIJA))
            .thenReturn(feDieselCombustionFija);
        when(mockRepoFactorEmision.getFeByCombustibleAndActividad(nafta, TipoActividad.COMBUSTION_MOVIL))
            .thenReturn(feNaftaCombustionMovil);
        when(mockRepoFactorEmision.getFeByCombustibleAndActividad(diesel, TipoActividad.COMBUSTION_MOVIL))
            .thenReturn(feDieselCombustionMovil);
        when(mockRepoFactorEmision.getFeByCombustibleAndActividad(electricidad, TipoActividad.ELECTRICA_ADQUIRIDA_CONSUMIDA))
            .thenReturn(feElectricidad);
        when(mockRepoFactorEmision.getFeByActividad(TipoActividad.LOGISTICA_PRODUCTOS_RESIDUOS))
            .thenReturn(feLogistica);
    }

    @DisplayName("Se obtiene el cálculo de la huella de carbono para un municipio")
    @Test
    public void seObtieneElCalculoDeHCParaUnMunicipio(){
        IRepositorioOrganizaciones mockRepo = mock(IRepositorioOrganizaciones.class);
        // Con este municipioId trae todas las organizaciones menos la que tiene actividad logísitca (la 5ta org)
        // Pero el cálculo de huelal territorial calcula la huella actual, y eso sólo contempla las
        // actividades ANUALES. Se descartan logística y electricidad que son mensuales
        Usuario usuario = new Usuario("Miembro1", null, RolesUsuario.AGENTE_SECTORIAL);
        AgenteSectorial agenteSectorial = new AgenteSectorial(200, 1, "Agente Sector 1 - Conurbano",usuario);
        List<Organizacion> organizacionesMock = generarOrganizacionesMunicipio().stream()
            .filter(o -> o.getDireccion().getMunicipio().getId() == 200).collect(Collectors.toList());
        when(mockRepo.getOrganizacionesPorMunicipio(any())).thenReturn(organizacionesMock);

        CalculadoraHc calculadoraHc = new CalculadoraHc(mockRepo, null);

        assertEquals(875, calculadoraHc.hCTerritorial(agenteSectorial));
    }

    @DisplayName("Se obtiene el cálculo de la huella de carbono para una provincia")
    @Test
    public void seObtieneElCalculoDeHCParaUnaProvincia(){
        IRepositorioOrganizaciones mockRepo = mock(IRepositorioOrganizaciones.class);
        //Con este provinciaId trae sólo la organizació que tiene actividad combustionFijaDiesel (la 1era org)
        Usuario usuario = new Usuario("Miembro1", null, RolesUsuario.AGENTE_SECTORIAL);
        AgenteSectorial agenteSectorial = new AgenteSectorial(200, 1, "Agente Sector 1 - Conurbano", usuario);
        List<Organizacion> organizacionesMock = generarOrganizacionesProvincia();
        when(mockRepo.getOrganizacionesPorProvincia(1)).thenReturn(organizacionesMock);

        CalculadoraHc calculadoraHc = new CalculadoraHc(mockRepo, null);

        assertEquals(250.00, calculadoraHc.hCTerritorial(agenteSectorial));
    }

    @DisplayName("Se obtiene el cálculo de la huella de carbono para una provincia que no está en el listado")
    @Test
    public void seObtieneElCalculoDeHCParaUnaProvinciaQueNoEsta(){
        IRepositorioOrganizaciones mockRepo = mock(IRepositorioOrganizaciones.class);

        Usuario usuario = new Usuario("Miembro1", null, RolesUsuario.AGENTE_SECTORIAL);
        AgenteSectorial agenteSectorial = new AgenteSectorial(200, 7, "Agente Sector 1 - Conurbano", usuario);
        List<Organizacion> organizacionesMock = generarOrganizacionesProvincia();
        when(mockRepo.getOrganizacionesPorProvincia(7)).thenReturn(organizacionesMock);

        CalculadoraHc calculadoraHc = new CalculadoraHc(mockRepo, null);

        assertEquals(0.0, calculadoraHc.hCTerritorial(agenteSectorial));
    }
    
    private List<Organizacion> generarOrganizacionesMunicipio() {
        return agregarActividades(Arrays.asList(
                new Organizacion(null, null, null, new Direccion("AV RIVADAVIA", "1500", new Localidad(1, ""), new Municipio(200, ""), new Provincia(1, "") ), null, null),
                new Organizacion(null, null, null, new Direccion("AV EVA PERON", "1230", new Localidad(2, ""), new Municipio(200, ""), new Provincia(2, "")), null, null),
                new Organizacion(null, null, null, new Direccion("AV RIVADAVIA", "2000", new Localidad(1, ""), new Municipio(200, ""), new Provincia(1, "")), null, null),
                new Organizacion(null, null,null , new Direccion("MILTON", "15", new Localidad(3, ""), new Municipio(200, ""), new Provincia(1, "")), null, null),
                new Organizacion(null, null,null , new Direccion("BONIFACIO", "1520", new Localidad(2, ""), new Municipio(201, ""), new Provincia(1, "")), null, null)
        ));
    }

    private List<Organizacion> generarOrganizacionesProvincia() {
        return agregarActividades(Arrays.asList(
                new Organizacion(null, null, null, new Direccion("AV RIVADAVIA", "1500", new Localidad(1, ""), new Municipio(200, ""), new Provincia(1, "") ), null, null),
                new Organizacion(null, null, null, new Direccion("AV EVA PERON", "1230", new Localidad(2, ""), new Municipio(201, ""), new Provincia(2, "")), null, null),
                new Organizacion(null, null, null, new Direccion("AV RIVADAVIA", "2000", new Localidad(1, ""), new Municipio(202, ""), new Provincia(13, "")), null, null),
                new Organizacion(null, null, null, new Direccion("MILTON", "15", new Localidad(3, ""), new Municipio(203, ""), new Provincia(4, "")), null, null),
                new Organizacion(null, null, null, new Direccion("BONIFACIO", "1520", new Localidad(2, ""), new Municipio(204, ""), new Provincia(5, "")), null, null)
        ));
    }

    private List<Organizacion> agregarActividades(List<Organizacion> organizaciones) {
        organizaciones.get(0).agregarActividad(Arrays.asList(this.combustionFijaDiesel));
        organizaciones.get(1).agregarActividad(Arrays.asList(this.combustionMovilDiesel));
        organizaciones.get(2).agregarActividad(Arrays.asList(this.combustionMovilNafta));
        organizaciones.get(3).agregarActividad(Arrays.asList(this.electricidadAdqCons));
        organizaciones.get(4).agregarActividad(Arrays.asList(this.logistica));

        return organizaciones;
    }
}


