package dominio.factoresEmision;

import com.google.gson.annotations.SerializedName;
import shared.enums.DisplayName;

public enum TipoFactorEmision {
  @DisplayName(displayName = "Tipo de Actividad")
  @SerializedName("0")
  ACTIVIDAD,
  @DisplayName(displayName = "Tipo Transporte Público")
  @SerializedName("1")
  TRANSPORTE_PUBLICO,
  @DisplayName(displayName = "Tipo Vehículo")
  @SerializedName("2")
  VEHICULO
}
