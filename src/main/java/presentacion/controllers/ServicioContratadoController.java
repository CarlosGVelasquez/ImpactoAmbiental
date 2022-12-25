package presentacion.controllers;

import dominio.mediosTransportes.TipoServContratado;
import infraestructura.repositorios.RepositorioServicioContratado;
import spark.Request;
import spark.Response;

import java.util.List;

public class ServicioContratadoController extends BaseController {
  private RepositorioServicioContratado repositorioServicioContratado = new RepositorioServicioContratado(TipoServContratado.class);

  public Object getServiciosContratado(Request req, Response res) {
    List<TipoServContratado> tiposServicioContratado = this.repositorioServicioContratado.buscarTodos();
    return ok(tiposServicioContratado, res);
  }
}
