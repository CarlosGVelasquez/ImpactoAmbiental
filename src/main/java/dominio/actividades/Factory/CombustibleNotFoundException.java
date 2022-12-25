package dominio.actividades.Factory;

public class CombustibleNotFoundException extends RuntimeException {
  public CombustibleNotFoundException(String combustible) {
    super("El combustible ingresado (" + combustible + ") no existe en el sistema. Contacte al administrador");
  }
}
