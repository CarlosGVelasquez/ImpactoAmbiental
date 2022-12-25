package dominio.actividades.Factory;

import dominio.actividades.enums.CategoriaProductoTranportado;
import dominio.actividades.enums.MedioDeTransporteLogistica;
import java.util.Arrays;
import java.util.List;

public final class Constantes {
  /* ---- Encabezado de la tabla ------ */
  public static final String ACTIVIDAD = "ACTIVIDAD";
  public static final String TIPO_CONSUMO = "TIPO DE CONSUMO";
  public static final String VALOR = "VALOR";
  public static final String PERIODICIDAD = "PERIODICIDAD";
  public static final String PERIODO = "PERIODO DE IMPUTACION";

  /* ---- Valores posibles de Tipos de Activdad ------ */
  public static final String COMBUSTION_FIJA = "COMBUSTION FIJA";
  public static final String COMBUSTION_MOVIL = "COMBUSTION MOVIL";
  public static final String ELECTRICIDAD_ADQUIRIDA_CONSUMIDA = "ELECTRICIDAD ADQUIRIDA Y CONSUMIDA";
  public static final String LOGISTICA_PRODUCTOS_RESIDUOS = "LOGISTICA DE PRODUCTOS Y RESIDUOS";

  /* ---- Valores posibles de Tipos de Combustión por Tipo de Actividad ------ */
  public static final List<String> COMBUSTIBLES_SOPORTADOS_FIJA = Arrays.asList("GAS NATURAL", "DIESEL/GASOIL",
      "KEROSENE", "FUEL OIL", "NAFTA", "CARBON", "CARBON DE LEÑA", "LENA");
  public static final List<String> COMBUSTIBLES_SOPORTADOS_MOVIL= Arrays.asList("DIESEL/GASOIL", "NAFTA", "GNC");
  public static final List<String> COMBUSTIBLES_SOPORTADOS_ELECTRICIDAD= Arrays.asList("ELECTRICIDAD");

  /* ---- Logística de productos y residuos  ------ */
  public static final List<String> LOGISTICA_PRODUCTOS_TRANSPORTADOS_SOPORTADOS = Arrays.asList(
      CategoriaProductoTranportado.MATERIA_PRIMA.name(),
      CategoriaProductoTranportado.INSUMOS.name(),
      CategoriaProductoTranportado.PRODUCTOS_VENDIDOS.name(),
      CategoriaProductoTranportado.RESIDUOS.name());

  public static final List<String> LOGISTICA_MEDIOS_TRANSPORTE_SOPORTADOS =
         Arrays.asList(MedioDeTransporteLogistica.CAMION.name(), MedioDeTransporteLogistica.UTILITARIO_LIVIANO.name());

  public static final Integer LOGISTICA_REGISTROS_MAX = 4;

  public static final String PRODUCTO_TRANSPORTADO = "PRODUCTO_TRANSPORTADO";
  public static final String MEDIO_TRANSPORTE = "MEDIO_TRANSPORTE";
  public static final String DISTANCIA_MEDIA = "DISTANCIA_MEDIA";
  public static final String PESO_TOTAL = "PESO_TOTAL";

}
