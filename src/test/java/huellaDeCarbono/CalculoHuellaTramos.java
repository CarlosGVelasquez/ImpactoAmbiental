package huellaDeCarbono;

import dominio.factoresEmision.FactorEmision;
import dominio.factoresEmision.Factory.FactoryFE;
import dominio.mediosTransportes.*;
import dominio.ubicacion.Direccion;
import dominio.ubicacion.Localidad;
import dominio.viajes.Tramo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import static org.mockito.Mockito.*;

public class CalculoHuellaTramos extends BaseTestHuella {
  private final TipoServContratado tipoTaxi = new TipoServContratado("Taxi", TipoVehiculo.AUTO, nafta);
  private final TipoServContratado tipoCombi = new TipoServContratado("Combi", TipoVehiculo.CAMIONETA, diesel);

  private final Direccion dirParqueAvellaneda = new Direccion("Av Directorio", "2000", new Localidad(5, ""));
  private final Direccion dirParqueCentenario = new Direccion("Av Diaz Velez", "5000", new Localidad(5, ""));
  private final Direccion dirPlazaItalia = new Direccion("Uriarte", "2500", new Localidad(5, ""));

  private MedioTransporte taxi = new ServicioContratado(tipoTaxi, feAutoNafta);
  private MedioTransporte combi = new ServicioContratado(tipoCombi, feCamionetaDiesel);

  @BeforeEach
  public void Init() {
    //El adapter de distancia siempre devuelve 100
    diesel.setId(1);
    nafta.setId(2);
    electricidad.setId(3);
    gnc.setId(4);
    when(adapterDistancia.calcularDistancia(any(), any())).thenReturn(100.00);
  }

  @Test
  @DisplayName("Se calcula la huella de un tramo en colectivo a diesel")
  public void hcTramoEnColectivoDiesel() {
    Parada parqueAvellaneda = new Parada("Parque Avellaneda", dirParqueAvellaneda, 10.0, 0.0);
    Parada parqueCentenario = new Parada("Parque Centenario", dirParqueCentenario, 30.0, 10.0);
    Parada palermo = new Parada("Palermo", dirPlazaItalia, 0.0, 30.0);
    Linea linea55 = new Linea("Línea 55", TipoTransportePublico.COLECTIVO, diesel, Arrays.asList(parqueAvellaneda, parqueCentenario, palermo));

    FactorEmision fe = FactoryFE.getFe(linea55.getCombustible(), null, null, linea55.getTipoTransportePublico(), factoresEmisionMediosTransporte);

    MedioTransporte transporte = new TransportePublico(linea55, parqueAvellaneda, palermo, fe);

    Tramo tramo = new Tramo(null, null, "Viaje desde Parque Avellaneda hasta Palermo", transporte);

    Assertions.assertEquals(120, tramo.calcularHC(adapterDistancia));
  }

  @Test
  @DisplayName("Se calcula la huella de un tramo en colectivo a gnc")
  public void hcTramoEnColectivoGNC() {
    Parada parqueAvellaneda = new Parada("Parque Avellaneda", dirParqueAvellaneda, 10.0, 0.0);
    Parada parqueCentenario = new Parada("Parque Centenario", dirParqueCentenario, 30.0, 10.0);
    Parada palermo = new Parada("Palermo", dirPlazaItalia, 0.0 ,30.0);

    Linea linea55 = new Linea("Línea 55", TipoTransportePublico.COLECTIVO, gnc, Arrays.asList(parqueAvellaneda, parqueCentenario, palermo));
    MedioTransporte transporte = new TransportePublico(linea55, parqueAvellaneda, palermo, feColectivoGNC);

    Tramo tramo = new Tramo(null, null, "Viaje desde Parque Avellaneda hasta Palermo", transporte);

    Assertions.assertEquals(80, tramo.calcularHC(adapterDistancia));
  }

