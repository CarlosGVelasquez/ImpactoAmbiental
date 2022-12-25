package main;

import com.google.gson.JsonParseException;
import dominio.actividades.Factory.CombustibleNotFoundException;
import dominio.factoresEmision.Factory.FactorEmisionNotFoundException;
import presentacion.controllers.*;
import spark.Spark;
import spark.servlet.SparkApplication;

import static spark.Spark.*;

public class Routes extends BaseController implements SparkApplication {
  private PingController pingController = new PingController();
  private UsuarioController usuarioController = new UsuarioController();
  private MetadataController metadataController = new MetadataController();
  private MiembrosController miembrosController = new MiembrosController();
  private FactorEmisionController factorEmisionController = new FactorEmisionController();
  private CombustiblesController combustiblesController = new CombustiblesController();
  private LineasParadasController lineasParadasController = new LineasParadasController();
  private ClasificacionesController clasificacionesController = new ClasificacionesController();
  private OrganizacionController organizacionController = new OrganizacionController();
  private TrayectoController trayectoController = new TrayectoController();
  private ServicioContratadoController servicioContratadoController = new ServicioContratadoController();
  private ActividadesController actividadesController = new ActividadesController();
  private LocalizacionController localizacionController = new LocalizacionController();

  @Override
  public void init() {
    handleCustomExceptions();
    before(((request, response) -> {
        response.header("Access-Control-Allow-Origin","*");
        response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
      }
    ));
    after(((request, response) -> response.type("application/json")));
    
    // Lo puse igual que en ejemplo de clase.
    //Spark.before("", AuthMiddleware::verificarSesion);
    //Spark.before("/*", AuthMiddleware::verificarSesion);

    //Rutas
    Spark.get("/ping", pingController::ping);

    String defaultRoute = System.getenv("SERVER_PORT") != null && !"".equals(System.getenv("SERVER_PORT")) ?  "/apiv2" : "/apiv1" ;
    Spark.path(defaultRoute, () -> {

      Spark.get("/ping", pingController::ping);

      Spark.path("/usuarios", () -> {
        Spark.post("/login", usuarioController::login);
        Spark.post("/logout", usuarioController::logout);
        Spark.get("/data", usuarioController::getUsuarioLogeado);
      });
      // TODO: cambiar a "" y meter en el grupo /usuarios
      Spark.post("/register", usuarioController::crearUsuario);

      Spark.path("/factor-emision", () -> {
        Spark.post("", factorEmisionController::crearFactorEmision);
        Spark.get("", factorEmisionController::getFactoresEmision);
      });

      Spark.path("/miembro", () -> {
        Spark.post("/:id/aceptar",miembrosController::aceptarMiembro);
        Spark.post("/:id/rechazar", miembrosController::rechazarMiembro);
        Spark.get("/:id",miembrosController::getMiembro);
        Spark.post("", miembrosController::crearMiembro);
      });

      Spark.path("/organizaciones", () -> {
        Spark.get("/:id/miembros", organizacionController::getMiembrosOrganizacion);
      });
      // TODO: cambiar a plural y meter el grupo /organizaciones
      Spark.post("/organizacion", organizacionController::crearOrganizacion);
      Spark.get("/organizacion", organizacionController::getOrganizaciones);


      Spark.get("/metadata-enums", metadataController::getSystemEnums);

      Spark.get("/combustibles", combustiblesController::getCombustibles);

      Spark.get("/clasificaciones", clasificacionesController::getClasificaciones);

      Spark.post("/trayectos", trayectoController::crearTrayecto);

      // TODO: cambiar ruta de paradas => /lineas/:idLinea/paradas
      Spark.get("/lineas", lineasParadasController::getLineas);
      Spark.get("/paradas/:id", lineasParadasController::getParadasById);


      Spark.get("/servicios-contratados", servicioContratadoController::getServiciosContratado);
      Spark.post("/actividades", actividadesController::cargarActividades);

      Spark.path("/localizacion", () -> {
        Spark.get("/provincias",localizacionController::getProvincias);
        Spark.get("/municipios", localizacionController::getMunicipios);
        Spark.get("/localidades",localizacionController::getLocalidades);
      });
    });
    Spark.init();
  }


  private void handleCustomExceptions() {
    exception(Exception.class, (exception, request, response) -> {
      response.type("application/json");
      Object exceptionClass = exception.getClass();

      if (FactorEmisionNotFoundException.class.equals(exceptionClass)) {
        conflict("Ocurrió un error: " + getErrorMessage(exception), response);
        return;
      }

      if (JsonParseException.class.equals(exceptionClass)) {
        unprocessableEntity("Ocurrió un error: " + getErrorMessage(exception), response);
        return;
      }

      if (CombustibleNotFoundException.class.equals(exceptionClass)) {
        unprocessableEntity("Ocurrió un error: " + getErrorMessage(exception), response);
        return;
      }
      // Acá se deben agregar todas las excepciones personalizadas del sistema


      // Salida por defecto para excepciones no controlados
      badRequest("Ocurrió un error inesperado: " + getErrorMessage(exception), response);
    });
  }

  //TODO: sacar email stack trace una vez terminado el TP, para no exponer información del sistema a terceros
  private String getErrorMessage(Exception e) {
    return e.getClass().getCanonicalName() + " - " + e.getMessage() + " - Revisar línea: " + e.getStackTrace()[0];
  }
}



// https://www.mscharhag.com/java/building-rest-api-with-spark => ver toJson(object)
// exception();
// https://stackoverflow.com/questions/56265973/using-after-filter-modifies-the-response-code-to-200-ok

