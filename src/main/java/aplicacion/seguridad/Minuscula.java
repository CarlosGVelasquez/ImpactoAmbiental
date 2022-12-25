package aplicacion.seguridad;

public class Minuscula extends ExpresionRegular {
  public Minuscula(){
    super("^(?=.*[a-z]).+$");
  }
}
