package dominio.usuarios;

import com.google.gson.annotations.SerializedName;
import shared.enums.DisplayName;

public enum TipoDocumento{
    @DisplayName(displayName = "DNI")
    @SerializedName("0")
    DNI,
    @DisplayName(displayName = "Pasaporte")
    @SerializedName("1")
    PASAPORTE,
    @DisplayName(displayName = "Cédula de Identidad")
    @SerializedName("2")
    CI
}
