package dominio.organizacion;

import com.google.gson.annotations.SerializedName;
import shared.enums.DisplayName;

public enum  TipoOrg {
        @SerializedName("0")
        @DisplayName(displayName = "Gubernamental")
        GUBERNAMENTAL,
        @SerializedName("1")
        @DisplayName(displayName = "Ong")
        ONG,
        @SerializedName("2")
        @DisplayName(displayName = "Empresa")
        EMPRESA,
        @SerializedName("3")
        @DisplayName(displayName = "Institución")
        INSTITUCION
}
