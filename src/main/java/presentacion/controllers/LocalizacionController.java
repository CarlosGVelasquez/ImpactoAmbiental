package presentacion.controllers;

import infraestructura.APICalculoDistancia.adapter.AdapterDistancia;
import infraestructura.APICalculoDistancia.adapter.AdapterRetrofit;
import infraestructura.APICalculoDistancia.entities.Localidad;
import infraestructura.APICalculoDistancia.entities.Municipio;
import infraestructura.APICalculoDistancia.entities.Provincia;
import spark.Request;
import spark.Response;

import java.util.List;

public class LocalizacionController extends BaseController {
  private AdapterDistancia adapterLocalizacion = new AdapterRetrofit();

  public Object getProvincias(Request req, Response res) {
    List<Provincia> provincias = adapterLocalizacion.getProvincias();
    return ok(provincias, res);
  }

  public Object getMunicipios(Request req, Response res) {
    String provinciaId = req.queryParams("provinciaId");
    List<Municipio> municipios = adapterLocalizacion.getMunicipios(Integer.parseInt(provinciaId));
    return ok(municipios, res);
  }

  public Object getLocalidades(Request req, Response res) {
    String municipioId = req.queryParams("municipioId");
    List<Localidad> localidades = adapterLocalizacion.getLocalidades(Integer.parseInt(municipioId));
    return ok(localidades, res);
  }
}
