package aplicacion.servicios.calculadoraHc;


import dominio.organizacion.Sector;

public class ResultadosHcSector {
  private final Sector sector;
  private final Double huellaSector;
  private final Integer cantidadMiembros;
  private final Double factor;

  public ResultadosHcSector(Sector sector, Double huellaSector, Integer cantidadMiembros) {
    this.sector = sector;
    this.cantidadMiembros = cantidadMiembros;
    this.huellaSector = huellaSector;
    this.factor = huellaSector / cantidadMiembros;
  }
}
