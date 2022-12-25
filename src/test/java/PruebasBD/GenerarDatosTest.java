//package PruebasBD;
//
//import dominio.factoresEmision.Combustible;
//import dominio.mediosTransportes.*;
//import dominio.organizacion.*;
//import dominio.ubicacion.Direccion;
//import dominio.ubicacion.Localidad;
//import dominio.ubicacion.Municipio;
//import dominio.ubicacion.Provincia;
//import dominio.usuarios.Persona;
//import dominio.usuarios.RolesUsuario;
//import dominio.usuarios.TipoDocumento;
//import dominio.usuarios.Usuario;
//import infraestructura.repositorios.RepositorioCombustible;
//import infraestructura.repositorios.RepositorioLineas;
//import infraestructura.repositorios.RepositorioMiembros;
//import infraestructura.repositorios.RepositorioServicioContratado;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class GenerarDatosTest {
//  private RepositorioMiembros repositorioMiembros = new RepositorioMiembros(Miembro.class);
//  private RepositorioCombustible repositorioCombustible = new RepositorioCombustible(Combustible.class);
//  private RepositorioLineas repositorioLineas = new RepositorioLineas(Linea.class);
//  private RepositorioServicioContratado repositorioServicioContratado = new RepositorioServicioContratado(TipoServContratado.class);
//
//  @Test
//  public void generarDatos() {
//    this.generarDatosMiembro();
//    this.generarCombustibles();
//    this.generarLineas();
//    this.generarServiciosContratados();
//  }
//
//  private void generarDatosMiembro() {
//    Usuario usuarioPersona = new Usuario("cfulanito", "noSoyHomeroSoyCosmeFulanito", RolesUsuario.MIEMBRO);
//    Usuario usuarioOrganizacion = new Usuario("plantaNuclear", "isotopos_radioactivos", RolesUsuario.ADMINISTRADOR_GENERAL);
//    Sector sectorTecnologia = new Sector("Tecnología");
//    Direccion direccion = new Direccion("Calle Falsa", "123", new Localidad(3, ""));
//    Clasificacion clasificacion = new Clasificacion("Generación de Energía");
//    Organizacion plantaNuclear = new Organizacion(usuarioOrganizacion, "Planta de Springfield", "planta_nuclear@simpsons.com",
//        direccion, clasificacion, TipoOrg.EMPRESA);
//
//    Persona cosmeFulanito = new Persona("Cosme", "99.999.999", "Fulanito", usuarioPersona, "cosme_fulanito@simpsons.com", TipoDocumento.DNI);
//    Miembro cosmeFulanitoMiembroDePlantaNuclear = new Miembro(cosmeFulanito, plantaNuclear, sectorTecnologia, false);
//
//    this.repositorioMiembros.agregar(cosmeFulanitoMiembroDePlantaNuclear);
//  }
//
//  private void generarCombustibles() {
//    this.repositorioCombustible.agregar(new Combustible("Nafta"));
//    this.repositorioCombustible.agregar(new Combustible("Diesel"));
//    this.repositorioCombustible.agregar(new Combustible("GNC"));
//    this.repositorioCombustible.agregar(new Combustible("Electricida"));
//    this.repositorioCombustible.agregar(new Combustible("Leña"));
//    this.repositorioCombustible.agregar(new Combustible("Vapor"));
//    this.repositorioCombustible.agregar(new Combustible("Bio Diesel"));
//  }
//
//  private void generarLineas() {
//    Provincia provincia1 = new Provincia(1, "Buenos Aires");
//    Municipio municipio1 = new Municipio(21, "Ciudad de Buenos Aires Buenos Aires");
//    Localidad localidad1 = new Localidad(45, "Almagro");
//    Direccion direccion1 = new Direccion("Av. Medrano", "951", localidad1, municipio1, provincia1, "1111");
//
//    Provincia provincia2 = new Provincia(1, "Buenos Aires");
//    Municipio municipio2 = new Municipio(21, "Ciudad de Buenos Aires Buenos Aires");
//    Localidad localidad2 = new Localidad(45, "Lugano");
//    Direccion direccion2 = new Direccion("Mozart", "2300", localidad2, municipio2, provincia2, "2222");
//
//    List<Parada> paradasDel47 = Arrays.asList(
//        new Parada("Campus UTN", direccion1),
//        new Parada("Escalada y Eva Perón", direccion1),
//        new Parada("Medrano", direccion2),
//        new Parada("Liniers", direccion1)
//    );
//    List<Parada> paradasDeLineaA = Arrays.asList(
//        new Parada("San Pedrito", direccion2),
//        new Parada("Carabobo", direccion1),
//        new Parada("Río de Janeiro", direccion1),
//        new Parada("Plaza de Mayo", direccion1)
//    );
//    List<Parada> paradasDelSarmiento = Arrays.asList(
//        new Parada("Once", direccion1),
//        new Parada("Liniers", direccion1),
//        new Parada("Ituzaingó", direccion1),
//        new Parada("Moreno", direccion1)
//    );
//
//    List<Combustible> combustibles = this.repositorioCombustible.buscarTodos();
//    this.repositorioLineas.agregar(new Linea("Línea 47", TipoTransportePublico.COLECTIVO, combustibles.get(0), paradasDel47));
//    this.repositorioLineas.agregar(new Linea("Sarmiento", TipoTransportePublico.TREN, combustibles.get(4), paradasDelSarmiento));
//    this.repositorioLineas.agregar(new Linea("A", TipoTransportePublico.SUBTE, combustibles.get(4), paradasDeLineaA));
//    this.repositorioLineas.agregar(new Linea("Línea 114", TipoTransportePublico.COLECTIVO, combustibles.get(1), null));
//    this.repositorioLineas.agregar(new Linea("Roca", TipoTransportePublico.TREN, combustibles.get(4), null));
//  }
//
//  private void generarServiciosContratados() {
//    List<Combustible> combustibles = this.repositorioCombustible.buscarTodos();
//    this.repositorioServicioContratado.agregar(new TipoServContratado("Remís", TipoVehiculo.AUTO, combustibles.get(0)));
//    this.repositorioServicioContratado.agregar(new TipoServContratado("Remís", TipoVehiculo.AUTO, combustibles.get(1)));
//    this.repositorioServicioContratado.agregar(new TipoServContratado("Combi", TipoVehiculo.CAMIONETA, combustibles.get(1)));
//    this.repositorioServicioContratado.agregar(new TipoServContratado("Taxi", TipoVehiculo.AUTO, combustibles.get(0)));
//    this.repositorioServicioContratado.agregar(new TipoServContratado("Taxi", TipoVehiculo.AUTO, combustibles.get(3)));
//  }
//
//
//}
