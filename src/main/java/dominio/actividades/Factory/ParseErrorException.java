package dominio.actividades.Factory;

public class ParseErrorException extends RuntimeException{
  public ParseErrorException(String message) {
    super(message);
  }

  public ParseErrorException() {
    super("Los datos ingresados en el archivo no son válidos");
  }
}
