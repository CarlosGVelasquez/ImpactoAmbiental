package dominio.mediosTransportes;

import com.google.gson.annotations.SerializedName;
import shared.enums.DisplayName;

public enum TipoTransportePublico {
    @DisplayName(displayName = "Colectivo")
    @SerializedName("0")
    COLECTIVO,
    @DisplayName(displayName = "Subte")
    @SerializedName("1")
    SUBTE,
    @DisplayName(displayName = "Tren")
    @SerializedName("2")
    TREN
}
