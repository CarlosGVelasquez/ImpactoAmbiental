package dominio.actividades.enums;

import com.google.gson.annotations.SerializedName;
import shared.enums.DisplayName;

public enum TipoActividad {
  @DisplayName(displayName = "Combustión Fija")
  @SerializedName("0")
  COMBUSTION_FIJA,
  @DisplayName(displayName = "Combustión Móvil")
  @SerializedName("1")
  COMBUSTION_MOVIL,
  @DisplayName(displayName = "Electricidad Adquirida y Consumida")
  @SerializedName("2")
  ELECTRICA_ADQUIRIDA_CONSUMIDA,
  @DisplayName(displayName = "Logística de Productos y Residuos")
  @SerializedName("3")
  LOGISTICA_PRODUCTOS_RESIDUOS
}
