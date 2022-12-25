package presentacion.models.responses;

public class MiembroModel {
  private Integer id;
  private OrganizacionModel organizacion;
  private PersonaModel persona;
  private TrayectoModel trayecto;
  private Double huella; // Para llenar esto, llamar dentro del mapeador al servicio CalculadoraHc
  private Boolean aprobado;

  private SectorModel sector;

  public MiembroModel(Integer id, OrganizacionModel organizacion, PersonaModel persona, TrayectoModel trayecto, Double huella, SectorModel sector, Boolean aprobado) {
    this.id = id;
    this.organizacion = organizacion;
    this.persona = persona;
    this.trayecto = trayecto;
    this.huella = huella;
    this.sector = sector;
    this.aprobado = aprobado;
  }
}