  @Test
  @DisplayName("Se calcula la huella de un tramo en auto a nafta")
  public void hcTramoEnAutoNafta() {
    MedioTransporte vehiculoParticular = new VehiculoParticular(nafta, TipoVehiculo.AUTO, feAutoNafta);

    // No importa que direcciones se coloquen, porque el mock adapter siempre devuelve 100 como distancia
    Tramo tramo = new Tramo(dirParqueAvellaneda, dirParqueCentenario, "", vehiculoParticular);

    // El FE de auto a nafta se definió como 0.5 así que la HC debe ser 100 * 0.5 = 50
    Assertions.assertEquals(50, tramo.calcularHC(adapterDistancia));
  }

  @Test
  @DisplayName("Se calcula la huella de un tramo en auto a GNC")
  public void hcTramoEnAutoGNC() {
    MedioTransporte vehiculoParticular = new VehiculoParticular(gnc, TipoVehiculo.AUTO, feAutoGNC);

    Tramo tramo = new Tramo(dirParqueAvellaneda, dirParqueCentenario, "", vehiculoParticular);

    Assertions.assertEquals(30, tramo.calcularHC(adapterDistancia));
    // El FE de auto a GNC se definió como 0.3 así que la HC debe ser 100 * 0.3 = 30
  }

  @Test
  @DisplayName("Se calcula la huella de un tramo en auto a diesel")
  public void hcTramoEnAutoDiesel() {
    MedioTransporte vehiculoParticular = new VehiculoParticular(diesel, TipoVehiculo.AUTO, feAutoDiesel);

    Tramo tramo = new Tramo(dirParqueAvellaneda, dirParqueCentenario, "", vehiculoParticular);

    // El FE de auto a diesel se definió como 0.8 así que la HC debe ser 100 * 0.8 = 80
    Assertions.assertEquals(80, tramo.calcularHC(adapterDistancia));
  }

  @Test
  @DisplayName("Se calcula la huella de un tramo en moto a nafta")
  public void hcTramoEnMotoNafta() {
    MedioTransporte vehiculoParticular = new VehiculoParticular(nafta, TipoVehiculo.MOTO, feMotoNafta);

    Tramo tramo = new Tramo(dirParqueAvellaneda, dirParqueCentenario, "", vehiculoParticular);

    // El FE de moto a nafta se definió como 0.2 así que la HC debe ser 100 * 0.2 = 20
    Assertions.assertEquals(20, tramo.calcularHC(adapterDistancia));
  }

  @Test
  @DisplayName("Se calcula la huella de un tramo en camioneta a diesel")
  public void hcTramoEnCamionetaDiesel() {
    MedioTransporte vehiculoParticular = new VehiculoParticular(diesel, TipoVehiculo.CAMIONETA, feCamionetaDiesel);

    Tramo tramo = new Tramo(dirParqueAvellaneda, dirParqueCentenario, "", vehiculoParticular);

    // El FE de camioneta a diesel se definió como 0.7 así que la HC debe ser 100 * 0.7 = 70
    Assertions.assertEquals(70, tramo.calcularHC(adapterDistancia));
  }

  @Test
  @DisplayName("Se calcula la huella de un tramo en servicio contratado taxi (auto) a nafta")
  public void hcTramoEnServicioContratadoTaxiNafta() {

    Tramo tramo = new Tramo(dirParqueAvellaneda, dirParqueCentenario, "", taxi);

    // El FE de auto (taxi) a nafta se definió como 0.5 así que la HC debe ser 100 * 0.5 = 50
    Assertions.assertEquals(50, tramo.calcularHC(adapterDistancia));
  }

  @Test
  @DisplayName("Se calcula la huella de un tramo en servicio contratado combi (camioneta) a diesel")
  public void hcTramoEnServicioContratadoCombiDiesel() {

    Tramo tramo = new Tramo(dirParqueAvellaneda, dirParqueCentenario, "", combi);

    // El FE de camioneta a diesel (combi) se definió como 0.70 así que la HC debe ser 100 * 0.7 = 70
    Assertions.assertEquals(70, tramo.calcularHC(adapterDistancia));
  }
}
