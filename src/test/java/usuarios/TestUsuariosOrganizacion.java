package usuarios;

import dominio.mediosTransportes.*;
import dominio.mediosTransportes.TransportePublico;
import dominio.ubicacion.Direccion;
import dominio.ubicacion.Localidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import infraestructura.repositorios.interfaces.IRepositorioInvitacionTramos;
import dominio.organizacion.Miembro;
import dominio.organizacion.Organizacion;
import aplicacion.servicios.ServicioTramos;
import dominio.usuarios.Usuario;
import dominio.organizacion.TipoOrg;
import dominio.usuarios.Persona;
import dominio.usuarios.RolesUsuario;
import dominio.usuarios.TipoDocumento;
import dominio.organizacion.Sector;
import dominio.organizacion.Clasificacion;
import dominio.viajes.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestUsuariosOrganizacion {

    private final Direccion direccion = inicializarDireccion();
    private final Organizacion organizacion = inicializarOrganizacion();

    @DisplayName("Test Organizacion - Modificar Direccion De la Org ")
    @Test
    void modificarDireccionOrg() {
        organizacion.setDireccion(direccion);
        Assertions.assertEquals(direccion, organizacion.getDireccion());
    }

    @DisplayName("Test Organizacion - Modificar Direccion De la Org ")
    @Test
    void modificarUsuarioOrg() {
        Usuario usuarioOrganizacion = inicializarUsuarioOrganizacion();
        organizacion.setUsuario(usuarioOrganizacion);
        Assertions.assertEquals(usuarioOrganizacion, organizacion.getUsuario());
    }

    @DisplayName("Test Organizacion - Agrega Sectores en la Org ")
    @Test
    void agregarSectorOrg() {
        Sector sector = new Sector("Legales");
        organizacion.addSectores(sector);
        Assertions.assertEquals(2, organizacion.getSectores().size());
    }

    @DisplayName("Test Organizacion - Elimina Sector en la Org")
    @Test
    void eliminaSectorOrg() {
        System.out.println("Cantidad de Sectores: " + organizacion.getSectores().size());
        Sector sector = organizacion.getSectores().get(0);
        organizacion.removeSectores(sector);
        Assertions.assertEquals(0, organizacion.getSectores().size());
    }

    @DisplayName("Test Organizacion - Agrega Miembros en la Org ")
    @Test
    void agregarMiembroOrg() {
        Miembro miembroNuevo = new Miembro(null, null, null, false);
        organizacion.addMiembro(miembroNuevo);
        Assertions.assertEquals(3, organizacion.getMiembros().size());
    }

    @DisplayName("Test Organizacion - Se puede compartir un tramo en un vehiculo particular ")
    @Test
    void compartirTramoOrg() {
        ServicioTramos servicioTramos = new ServicioTramos(mock(IRepositorioInvitacionTramos.class));

        Miembro miembro1 = organizacion.getMiembros().get(0);
        Miembro miembro2 = organizacion.getMiembros().get(1);
        Trayecto trayecto = new Trayecto(null, null);
        Trayecto otroTrayecto = new Trayecto(null, null);
        miembro1.setTrayecto(trayecto);
        miembro2.setTrayecto(otroTrayecto);

        MedioTransporte medioTransporteParaCompartir = new VehiculoParticular(null, TipoVehiculo.AUTO, null);
        MedioTransporte medioTransporteParaNoComparir = new TransportePublico(null, null , null, null);

        Tramo tramoCompartido = new Tramo(this.direccion, this.direccion, "", medioTransporteParaCompartir);
        Tramo tramoNoCompartido = new Tramo(this.direccion, this.direccion, "", medioTransporteParaNoComparir);

        miembro1.getTrayecto().addTramo(tramoCompartido);
        miembro1.getTrayecto().addTramo(tramoNoCompartido);

        InvitacionTramo invitacion = servicioTramos.compartirTramoEntre(miembro1, miembro2, tramoCompartido);
        servicioTramos.aceptarInvitacionTramo(invitacion);

        Assertions.assertTrue(miembro1.getTrayecto().getTramo().stream().anyMatch(t -> miembro2.getTrayecto().getTramo().contains(t)));
    }

    @DisplayName("Test Organizacion - Elimina Miembro en la Org")
    @Test
    void eliminaMiembroOrg() {
        System.out.println("Cantidad de Miembros: " + organizacion.getMiembros().size());
        Miembro miembro = organizacion.getMiembros().get(0);
        organizacion.removeMiembro(miembro);
        Assertions.assertEquals(1, organizacion.getMiembros().size());
    }

    @DisplayName("Test Organizacion - Modificar Razon social De la Org ")
    @Test
    void modificarRazonSocialOrg() {
        organizacion.setRazonSocial("Aero S.R.L.");
        Assertions.assertEquals("Aero S.R.L.", organizacion.getRazonSocial());
    }

    @DisplayName("Test Organizacion - Modificar Clasificacion De la Org ")
    @Test
    void modificarClasificacionOrg() {
        Clasificacion clasificacion = new Clasificacion("Empresa");
        organizacion.setClasificacion(clasificacion);
        Assertions.assertEquals(clasificacion, organizacion.getClasificacion());
    }

    @DisplayName("Test Organizacion - Modificar Tipo Org ")
    @Test
    void modificarTipoOrg() {
        organizacion.setTipoOrg(TipoOrg.EMPRESA);
        Assertions.assertEquals(TipoOrg.EMPRESA, organizacion.getTipoOrg());
    }

    @DisplayName("Test Organizacion - Aprobar Organizcion ")
    @Test
    void AprobarOrg() {
        organizacion.setAprobado(true);
        Assertions.assertEquals(true, organizacion.isAprobado());
    }

    @DisplayName("Test Persona - Modificar Nro doc de Persona")
    @Test
    void modificarNroDocPersona() {
        Persona unaPersona = inicializarPersona();
        unaPersona.setNroDocumento("23456789");
        Assertions.assertEquals("23456789", unaPersona.getNroDocumento());
    }

    /* Métodos para inicialización de datos */

    private Organizacion inicializarOrganizacion() {
        Organizacion org = new Organizacion(inicializarUsuarioOrganizacion(), "Aero S.A.", "aero@gmail.com", direccion, null, TipoOrg.EMPRESA);
        org.addMiembro(inicializarMiembros().get(0));
        org.addMiembro(inicializarMiembros().get(1));
        org.addSectores(new Sector("Investigación"));
        return org;
    }

    private Direccion inicializarDireccion() {
        return new Direccion("Av Corrientes", "220", new Localidad(1, ""));
    }

    private Usuario inicializarUsuarioOrganizacion() {
        return new Usuario("AERO", "AERO", RolesUsuario.ADMINISTRADOR_ORGANIZACION);
    }

    private Persona inicializarPersona() {
        Usuario usuario = new Usuario("Miembro1", null, RolesUsuario.MIEMBRO);
        return new Persona("Juan", "23960589", "Perez", usuario, "aero@gmail.com", TipoDocumento.DNI);
    }

    private Persona inicializarOtraPersona() {
        Usuario usuario = new Usuario("Miembro2", null, RolesUsuario.MIEMBRO);
        return new Persona("Ana", "22960500", "Juarez", usuario, "anajuarez@gmail.com", TipoDocumento.DNI );
    }

    private List<Miembro> inicializarMiembros() {
        inicializarOtraPersona();
        return Arrays.asList(new Miembro(inicializarPersona(), this.organizacion, null, false),
            new Miembro(inicializarOtraPersona(), this.organizacion, null, false));
    }
}
