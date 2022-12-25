package presentacion.security;

import dominio.usuarios.RolesUsuario;
import lombok.Getter;

@Getter
public class UserClaims {
  private Integer usuarioId;
  private RolesUsuario rol;
  private Integer miembroId;
  private Integer organizacionId;
  private Integer agenteSectorialId;
  private String usuario;
  private String sessionId;

  public UserClaims(Integer userId, RolesUsuario rol, Integer miembroId, Integer organizacionId, Integer agenteSectorialId, String usuario, String sessionId) {
    this.usuarioId = userId;
    this.rol = rol;
    this.miembroId = miembroId;
    this.organizacionId = organizacionId;
    this.agenteSectorialId = agenteSectorialId;
    this.usuario = usuario;
    this.sessionId = sessionId;
  }

  private Boolean checkRol(RolesUsuario rol) {
    return this.rol == rol;
  }

  public Boolean esMiembro() {
    return checkRol(RolesUsuario.MIEMBRO);
  }

  public Boolean esAdministradorOrganizacion() {
    return checkRol(RolesUsuario.ADMINISTRADOR_ORGANIZACION);
  }

  public Boolean esAdministradorGeneral() {
    return checkRol(RolesUsuario.ADMINISTRADOR_GENERAL);
  }

  public Boolean esAgenteSectorial() {
    return checkRol(RolesUsuario.AGENTE_SECTORIAL);
  }
}

