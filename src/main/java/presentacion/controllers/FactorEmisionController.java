package presentacion.controllers;

import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import infraestructura.repositorios.RepositorioCombustible;
import infraestructura.repositorios.RepositorioFactorEmision;
import presentacion.models.requests.CrearFactorEmisionModel;
import presentacion.models.requests.RequestMapper;
import shared.json.Json;
import spark.Request;
import spark.Response;

import java.util.List;

public class FactorEmisionController extends BaseController {

  private RepositorioFactorEmision repositorioFactorEmision = new RepositorioFactorEmision(FactorEmision.class);
  private RepositorioCombustible repositorioCombustible = new RepositorioCombustible(Combustible.class);
  private RequestMapper requestMapper = new RequestMapper();
  public Object crearFactorEmision(Request req, Response resp) {
    CrearFactorEmisionModel crearFactorEmisionModel = Json.fromJson(req.body(), CrearFactorEmisionModel.class);

    FactorEmision factorEmisionDomain = requestMapper.toDomain(crearFactorEmisionModel);
    this.repositorioFactorEmision.agregar(factorEmisionDomain);
    return ok(1, resp);
  }

  public Object getFactoresEmision(Request req, Response resp) {
    List<FactorEmision> factoresEmision = this.repositorioFactorEmision.buscarTodos();
    return ok(factoresEmision, resp);
  }
}
