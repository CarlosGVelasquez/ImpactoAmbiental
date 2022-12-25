package dominio.actividades.Factory;

import java.time.LocalDate;
import java.util.Map;
import dominio.actividades.enums.Periodicidad;

public class Propiedades {
  public Propiedades(Map<String, String> propiedades) {
    validarPropiedades(propiedades);
  }

  private String actividad;
  private String tipoConsumo;
  private String valor;
  private String periodicidad;
  private String periodo;

  public String getActividad() {
    return actividad;
  }

  public String getTipoConsumo() {
    return tipoConsumo;
  }

  public String getValor() {
    return valor;
  }

  public Periodicidad getPeriodicidad() {
    return Periodicidad.valueOf(periodicidad);
  }

  public LocalDate getPeriodo() {
    return LocalDate.parse(this.periodo);
  }

  private void validarPropiedades(Map<String, String> propiedades) {
    if (!propiedades.containsKey(Constantes.ACTIVIDAD)
    || !propiedades.containsKey(Constantes.TIPO_CONSUMO)
    || !propiedades.containsKey(Constantes.VALOR)
    || !propiedades.containsKey(Constantes.PERIODICIDAD)
    || !propiedades.containsKey(Constantes.PERIODO)
    ) {
      throw new ParseErrorException("Los datos del encabezado de la tabla no son válidos");
    }

    this.actividad = propiedades.get(Constantes.ACTIVIDAD);
    this.tipoConsumo = propiedades.get(Constantes.TIPO_CONSUMO);
    this.valor = propiedades.get(Constantes.VALOR);
    this.periodicidad = propiedades.get(Constantes.PERIODICIDAD);
    this.periodo = propiedades.get(Constantes.PERIODO);
  }
}
