package dominio.mediosTransportes;

import com.google.gson.annotations.SerializedName;
import shared.enums.DisplayName;

public enum TipoVehiculo {
        @DisplayName(displayName = "Auto")
        @SerializedName("0")
        AUTO,
        @DisplayName(displayName = "Moto")
        @SerializedName("1")
        MOTO,
        @DisplayName(displayName = "Camioneta")
        @SerializedName("2")
        CAMIONETA
}
