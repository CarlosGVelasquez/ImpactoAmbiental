package presentacion.controllers;

import aplicacion.servicios.calculadoraHc.CalculadoraHc;
import dominio.organizacion.Organizacion;
import dominio.usuarios.RolesUsuario;
import dominio.usuarios.Usuario;
import infraestructura.APICalculoDistancia.adapter.AdapterRetrofit;
import infraestructura.repositorios.RepositorioOrganizaciones;
import infraestructura.repositorios.RepositorioUsuarios;
import presentacion.models.requests.CrearOrganizacionModel;
import presentacion.models.requests.RequestMapper;
import presentacion.models.responses.MiembroModel;
import presentacion.models.responses.OrganizacionModel;
import presentacion.models.responses.ResponseMapper;
import shared.json.Json;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

public class OrganizacionController extends BaseController{

    private RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones(Organizacion.class);
    private RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios(Usuario.class);
    private RequestMapper requestMapper = new RequestMapper();
    private CalculadoraHc calculadoraHc = new CalculadoraHc(repositorioOrganizaciones, new AdapterRetrofit());
    private ResponseMapper responseMapper = new ResponseMapper(calculadoraHc);

    public Object crearOrganizacion(Request req, Response resp) {
        CrearOrganizacionModel crearOrganizacionModel = Json.fromJson(req.body(),CrearOrganizacionModel.class);
        Organizacion organizacionDomain = requestMapper.toDomain(crearOrganizacionModel, getUserClaims(req));
        this.repositorioOrganizaciones.agregar(organizacionDomain);
        Usuario usuario = this.repositorioUsuarios.buscar(getUserClaims(req).getUsuarioId());
        usuario.setRol(RolesUsuario.ADMINISTRADOR_ORGANIZACION);
        this.repositorioUsuarios.modificar(usuario);
        return created(null, resp);
    }

    public Object getOrganizaciones(Request req, Response res) {
        List<Organizacion> organizaciones = this.repositorioOrganizaciones.buscarTodos();
        List<OrganizacionModel> organizacionesModel = organizaciones.stream().map(o -> this.responseMapper.organizacionResponse(o)).collect(Collectors.toList());
        return ok(organizacionesModel, res);
    }

    public Object getMiembrosOrganizacion(Request req, Response res) {
        Integer id = Integer.parseInt(req.params("id"));
        Organizacion organizacion = this.repositorioOrganizaciones.buscar(id);
        List<MiembroModel> miembros = organizacion.getMiembros().stream().map(m -> this.responseMapper.toResponse(m)).collect(Collectors.toList());
        return ok(miembros, res);
    }
}
