package huellaDeCarbono;
import aplicacion.servicios.calculadoraHc.CalculadoraHc;
import aplicacion.servicios.ServicioTramos;
import dominio.actividades.Actividad;
import dominio.actividades.CombustionMovil;
import dominio.actividades.enums.Periodicidad;
import dominio.actividades.enums.TipoActividad;
import dominio.mediosTransportes.TipoVehiculo;
import dominio.mediosTransportes.VehiculoParticular;
import dominio.organizacion.Miembro;
import dominio.organizacion.Organizacion;
import dominio.ubicacion.Direccion;
import dominio.ubicacion.Localidad;
import dominio.viajes.InvitacionTramo;
import dominio.viajes.Tramo;
import dominio.viajes.Trayecto;
import infraestructura.repositorios.interfaces.IRepositorioInvitacionTramos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculoHuellaOrganizacion extends BaseTestHuella {

  @BeforeEach
  public void Init() {
    //El adapter de distancia siempre devuelve 100
    when(adapterDistancia.calcularDistancia(any(), any())).thenReturn(100.00);
  }

  @Test
  @DisplayName("Se calcula la huella para una organización con todos los tipos de actividad sin tramos, en la fecha actual")
  public void huellaOrganizacionTodasLasActividadesSinTramosActual() {
    Organizacion organizacion = new Organizacion();

    // La huella actual sólo calcula las actividades ANUALES:
    // HC = 0 + 250 + 0 + 600 = 850
    organizacion.agregarActividad(Arrays.asList(this.electricidadAdqCons,
        this.combustionFijaDiesel, this.logistica, this.combustionMovilDiesel));

    CalculadoraHc calculadora = new CalculadoraHc(null, adapterDistancia);

    Assertions.assertEquals(850, calculadora.hcOrganizacionActual(organizacion));
  }

  @Test
  @DisplayName("Se calcula la huella para una organización mensual de actividad sin tramos, en la fecha actual")
  public void huellaOrganizacionSinTramosMensual() {
    Organizacion organizacion = new Organizacion();

    //Las mensuales son electricidad y logísitca => HC = 300 + 0 + 200000 + 0 = 200300
    organizacion.agregarActividad(Arrays.asList(this.electricidadAdqCons,
        this.combustionFijaDiesel, this.logistica, this.combustionMovilDiesel));

    CalculadoraHc calculadora = new CalculadoraHc(null, adapterDistancia);

    Assertions.assertEquals(200300, calculadora.hcOrganizacionEnPeriodo(organizacion, LocalDate.now(), Periodicidad.MENSUAL));
  }

  @Test
  @DisplayName("Se calcula la huella para una organización anual de actividad sin tramos, en la fecha actual")
  public void huellaOrganizacionSinTramosAnual() {
    Organizacion organizacion = new Organizacion();

    //Las anuales son fija diesel y movil diesel => HC = 0 + 250 + 0 + 600 = 850
    organizacion.agregarActividad(Arrays.asList(this.electricidadAdqCons,
        this.combustionFijaDiesel, this.logistica, this.combustionMovilDiesel));

    CalculadoraHc calculadora = new CalculadoraHc(null, adapterDistancia);

    Assertions.assertEquals(850, calculadora.hcOrganizacionEnPeriodo(organizacion, LocalDate.now(), Periodicidad.ANUAL));
  }

  @Test
  @DisplayName("Se calcula la huella para una organización mensual de actividad sin tramos, en mayo")
  public void huellaOrganizacionSinTramosMayoMensual() {
    Organizacion organizacion = new Organizacion();
    Actividad actividadMayoMensual =
        new CombustionMovil(diesel, 10.0, Periodicidad.MENSUAL, LocalDate.of(2022, 5, 1), feDieselCombustionMovil);
    //La única mensual de mayo es actividadMayoMensual => HC = 10 * feDieselCombustionMovil
    // => HC = 10 * 3 => HC = 30
    organizacion.agregarActividad(Arrays.asList(this.electricidadAdqCons,
        this.combustionFijaDiesel, this.logistica, this.combustionMovilDiesel, actividadMayoMensual));

    CalculadoraHc calculadora = new CalculadoraHc(null, adapterDistancia);

    Assertions.assertEquals(30, calculadora.hcOrganizacionEnPeriodo(organizacion, LocalDate.of(2022, 5, 1), Periodicidad.MENSUAL));
  }

  @Test
  @DisplayName("Se calcula la huella para una organización anual de actividad con tramos compartidos, en la fecha actual")
  public void huellaOrganizacionConTramosCompartidosAnual() {
    when(mockRepoFactorEmision.getFeByCombustibleAndVehiculo(diesel, TipoVehiculo.AUTO)).thenReturn(feAutoDiesel);
    when(mockRepoFactorEmision.getFeByCombustibleAndVehiculo(nafta, TipoVehiculo.AUTO)).thenReturn(feAutoNafta);
    when(mockRepoFactorEmision.getFeByCombustibleAndVehiculo(diesel, TipoVehiculo.CAMIONETA)).thenReturn(feCamionetaDiesel);

    Organizacion organizacion = new Organizacion();

    //Las anuales son fija diesel y movil diesel => HC = 0 + 250 + 0 + 600 = 850
    organizacion.agregarActividad(Arrays.asList(this.electricidadAdqCons,
        this.combustionFijaDiesel, this.logistica, this.combustionMovilDiesel));

    Miembro miembro1 = new Miembro(null, organizacion, null, true);
    Trayecto trayecto1 = new Trayecto(null, null);
    miembro1.setTrayecto(trayecto1);

    Miembro miembro2 = new Miembro(null, organizacion, null, true);
    Trayecto trayecto2 = new Trayecto(new Direccion("","", new Localidad(3, "")), new Direccion("","", new Localidad(3, "")));
    miembro2.setTrayecto(trayecto2);

    //HC de este tramo = feDieselAuto * 100 => HC = 0.8 * 100 = 80
    Tramo tramoMiembro1 = new Tramo(null, null, "", new VehiculoParticular(diesel, TipoVehiculo.AUTO, feAutoDiesel));

    //HC de este tramo = feNaftalAuto * 100 => HC = 0.5 * 100 = 50. Se debe contar una sola vez
    Tramo tramoCompartido = new Tramo(null, null, "", new VehiculoParticular(nafta, TipoVehiculo.AUTO, feAutoNafta));

    //HC de este tramo = feCamionetaDiesel * 100 => HC = 0.7 * 100 = 70
    Tramo tramoMiembro2 = new Tramo(null, null, "", new VehiculoParticular(diesel, TipoVehiculo.CAMIONETA, feCamionetaDiesel));

    miembro1.getTrayecto().addTramo(tramoMiembro1);
    miembro1.getTrayecto().addTramo(tramoCompartido);
    miembro2.getTrayecto().addTramo(tramoMiembro2);

    organizacion.addMiembro(miembro1);
    organizacion.addMiembro(miembro2);

    ServicioTramos servicioTramos = new ServicioTramos(mock(IRepositorioInvitacionTramos.class));
    InvitacionTramo invitacion = servicioTramos.compartirTramoEntre(miembro1, miembro2, tramoCompartido);
    invitacion.aceptar();

    CalculadoraHc calculadora = new CalculadoraHc(null, adapterDistancia);

    Double sumaTramosIndividualesMiembro1 = miembro1.getTrayecto()
        .getTramo().stream().mapToDouble(t -> t.calcularHC(adapterDistancia)).sum();

    Double sumaTramosIndividualesMiembro2 = miembro2.getTrayecto()
        .getTramo().stream().mapToDouble(t -> t.calcularHC(adapterDistancia)).sum();

    // tramoMiembro1 + tramoMiembro2 + tramoCompartido = 80 + 70 + 50 = 200
    // 850 de las actividades
    Assertions.assertEquals(200 + 850, calculadora.hcOrganizacionEnPeriodo(organizacion, LocalDate.now(), Periodicidad.ANUAL));

    // tramoMiembro1 + tramoMiembro2 + 2 * tramoCompartido = 80 + 70 + 2 * 50 = 250
    Assertions.assertEquals(250, sumaTramosIndividualesMiembro1 + sumaTramosIndividualesMiembro2);
  }

  @Test
  @DisplayName("Se calcula la huella para una organización anual de actividad con no compartidos, en la fecha actual")
  public void huellaOrganizacionConTramosSinCompartirAnual() {
    // TODO: HACER TEST
  }
}
