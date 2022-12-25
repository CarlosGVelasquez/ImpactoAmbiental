package dominio.factoresEmision.Factory;

import dominio.actividades.Actividad;
import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import dominio.mediosTransportes.*;
import shared.helpers.Helpers;

import java.util.List;

public class FactoryFE {
// Para usar al momento de instanciar nuevos medios de transporte
  public static FactorEmision getFe(Combustible combustible, TipoActividad actividad, TipoVehiculo tipoVehiculo,
        TipoTransportePublico tipoTransportePublico, List<FactorEmision> factoresEmision) {
    return factoresEmision.stream().filter(fe ->
        Helpers.equals(fe.getCombustible(), combustible)    &&
        Helpers.equals(fe.getTipoActividad(), actividad)    &&
        Helpers.equals(fe.getTipoVehiculo(), tipoVehiculo)  &&
        Helpers.equals(fe.getTipoTransportePublico(), tipoTransportePublico)
    ).findFirst().orElseThrow(() -> new FactorEmisionNotFoundException(combustible, actividad, tipoVehiculo, tipoTransportePublico));
  }
}
