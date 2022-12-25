package dominio.actividades.Factory;

import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.FactorEmision;
import dominio.factoresEmision.Factory.FactoryFE;
import shared.helpers.Helpers;
import infraestructura.repositorios.RepositorioCombustible;
import dominio.actividades.Actividad;
import dominio.actividades.CombustionFija;
import dominio.factoresEmision.Combustible;
import infraestructura.repositorios.interfaces.IRepositorioFactorEmision;

import java.util.List;
import java.util.Map;

public class CombustionFijaFactory extends Factory {
  @Override
  public Actividad crearActividad(Map<String, String> propiedades, List<FactorEmision> factoresEmision,
                                  List<Combustible> combustibles) {
    Propiedades propiedadesMapeadas = new Propiedades(propiedades);
    validarPropiedades(propiedadesMapeadas);

    Combustible combustible = getCombustible(combustibles, propiedadesMapeadas);
    FactorEmision fe = getFactorEmision(factoresEmision, TipoActividad.COMBUSTION_FIJA, combustible);

    Double valor = Double.parseDouble(propiedadesMapeadas.getValor());

    return new CombustionFija(combustible, valor, propiedadesMapeadas.getPeriodicidad(),
        propiedadesMapeadas.getPeriodo(), fe);
  }

  @Override
  public String nombre() {
    return Constantes.COMBUSTION_FIJA;
  }

  public void validarPropiedades(Propiedades propiedades) {
    if (!Constantes.COMBUSTIBLES_SOPORTADOS_FIJA.contains(propiedades.getTipoConsumo())
        || Helpers.isNullOrEmpty(propiedades.getValor())
    ) {
      throw new ParseErrorException("El combustible " + propiedades.getTipoConsumo() + " no es válido para el tipo de actividad " +
          propiedades.getActividad());
    }
  }
}
