package aplicacion.seguridad;

public class Mayuscula extends ExpresionRegular {
  public Mayuscula(){
    super("^(?=.*[A-Z]).+$");
  }
}
