package aplicacion.seguridad;

public class Longitud implements Verificable {

  public boolean verificar(String password) {
    return password.length() >= 8;
  }
}

