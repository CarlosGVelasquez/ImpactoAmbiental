package aplicacion.seguridad;

public class Digito extends ExpresionRegular {
  public Digito(){
    super("^(?=.*[0-9]).+$");
  }
}

