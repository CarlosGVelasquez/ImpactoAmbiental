package aplicacion.seguridad;

import java.util.ArrayList;
import java.util.List;

public class Validador {
  List<Verificable> validaciones = new ArrayList<>();

  //Por defecto, el validador valida al menos la condición de longitud
  public Validador() {
    this.validaciones.add(new Longitud());
  }

  public void agregarValidacion(Verificable validacion) {
    validaciones.add(validacion);
  }

  public void quitarValidacion(Verificable validacion) {
    validaciones.remove(validacion);
  }

  public boolean validarPassword(String password) {
    return this.validaciones.stream().allMatch(v -> v.verificar(password));
  }
}
