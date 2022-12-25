package dominio.usuarios;

import com.google.gson.annotations.SerializedName;
import shared.enums.DisplayName;

public enum RolesUsuario {
    @SerializedName("0")
    @DisplayName(displayName = "Miembro")
    MIEMBRO,

    @SerializedName("1")
    @DisplayName(displayName = "Administrador General")
    ADMINISTRADOR_GENERAL,

    @SerializedName("2")
    @DisplayName(displayName = "Administrador Organización")
    ADMINISTRADOR_ORGANIZACION,

    @SerializedName("3")
    @DisplayName(displayName = "Agente Sectorial")
    AGENTE_SECTORIAL;
}