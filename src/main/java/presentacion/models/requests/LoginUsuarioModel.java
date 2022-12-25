package presentacion.models.requests;

import shared.json.JsonRequired;

public class LoginUsuarioModel {
  @JsonRequired(message = "El usuario es requerido")
  public String usuario;

  @JsonRequired(message = "La contraseña es requerida")
  public String contrasenia;
}
