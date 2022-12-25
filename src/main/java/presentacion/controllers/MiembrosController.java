package presentacion.controllers;

import dominio.organizacion.Miembro;
import dominio.usuarios.Persona;
import dominio.usuarios.RolesUsuario;
import dominio.usuarios.Usuario;
import infraestructura.repositorios.RepositorioMiembros;
import infraestructura.repositorios.RepositorioUsuarios;
import presentacion.models.requests.CrearMiembroModel;
import presentacion.models.requests.RequestMapper;
import presentacion.models.responses.MiembroModel;
import presentacion.models.responses.ResponseMapper;
import shared.json.Json;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

public class MiembrosController extends BaseController{

    private RequestMapper requestMapper = new RequestMapper();
    private RepositorioMiembros repositorioMiembros = new RepositorioMiembros(Miembro.class);
    private RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios(Usuario.class);

    public Object aceptarMiembro(Request request, Response response) {

        String idBuscado = request.params("id");
        Miembro miembroBuscado = repositorioMiembros.buscar(Integer.parseInt(idBuscado));

        if (miembroBuscado==null)
            return notFound("No se encontro el miembro", response);

        if (miembroBuscado.isAprobado())
            return badRequest("El miembro ya fue aprobado",response);

        miembroBuscado.setAprobado(true);
        repositorioMiembros.modificar(miembroBuscado);
        return ok("Miembro Aceptado", response);

    }

    public Object rechazarMiembro(Request request, Response response) {
        String idBuscado = request.params("id");
        Miembro miembroBuscado = repositorioMiembros.buscar(Integer.parseInt(idBuscado));
        if (miembroBuscado==null)
            return notFound("No se encontro el miembro", response);
        if (miembroBuscado.isAprobado())
            return badRequest("El miembro ya fue aprobado",response);

        repositorioMiembros.eliminar(miembroBuscado);
        return ok("Miembro rechazado", response);
    }

    public Object getMiembro(Request request, Response response) {
        String idBuscado = request.params("id");
        Miembro miembroBuscado = repositorioMiembros.buscar(Integer.parseInt(idBuscado));
        return miembroBuscado== null?  badRequest("No existe el miembro", response):
        ok(new ResponseMapper().toResponse(miembroBuscado),response);
    }

    public Object getMiembrosAprobados(Request req, Response resp) {
        List<Miembro> miembros = this.repositorioMiembros.buscarTodos();
        ResponseMapper mapper = new ResponseMapper();
        List<MiembroModel> miembrosModel = miembros.stream().map(miembro -> mapper.toResponse(miembro)).collect(Collectors.toList());
        return ok(miembrosModel,resp);
    }

    public Object crearMiembro(Request req, Response resp){
        CrearMiembroModel crearMiembroModel = Json.fromJson(req.body(), CrearMiembroModel.class);
        Miembro miembro = this.requestMapper.toDomain(crearMiembroModel, getUserClaims(req));
        this.repositorioMiembros.agregar(miembro);
        Usuario usuario = this.repositorioUsuarios.buscar(getUserClaims(req).getUsuarioId());
        usuario.setRol(RolesUsuario.MIEMBRO);;
        this.repositorioUsuarios.modificar(usuario);
        return ok(null,resp);
    }
}