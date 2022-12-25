package presentacion.models.requests;

import dominio.usuarios.Persona;
import dominio.usuarios.RolesUsuario;
import dominio.usuarios.Usuario;
import lombok.Getter;
import shared.helpers.HashCreador;
import shared.json.JsonRequired;

@Getter
public class CrearUsuarioModel {
 // private Integer id;
 // @JsonRequired(message = "El rol es requerido")
 //  private RolesUsuario rol;
 // @JsonRequired(message = "El usuario es requerido")
  private String usuario;

  //@JsonRequired(message = "La contraseña es requerida")
  private String contrasenia;

  //@JsonRequired(message = "La contraseña es requerida")
  private String mail;

  static HashCreador hashear = new HashCreador();

  public static Usuario toDomain(CrearUsuarioModel model){
    return new Usuario(model.usuario,  hashear.hashear(model.contrasenia), model.mail);
  }
}
