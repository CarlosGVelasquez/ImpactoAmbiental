package presentacion.controllers;

import dominio.factoresEmision.FactorEmision;
import dominio.organizacion.Miembro;
import dominio.viajes.Trayecto;
import infraestructura.repositorios.RepositorioFactorEmision;
import infraestructura.repositorios.RepositorioMiembros;
import infraestructura.repositorios.RepositorioTrayecto;
import infraestructura.repositorios.Repository;
import presentacion.models.requests.CrearFactorEmisionModel;
import presentacion.models.requests.CrearTrayectoModel;
import presentacion.models.requests.RequestMapper;
import shared.json.Json;
import spark.Request;
import spark.Response;

public class TrayectoController extends BaseController{

    private final RepositorioTrayecto repositorioTrayecto = new RepositorioTrayecto(Trayecto.class);
    private final RepositorioMiembros repositorioMiembros = new RepositorioMiembros(Miembro.class);
    private final RequestMapper requestMapper = new RequestMapper();

    public Object crearTrayecto(Request req, Response resp) {
        CrearTrayectoModel crearTrayectoModel = Json.fromJson(req.body(), CrearTrayectoModel.class );
        Trayecto trayectoDomain = requestMapper.toDomain(crearTrayectoModel);

        Integer idMiembro = getUserClaims(req).getMiembroId();
        Miembro miembro  = this.repositorioMiembros.buscar(idMiembro);

        miembro.setTrayecto(trayectoDomain);
        this.repositorioMiembros.modificar(miembro);
        return ok(1, resp);
    }

}
