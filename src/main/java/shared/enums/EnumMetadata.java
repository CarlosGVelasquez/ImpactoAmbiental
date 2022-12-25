package shared.enums;

import dominio.actividades.enums.*;
import dominio.factoresEmision.TipoFactorEmision;
import dominio.mediosTransportes.TipoMedioDeTransporte;
import dominio.mediosTransportes.TipoTransportePublico;
import dominio.mediosTransportes.TipoVehiculo;
import dominio.organizacion.TipoOrg;
import dominio.usuarios.RolesUsuario;
import dominio.usuarios.TipoDocumento;
import dominio.viajes.EstadoInvitacion;
import lombok.Getter;
import presentacion.models.responses.SystemEnumsModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EnumMetadata {
  private Integer value;
  private String name;
  private String displayName;

  public EnumMetadata(Integer value, String name, String displayName) {
    this.value = value;
    this.name = name;
    this.displayName = displayName;
  }

  public static <T extends Enum<T>> List<EnumMetadata> getMetadata(T[] values) {
    return Arrays.stream(values).map(e ->
        new EnumMetadata(e.ordinal(), e.name(), getDisplayName(e))).collect(Collectors.toList());
  }

  public static SystemEnumsModel getSystemEnumsModel() {
    return new SystemEnumsModel() {{
      rolesUsuario = EnumMetadata.getMetadata(RolesUsuario.values());
      tipoActividad = EnumMetadata.getMetadata(TipoActividad.values());
      alcance = EnumMetadata.getMetadata(Alcance.values());
      categoriaProductoTransportado = EnumMetadata.getMetadata(CategoriaProductoTranportado.values());
      medioDeTransporteLogistica = EnumMetadata.getMetadata(MedioDeTransporteLogistica.values());
      tipoTransportePublico = EnumMetadata.getMetadata(TipoTransportePublico.values());
      tipoVehiculo = EnumMetadata.getMetadata(TipoVehiculo.values());
      periodicidad = EnumMetadata.getMetadata(Periodicidad.values());
      tipoFactorEmision = EnumMetadata.getMetadata(TipoFactorEmision.values());
      tipoDocumento = EnumMetadata.getMetadata(TipoDocumento.values());
      tipoOrg = EnumMetadata.getMetadata(TipoOrg.values());
      estadoInvitacion = EnumMetadata.getMetadata(EstadoInvitacion.values());
      tipoMedioDeTransporte = EnumMetadata.getMetadata(TipoMedioDeTransporte.values());
    }};
  }

  private static<T extends Enum<T>> String getDisplayName(T enumValue) {
    DisplayName annotation;
    try {
      annotation = enumValue.getClass().getField(enumValue.name()).getAnnotation(DisplayName.class);
    }
    catch (Exception e) {
      throw new RuntimeException("Error al intentar obtener el enum");
    }
    return annotation != null ? annotation.displayName() : "";
  }

  public static <T extends Enum<T>> EnumMetadata getEnumMetadata(T value) {
    return  new EnumMetadata(value.ordinal(), value.name(), getDisplayName(value));
  }
}

