package dominio.mediosTransportes;

import com.google.gson.annotations.SerializedName;
import shared.enums.DisplayName;

public enum TipoMedioDeTransporte {
  @SerializedName("0")
  @DisplayName(displayName = "Transporte Público")
  TRANSPORTE_PUBLICO,
  @SerializedName("1")
  @DisplayName(displayName = "Vehículo Particular")
  VEHICULO_PARTICULAR,
  @SerializedName("2")
  @DisplayName(displayName = "Servicio Contratado")
  SERVICIO_CONTRATADO,
  @SerializedName("3")
  @DisplayName(displayName = "Medios Propios")
  MEDIOS_PROPIOS
}
