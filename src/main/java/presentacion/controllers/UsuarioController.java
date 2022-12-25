package presentacion.controllers;

import dominio.organizacion.Miembro;
import dominio.organizacion.Organizacion;
import dominio.usuarios.AgenteSectorial;
import dominio.usuarios.RolesUsuario;
import dominio.usuarios.Usuario;
import infraestructura.APICalculoDistancia.entities.User;
import infraestructura.repositorios.RepositorioAgentesSectoriales;
import infraestructura.repositorios.RepositorioMiembros;
import infraestructura.repositorios.RepositorioOrganizaciones;
import infraestructura.repositorios.RepositorioUsuarios;
import presentacion.models.requests.CrearUsuarioModel;
import presentacion.models.requests.LoginUsuarioModel;
import presentacion.security.UserClaims;
import shared.helpers.HashCreador;
import shared.json.Json;
import spark.Request;
import spark.Response;
import spark.Session;

public class UsuarioController extends BaseController {
  private RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios(Usuario.class);
  private RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones(Organizacion.class);
  private RepositorioMiembros repositorioMiembros = new RepositorioMiembros(Miembro.class);
  private RepositorioAgentesSectoriales repositorioAgentesSectoriales = new RepositorioAgentesSectoriales(AgenteSectorial.class);

  public Object getUsuarioLogeado(Request req, Response res) {
    UserClaims userClaims = getUserClaims(req);
    return ok(userClaims, res);
  }

  public Object crearUsuario(Request req, Response res){
    CrearUsuarioModel crearUsuarioModel = Json.fromJson(req.body(), CrearUsuarioModel.class);
    Usuario usuario = CrearUsuarioModel.toDomain(crearUsuarioModel);

    if (this.repositorioUsuarios.getUsuarioByUsuario(crearUsuarioModel.getUsuario()) != null) {
      return conflict("El usuario ingresado ya está registrado en el sistema", res);
    }

    this.repositorioUsuarios.agregar(usuario);
    return created(usuario, res);
  }

  public Object login(Request req, Response res) {
    LoginUsuarioModel loginUsuarioModel = Json.fromJson(req.body(), LoginUsuarioModel.class);
    Usuario usuario = this.repositorioUsuarios.getUsuarioByUsuario(loginUsuarioModel.usuario);
    if (usuario == null) {
      return unauthorized("El usuario o contraseña ingresados no son válidos", res);
    }

    String hashPassowrd = HashCreador.hashear(loginUsuarioModel.contrasenia);
    if (usuario.getContrasenia().equals(hashPassowrd)){
      UserClaims userClaims = crearSesion(req, usuario);
      return ok(userClaims, res);
    }
    return unauthorized("El usuario o contraseña ingresados no son válidos", res);
  }

  public Object logout(Request req, Response res) {
    req.session().invalidate();
    sessions.remove(req.headers("JSESSIONID"));
    return noContent(null, res);
  }

  private UserClaims crearSesion(Request req, Usuario usuario) {
    // TODO: mejorar esto para no ir 3 veces a la base. Usar el Rol del usuario para decidir a qué repo ir
    // TODO: version 2 => tratar de sacar el switch feo

    Miembro miembro = null;
    Organizacion organizacion = null;
    AgenteSectorial agenteSectorial = null;

    if (usuario.getRol() != null) {
      switch (usuario.getRol()) {
        case MIEMBRO:
          miembro = this.repositorioMiembros.getByUsuarioId(usuario.getId());
          break;
        case ADMINISTRADOR_ORGANIZACION:
          organizacion = this.repositorioOrganizaciones.getByUsuarioId(usuario.getId());
          break;
        case AGENTE_SECTORIAL:
          agenteSectorial = this.repositorioAgentesSectoriales.getByUsuarioId(usuario.getId());
          break;
      }
    }
    Integer miembroId = miembro != null ? miembro.getId() : null;
    Integer organizacionId = organizacion != null ? organizacion.getId() : null;
    Integer agenteSectorialId = agenteSectorial != null ? agenteSectorial.getId() : null;

    String sessionId = req.session(true).id();
    UserClaims userClaims = new UserClaims(usuario.getId(), usuario.getRol(), miembroId, organizacionId, agenteSectorialId, usuario.getNombreUsuario(), sessionId);
    sessions.put(sessionId, userClaims);

    return userClaims;
  }
}