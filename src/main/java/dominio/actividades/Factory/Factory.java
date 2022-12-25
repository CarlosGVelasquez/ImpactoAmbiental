package dominio.actividades.Factory;

import dominio.actividades.Actividad;
import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import dominio.factoresEmision.Factory.FactoryFE;
import shared.helpers.Helpers;

import java.util.List;
import java.util.Map;

public abstract class Factory {
  protected Combustible getCombustible(List<Combustible> combustibles, Propiedades propiedadesMapeadas) {
    return combustibles.stream().filter(c -> Helpers.stringCompare(c.getNombre(), propiedadesMapeadas.getTipoConsumo()))
        .findFirst().orElseThrow(() -> new CombustibleNotFoundException(propiedadesMapeadas.getTipoConsumo()));
  }

  protected FactorEmision getFactorEmision(List<FactorEmision> factoresEmision, TipoActividad tipoActividad, Combustible combustible) {
    return FactoryFE.getFe(combustible, tipoActividad, null, null, factoresEmision);
  }

  public abstract Actividad crearActividad(Map<String, String> propiedades, List<FactorEmision> factoresEmision,
                                           List<Combustible> combustibles);
  public abstract String nombre();
}
