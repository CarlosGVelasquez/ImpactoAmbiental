package dominio.actividades.enums;


public enum Periodicidad {
  ANUAL(365), MENSUAL(30);

  private Integer dias;

  Periodicidad(Integer dias) {
    this.dias = dias;
  }

  public Integer getDias() {
    return this.dias;
  }
}