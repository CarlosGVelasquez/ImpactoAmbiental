package dominio.factoresEmision.Factory;

import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.Combustible;
import dominio.mediosTransportes.TipoTransportePublico;
import dominio.mediosTransportes.TipoVehiculo;

public class FactorEmisionNotFoundException extends RuntimeException {
  public FactorEmisionNotFoundException(Combustible combustible, TipoActividad actividad, TipoVehiculo vehiculo,
                                        TipoTransportePublico transportePublico) {
    String tipo = actividad != null
        ? "la actividad " + actividad.name() : vehiculo != null
        ? "el vehiculo " + vehiculo.name()  : "el medio de transporte " + transportePublico.name();

    String msg = "No se pudo encontrar un factor de emisión para el combustible " + combustible.getNombre()
       + " y " + tipo + ". Contacte a un administrador";
    throw new FactorEmisionNotFoundException(msg);
  }

  public FactorEmisionNotFoundException(String msg) {
    super(msg);
  }
}
