package presentacion.controllers;

import aplicacion.parseExcel.ParseExcel;
import dominio.actividades.Actividad;
import dominio.actividades.Factory.CreadorActividad;
import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import dominio.organizacion.Organizacion;
import infraestructura.repositorios.RepositorioCombustible;
import infraestructura.repositorios.RepositorioFactorEmision;
import infraestructura.repositorios.RepositorioOrganizaciones;
import infraestructura.repositorios.interfaces.IRepositorioCombustible;
import infraestructura.repositorios.interfaces.IRepositorioFactorEmision;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ActividadesController extends BaseController {
  private IRepositorioCombustible repositorioCombustible = new RepositorioCombustible(Combustible.class);
  private IRepositorioFactorEmision repositorioFactorEmision = new RepositorioFactorEmision(FactorEmision.class);
  private RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones(Organizacion.class);

  public Object cargarActividades(Request req, Response res) throws ServletException, IOException {
    Integer organizacionId = getUserClaims(req).getOrganizacionId();

    req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement(""));
    InputStream input = req.raw().getPart("uploadFile").getInputStream();
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    executorService.execute(() -> {
      //Sólo para simular demora en el procesamiento del archivo. Se esperan 30 segundos
      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      CreadorActividad creadorActividad = new CreadorActividad(repositorioFactorEmision, repositorioCombustible);
      ParseExcel parseExcel = new ParseExcel();
      List<Actividad> actividades = creadorActividad.crearActividades(parseExcel.leerExcelFromBytes(input));

      Organizacion organizacion = this.repositorioOrganizaciones.buscar(organizacionId);
      organizacion.agregarActividad(actividades);
      this.repositorioOrganizaciones.modificar(organizacion);
      // TODO: enviar notificación
    });
    return created(null, res);
  }
}
