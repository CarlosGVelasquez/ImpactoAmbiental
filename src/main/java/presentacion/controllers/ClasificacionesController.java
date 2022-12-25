package presentacion.controllers;

import dominio.organizacion.Clasificacion;
import infraestructura.repositorios.RepositorioClasificaciones;
import spark.Request;
import spark.Response;

import java.util.List;

public class ClasificacionesController extends BaseController {
    private RepositorioClasificaciones repositorioClasificaciones = new RepositorioClasificaciones(Clasificacion.class);

    public Object getClasificaciones(Request req, Response resp){
        List<Clasificacion> clasificaciones = repositorioClasificaciones.buscarTodos();
        return ok(clasificaciones,resp);
    }
}
