package huellaDeCarbono;

import dominio.actividades.*;
import dominio.actividades.enums.Periodicidad;
import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.*;
import dominio.mediosTransportes.TipoTransportePublico;
import dominio.mediosTransportes.TipoVehiculo;
import infraestructura.APICalculoDistancia.adapter.AdapterDistancia;
import infraestructura.repositorios.interfaces.IRepositorioFactorEmision;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;

public class BaseTestHuella {

  /* Mocks - Adapter Distancia y Repo Factor Emisión*/
  protected final IRepositorioFactorEmision mockRepoFactorEmision = mock(IRepositorioFactorEmision.class);
  protected final AdapterDistancia adapterDistancia = mock(AdapterDistancia.class);

  /* Combustibles */
  protected final Combustible diesel = new Combustible("Diesel") {{setId(1);}};
  protected final Combustible nafta = new Combustible("Nafta") {{setId(2);}};
  protected final Combustible electricidad = new Combustible("Electricidad") {{setId(3);}};
  protected final Combustible gnc = new Combustible("GNC") {{setId(4);}};
  protected final Combustible kerosene = new Combustible("Kerosene") {{setId(5);}};
  protected final Combustible lena = new Combustible("Lena") {{setId(6);}};
  protected final List<Combustible> combustibles = Arrays.asList(diesel, nafta, electricidad, gnc, kerosene, lena);

  /* Factores de Emisión Actividades */
  protected final FactorEmision feDieselCombustionFija = new FactorEmision(TipoActividad.COMBUSTION_FIJA, diesel, 5.0, TipoFactorEmision.ACTIVIDAD);
  protected final FactorEmision feDieselCombustionMovil = new FactorEmision(TipoActividad.COMBUSTION_MOVIL, diesel, 3.0, TipoFactorEmision.ACTIVIDAD);
  protected final FactorEmision feNaftaCombustionMovil = new FactorEmision(TipoActividad.COMBUSTION_MOVIL, nafta, 2.5, TipoFactorEmision.ACTIVIDAD);
  protected final FactorEmision feElectricidad = new FactorEmision(TipoActividad.ELECTRICA_ADQUIRIDA_CONSUMIDA, electricidad, 1.5, TipoFactorEmision.ACTIVIDAD);
  protected final FactorEmision feLogistica = new FactorEmision(TipoActividad.LOGISTICA_PRODUCTOS_RESIDUOS, null, 8.0, TipoFactorEmision.ACTIVIDAD);
  protected final FactorEmision feGNCCombustionMovil = new FactorEmision(TipoActividad.COMBUSTION_MOVIL, gnc, 8.0, TipoFactorEmision.ACTIVIDAD);
  protected final FactorEmision feNaftaCombustionFija = new FactorEmision(TipoActividad.COMBUSTION_FIJA, nafta, 2.5, TipoFactorEmision.ACTIVIDAD);
  protected final FactorEmision feKeroseneCombustionFija = new FactorEmision(TipoActividad.COMBUSTION_FIJA, kerosene, 8.0, TipoFactorEmision.ACTIVIDAD);

  protected final List<FactorEmision> factoresEmisionActividades = Arrays.asList(feDieselCombustionFija, feDieselCombustionMovil,
      feNaftaCombustionMovil, feElectricidad, feLogistica, feGNCCombustionMovil, feNaftaCombustionFija, feKeroseneCombustionFija);


  /* Factores de Emisión Tranporte Público y Vehículos Particulares*/
  protected final FactorEmision feColectivoDiesel = new FactorEmision(TipoTransportePublico.COLECTIVO, diesel, 3.0, TipoFactorEmision.TRANSPORTE_PUBLICO);
  protected final FactorEmision feColectivoGNC = new FactorEmision(TipoTransportePublico.COLECTIVO, gnc, 2.0, TipoFactorEmision.TRANSPORTE_PUBLICO);
  protected final FactorEmision feAutoNafta = new FactorEmision(TipoVehiculo.AUTO, nafta, 0.5, TipoFactorEmision.VEHICULO);
  protected final FactorEmision feAutoGNC = new FactorEmision(TipoVehiculo.AUTO, gnc, 0.3, TipoFactorEmision.VEHICULO);
  protected final FactorEmision feAutoDiesel = new FactorEmision(TipoVehiculo.AUTO, diesel, 0.8, TipoFactorEmision.VEHICULO);
  protected final FactorEmision feMotoNafta = new FactorEmision(TipoVehiculo.MOTO, nafta, 0.2, TipoFactorEmision.VEHICULO);
  protected final FactorEmision feCamionetaDiesel = new FactorEmision(TipoVehiculo.CAMIONETA, diesel, 0.7, TipoFactorEmision.VEHICULO);
  protected final List<FactorEmision> factoresEmisionMediosTransporte = Arrays.asList(feColectivoDiesel, feColectivoGNC,
      feAutoNafta, feAutoGNC, feAutoDiesel, feMotoNafta, feCamionetaDiesel);
  /* Actividades */

  // HC = 50*feDieselFija => HC = 50 * 5 => HC = 250
  Actividad combustionFijaDiesel = new CombustionFija(diesel, 50.0, Periodicidad.ANUAL, LocalDate.now(), feDieselCombustionFija);

  //HC = 100 * feDieseMovil => HC = 200 * 3 => HC = 600
  Actividad combustionMovilDiesel = new CombustionMovil(diesel, 200.0, Periodicidad.ANUAL, LocalDate.now(), feDieselCombustionMovil);

  //HC = 10 * feNaftaMovil => HC = 10 * 2.5 => HC = 25
  Actividad combustionMovilNafta = new CombustionMovil(nafta, 10.0, Periodicidad.ANUAL, LocalDate.now(), feNaftaCombustionMovil);

  //HC = 200 * feElectricidad => HC = 200 * 1.5 => HC = 300
  Actividad electricidadAdqCons = new ElectricaAdquiridaConsumida(electricidad, 200.00, Periodicidad.MENSUAL, LocalDate.now(), feElectricidad);

  //HC = dist * peso * k * feLogisitca => HC = 50 * 100 * 5 * 8 => HC = 200000
  Actividad logistica = new LogisticaProductosResiduos(Periodicidad.MENSUAL, LocalDate.now(), 50.0, 100.0, null, null, feLogistica);
}
