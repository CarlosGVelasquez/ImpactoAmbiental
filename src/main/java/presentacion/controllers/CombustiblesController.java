package presentacion.controllers;

import dominio.factoresEmision.Combustible;
import infraestructura.repositorios.RepositorioCombustible;
import spark.Request;
import spark.Response;

import java.util.List;

public class CombustiblesController extends BaseController {
  private RepositorioCombustible repositorioCombustible = new RepositorioCombustible(Combustible.class);

  public Object getCombustibles(Request req, Response resp) {
    List<Combustible> combustibles = repositorioCombustible.buscarTodos();
    return ok(combustibles, resp);
  }
}
