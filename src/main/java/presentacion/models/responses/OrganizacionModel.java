package presentacion.models.responses;

import java.util.List;

public class OrganizacionModel {
  private Integer id;
  private String nombre;
  private Double huella;
  private List<SectorModel> sectores;
  public OrganizacionModel(String nombre, Double huella, List<SectorModel> sectores, Integer id) {
    this.nombre = nombre;
    this.huella = huella;
    this.sectores = sectores;
    this.id = id;
  }
}
