package dominio.actividades.Factory;

import dominio.actividades.*;
import dominio.actividades.enums.CategoriaProductoTranportado;
import dominio.actividades.enums.MedioDeTransporteLogistica;
import dominio.actividades.enums.Periodicidad;
import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class LogisticaProductosResiduosFactory extends Factory {

  @Override
  public Actividad crearActividad(Map<String, String> propiedades, List<FactorEmision> factoresEmision,
                                  List<Combustible> combustibles) {
    validarPropiedades(propiedades);

    Periodicidad periodicidad = Periodicidad.valueOf(propiedades.get(Constantes.PERIODICIDAD));
    String periodo = propiedades.get(Constantes.PERIODO);
    CategoriaProductoTranportado productoTransportado = CategoriaProductoTranportado.valueOf(propiedades.get("PRODUCTO_TRANSPORTADO"));
    MedioDeTransporteLogistica medioDeTransporteLogistica = MedioDeTransporteLogistica.valueOf(propiedades.get("MEDIO_TRANSPORTE"));
    Double distanciaMedia = Double.parseDouble(propiedades.get("DISTANCIA_MEDIA"));
    Double peso = Double.parseDouble(propiedades.get("PESO_TOTAL"));

    FactorEmision fe = getFactorEmision(factoresEmision, TipoActividad.LOGISTICA_PRODUCTOS_RESIDUOS, null);

    return new LogisticaProductosResiduos(periodicidad, LocalDate.parse(periodo), distanciaMedia, peso, medioDeTransporteLogistica, productoTransportado, fe);
  }

  @Override
  public String nombre() {
    return Constantes.LOGISTICA_PRODUCTOS_RESIDUOS;
  }

  private void validarPropiedades(Map<String, String> propiedades) {
    if (!Constantes.LOGISTICA_PRODUCTOS_TRANSPORTADOS_SOPORTADOS.contains(propiedades.get("PRODUCTO_TRANSPORTADO"))
      || !Constantes.LOGISTICA_MEDIOS_TRANSPORTE_SOPORTADOS.contains(propiedades.get("MEDIO_TRANSPORTE"))
    ) {
      throw new ParseErrorException();
    }
  }
}
