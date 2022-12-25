package presentacion.controllers;

import dominio.mediosTransportes.Linea;
import dominio.mediosTransportes.Parada;
import dominio.mediosTransportes.TipoTransportePublico;
import infraestructura.repositorios.RepositorioLineas;
import infraestructura.repositorios.RepositorioParadas;
import spark.Request;
import spark.Response;

import java.util.List;

public class LineasParadasController extends BaseController {

  private RepositorioLineas repositorioLineas = new RepositorioLineas(Linea.class);
  private RepositorioParadas repositorioParadas = new RepositorioParadas(Parada.class);

  public Object getLineas(Request req, Response res) {
    List<Linea> lineas = this.repositorioLineas.buscarTodos();
    return ok(lineas, res);
  }

  public Object getParadasById(Request req, Response res) {
    Integer idLinea = Integer.parseInt(req.params("id"));
    List<Parada> paradas = this.repositorioParadas.getParadasByLineaId(idLinea);
    return ok(paradas, res);
  }
}