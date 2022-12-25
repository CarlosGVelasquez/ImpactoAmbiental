package dominio.actividades.Factory;

import shared.helpers.Helpers;
import java.util.Arrays;
import java.util.List;

public class FactoriesConcretos {
  private static List<Factory> factoriesConcretos = Arrays.asList(
    new CombustionFijaFactory(),
    new CombustionMovilFactory(),
    new ElectricaAdquiridaConsumidaFactory(),
    new LogisticaProductosResiduosFactory()
  );

  public static Factory buscarFactory(String nombreActividad) {
    return factoriesConcretos.stream().filter(f -> Helpers.stringCompare(nombreActividad, f.nombre()))
        .findFirst().orElseThrow(() -> new ParseErrorException("El tipo de actividad '" + nombreActividad + "' no es válido"));
  }
}
