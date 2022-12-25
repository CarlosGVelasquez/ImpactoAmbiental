package huellaDeCarbono;

import dominio.actividades.*;
import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.Factory.FactoryFE;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;

public class CalculoHuellaActividades extends BaseTestHuella{
  
  @Test
  @DisplayName("Cálculo de HC para Actividad Combustión Fija - Diesel")
  public void calculoHcActividadCombustionFijaDiesel() {
    // Combustión fija diesel definida en el base test con valor 250
    Actividad combustionFijaDiesel = this.combustionFijaDiesel;
    Assertions.assertEquals(250.00, combustionFijaDiesel.huellaDeCarbono());
  }

  @Test
  @DisplayName("Cálculo de HC para Actividad Combustión Movil - Diesel")
  public void calculoHcActividadCombustionMovilDiesel() {
    // Combustión móvil diesel definida en el base test con valor 300
    Actividad combustionMovilDiesel = this.combustionMovilDiesel;
    Assertions.assertEquals(600.00, combustionMovilDiesel.huellaDeCarbono());
  }

  @Test
  @DisplayName("Cálculo de HC para Actividad Combustión Movil - Nafta")
  public void calculoHcActividadCombustionMovilNafta() {
    // Combustión móvil nafta definida en el base test con valor 25
    Actividad combustionMovilNafta = this.combustionMovilNafta;
    Assertions.assertEquals(25.00, combustionMovilNafta.huellaDeCarbono());
  }

  @Test
  @DisplayName("Cálculo de HC para Actividad Electricidad")
  public void calculoHcActividadElectricidad() {
    // Actividad electricdad definida en el base test con valor 300
    Actividad electricidad = this.electricidadAdqCons;
    Assertions.assertEquals(300.00, electricidad.huellaDeCarbono());
  }

  @Test
  @DisplayName("Cálculo de HC para Actividad Logística")
  public void calculoHcActividadLogistica() {
    // Actividad logística definida en el base test con valor 200000
    Actividad logistica = this.logistica;
    Assertions.assertEquals(200000.00, logistica.huellaDeCarbono());
  }
}
