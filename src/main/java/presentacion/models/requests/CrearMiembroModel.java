package presentacion.models.requests;

import lombok.Getter;

@Getter
public class CrearMiembroModel {
  private PersonaModel persona;
  private Integer organizacionId;
  private Integer sectorId;
}
