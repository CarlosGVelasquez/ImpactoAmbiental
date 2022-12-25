package dominio.actividades.Factory;

import infraestructura.repositorios.interfaces.IRepositorioCombustible;
import infraestructura.repositorios.interfaces.IRepositorioFactorEmision;
import shared.helpers.Helpers;
import dominio.actividades.Actividad;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreadorActividad {
  public CreadorActividad(IRepositorioFactorEmision repositorioFactorEmision, IRepositorioCombustible repositorioCombustible) {
    this.repositorioFactorEmision = repositorioFactorEmision;
    this.repositorioCombustible = repositorioCombustible;
  }

  private IRepositorioFactorEmision repositorioFactorEmision;
  private IRepositorioCombustible repositorioCombustible;

  public List<Actividad> crearActividades(Map<Integer, List<String>> dataExcel) {
    List<Actividad> actividades = new ArrayList<>();

    List<String> encabezado1 = dataExcel.get(0);
    List<String> encabezado2 = dataExcel.get(1);

    String actividad = Helpers.stringNormalize(encabezado1.get(0));
    String tipoConsumo = Helpers.stringNormalize(encabezado1.get(1));
    String periodo = Helpers.stringNormalize(encabezado1.get(3));
    String valor = Helpers.stringNormalize(encabezado2.get(0));
    String periodicidad = Helpers.stringNormalize(encabezado2.get(1));

    for (Integer i = 2; i < dataExcel.size(); i++) {
      List<String> row = dataExcel.get(i);

      if (row.size() != 5)
        throw new ParseErrorException("Hay columnas sin completar");

      if (Helpers.stringCompare(row.get(0), Constantes.LOGISTICA_PRODUCTOS_RESIDUOS)) {
        actividades.add(mapearLogistica(dataExcel, i));
        i = i + Constantes.LOGISTICA_REGISTROS_MAX - 1;
        continue;
      }

      Map<String, String> actividadMap = new HashMap<>();

      actividadMap.put(actividad, Helpers.stringNormalize(row.get(0)));
      actividadMap.put(tipoConsumo, Helpers.stringNormalize(row.get(1)));
      actividadMap.put(valor, Helpers.stringNormalize(row.get(2)));
      actividadMap.put(periodicidad, Helpers.stringNormalize(row.get(3)));
      actividadMap.put(periodo, Helpers.stringNormalize(row.get(4)));

      actividades.add(mapearActividad(actividadMap));
    }
    return actividades;
  }

  private Actividad mapearActividad(Map<String, String> propiedades) {
    Propiedades propiedadesMapeadas = new Propiedades(propiedades);
    Factory factory = FactoriesConcretos.buscarFactory(propiedadesMapeadas.getActividad());
    return factory.crearActividad(propiedades, this.repositorioFactorEmision.getFactoresEmision(), this.repositorioCombustible.getCombustibles());
  }

  private Actividad mapearLogistica(Map<Integer, List<String>> dataExcel, Integer index) {
    Map<String, String> actividadMap = new HashMap<>();

    List<String> firstRow = dataExcel.get(index);
    actividadMap.put(Constantes.PERIODICIDAD, firstRow.get(3));
    actividadMap.put(Constantes.PERIODO, firstRow.get(4));

    for(Integer i = index; i < index + Constantes.LOGISTICA_REGISTROS_MAX; i++) {
      List<String> row = dataExcel.get(i);

      if (Helpers.stringCompare(row.get(1), Constantes.PRODUCTO_TRANSPORTADO)) {
        actividadMap.put(Constantes.PRODUCTO_TRANSPORTADO, row.get(2));
      }

      if (Helpers.stringCompare(row.get(1), Constantes.MEDIO_TRANSPORTE)) {
        actividadMap.put(Constantes.MEDIO_TRANSPORTE, row.get(2));
      }

      if (Helpers.stringCompare(row.get(1), Constantes.DISTANCIA_MEDIA)) {
        actividadMap.put(Constantes.DISTANCIA_MEDIA, row.get(2));
      }

      if (Helpers.stringCompare(row.get(1), Constantes.PESO_TOTAL)) {
        actividadMap.put(Constantes.PESO_TOTAL, row.get(2));
      }

      actividadMap.put(Constantes.ACTIVIDAD, row.get(0));
    }
    Factory factory = FactoriesConcretos.buscarFactory(actividadMap.get(Constantes.ACTIVIDAD));

    return factory.crearActividad(actividadMap, this.repositorioFactorEmision.getFactoresEmision(), this.repositorioCombustible.getCombustibles());
  }
}
