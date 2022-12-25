package parseExcel;

import dominio.actividades.*;
import huellaDeCarbono.BaseTestHuella;

import dominio.actividades.Factory.CreadorActividad;
import dominio.actividades.Factory.ParseErrorException;
import infraestructura.repositorios.interfaces.IRepositorioCombustible;
import infraestructura.repositorios.interfaces.IRepositorioFactorEmision;
import org.junit.jupiter.api.*;
import aplicacion.parseExcel.ParseExcel;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestParseExcel extends BaseTestHuella {
  private final IRepositorioFactorEmision mockRepoFactorEmision = mock(IRepositorioFactorEmision.class);
  private final IRepositorioCombustible mockRepoCombustible = mock(IRepositorioCombustible.class);

  @BeforeEach
  public void init() {
    when(mockRepoCombustible.getCombustibles()).thenReturn(combustibles);
    when(mockRepoFactorEmision.getFactoresEmision()).thenReturn(factoresEmisionActividades);
  }


  @DisplayName("Se abre una archivo guardado en disco")
  @Test
  public void seAbreExcelExitosamente() {
    ParseExcel parseExcel = new ParseExcel();
    Map<Integer, List<String>> data = parseExcel.leerExcelDesdeRuta(basePath("apertura-archivo.xlsx"));

    Assertions.assertNotNull(data);
  }

  @DisplayName("Si el archivo que se quiere abrir no existe, lanza excepción")
  @Test
  public void noSeAbreExcelExitosamenteYLanzaExcepcion() {
    ParseExcel parseExcel = new ParseExcel();

    Exception exception = Assertions.assertThrows(ParseErrorException.class, ()
        -> parseExcel.leerExcelDesdeRuta(basePath("archivos_test_actividades/asdfg.xlsx")));

    System.out.println(exception.getMessage());
  }

  @DisplayName("Si la cabezera de la tabla no es válida, lanza excepción")
  @Test
  public void seLeeExcelConCabeceraInvalidaYLanzaExcepcion() {
    ParseExcel parseExcel = new ParseExcel();
    Map<Integer, List<String>> data = parseExcel.leerExcelDesdeRuta(basePath("cabecera-invalida.xlsx"));
    CreadorActividad creadorActividad = new CreadorActividad(mockRepoFactorEmision, mockRepoCombustible);
    Exception exception = Assertions.assertThrows(ParseErrorException.class, ()
        -> creadorActividad.crearActividades(data));

    System.out.println(exception.getMessage());
  }

  @DisplayName("Si se ingresa un combustible que no está en el repositorio, lanza excepción")
  @Test
  public void seLeeExcelConCombustibleNoValidoYLanzaExcepcion() {
    ParseExcel parseExcel = new ParseExcel();
    Map<Integer, List<String>> data = parseExcel.leerExcelDesdeRuta(basePath("combustible-invalido.xlsx"));
    CreadorActividad creadorActividad = new CreadorActividad(mockRepoFactorEmision, mockRepoCombustible);

    Exception exception = Assertions.assertThrows(ParseErrorException.class, ()
        -> creadorActividad.crearActividades(data));

    System.out.println(exception.getMessage());
  }

  @DisplayName("Si se ingresa una actividad que no es válida, lanza excepción")
  @Test
  public void seLeeExcelConActividadNoValidaYLanzaExcepcion() {
    ParseExcel parseExcel = new ParseExcel();
    Map<Integer, List<String>> data = parseExcel.leerExcelDesdeRuta(basePath("actividad-invalida.xlsx"));
    CreadorActividad creadorActividad = new CreadorActividad(mockRepoFactorEmision, mockRepoCombustible);

    Exception exception = Assertions.assertThrows(ParseErrorException.class, ()
        -> creadorActividad.crearActividades(data));

    System.out.println(exception.getMessage());
  }

  @DisplayName("Si se deja una columna vacía, lanza excepción")
  @Test
  public void seLeeExcelConColumnaVaciaYLanzaExcepcion() {
    ParseExcel parseExcel = new ParseExcel();
    Map<Integer, List<String>> data = parseExcel.leerExcelDesdeRuta(basePath("columna-vacia.xlsx"));
    CreadorActividad creadorActividad = new CreadorActividad(mockRepoFactorEmision, mockRepoCombustible);

    Exception exception = Assertions.assertThrows(ParseErrorException.class, ()
        -> creadorActividad.crearActividades(data));

    System.out.println(exception.getMessage());
  }

  @DisplayName("Se lee un archivo válido con varias actividades y se instancian las actividades")
  @Test
  public void seLeeExcelValidoYSeInstancianLasActividades() {
    ParseExcel parseExcel = new ParseExcel();
    Map<Integer, List<String>> data = parseExcel.leerExcelDesdeRuta(basePath("valido-varias-actividades.xlsx"));
    CreadorActividad creadorActividad = new CreadorActividad(mockRepoFactorEmision, mockRepoCombustible);

    List<Actividad> actividades = creadorActividad.crearActividades(data);

    Assertions.assertTrue(actividades.size() > 0);
    Assertions.assertTrue(actividades.stream().anyMatch(a -> a.getClass() == CombustionFija.class));
    Assertions.assertTrue(actividades.stream().anyMatch(a -> a.getClass() == CombustionMovil.class));
    Assertions.assertTrue(actividades.stream().anyMatch(a -> a.getClass() == ElectricaAdquiridaConsumida.class));
    Assertions.assertTrue(actividades.stream().anyMatch(a -> a.getClass() == LogisticaProductosResiduos.class));
    Assertions.assertTrue(actividades.stream().anyMatch(a -> a.getClass() == LogisticaProductosResiduos.class));
  }

  @DisplayName("Se lee un archivo válido de Combustion Fija y se instancia la actividad")
  @Test
  public void seLeeExcelValidoFijaYSeInstanciasLasActividades() {
    ParseExcel parseExcel = new ParseExcel();
    Map<Integer, List<String>> data = parseExcel.leerExcelDesdeRuta(basePath("valido-fija.xlsx"));
    CreadorActividad creadorActividad = new CreadorActividad(mockRepoFactorEmision, mockRepoCombustible);

    List<Actividad> actividades = creadorActividad.crearActividades(data);

    Assertions.assertEquals(1, actividades.size());
    Assertions.assertEquals(actividades.stream().findFirst().get().getClass(), CombustionFija.class);
  }

  @DisplayName("Se lee un archivo válido de Combustion Movil y se instancia la actividad")
  @Test
  public void seLeeExcelValidoMovilYSeInstanciasLasActividades() {
    ParseExcel parseExcel = new ParseExcel();
    Map<Integer, List<String>> data = parseExcel.leerExcelDesdeRuta(basePath("valido-movil.xlsx"));
    CreadorActividad creadorActividad = new CreadorActividad(mockRepoFactorEmision, mockRepoCombustible);

    List<Actividad> actividades = creadorActividad.crearActividades(data);

    Assertions.assertEquals(1, actividades.size());
    Assertions.assertEquals(actividades.stream().findFirst().get().getClass(), CombustionMovil.class);
  }

  @DisplayName("Se lee un archivo válido de Actividad Electricidad y se instancia la actividad")
  @Test
  public void seLeeExcelValidoElectricidadYSeInstanciasLasActividades() {
    ParseExcel parseExcel = new ParseExcel();
    Map<Integer, List<String>> data = parseExcel.leerExcelDesdeRuta(basePath("valido-electricidad.xlsx"));
    CreadorActividad creadorActividad = new CreadorActividad(mockRepoFactorEmision, mockRepoCombustible);

    List<Actividad> actividades = creadorActividad.crearActividades(data);

    Assertions.assertEquals(1, actividades.size());
    Assertions.assertEquals(actividades.stream().findFirst().get().getClass(), ElectricaAdquiridaConsumida.class);
  }

  @DisplayName("Se lee un archivo válido de Actividad Logistica y se instancia las actividads")
  @Test
  public void seLeeExcelValidoLogisticaYSeInstanciasLasActividades() {
    ParseExcel parseExcel = new ParseExcel();
    Map<Integer, List<String>> data = parseExcel.leerExcelDesdeRuta(basePath("valido-logistica.xlsx"));
    CreadorActividad creadorActividad = new CreadorActividad(mockRepoFactorEmision, mockRepoCombustible);

    List<Actividad> actividades = creadorActividad.crearActividades(data);

    Assertions.assertEquals(1, actividades.size());
    Assertions.assertEquals(actividades.stream().findFirst().get().getClass(), LogisticaProductosResiduos.class);
  }

  private String basePath(String path) {
    return "src/main/resources/archivos_test_actividades/" + path;
  }
}
