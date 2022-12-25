package infraestructura.repositorios.interfaces;

import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import dominio.mediosTransportes.TipoTransportePublico;
import dominio.mediosTransportes.TipoVehiculo;

import java.util.List;

public interface IRepositorioFactorEmision {
  public List<FactorEmision> getFactoresEmision();
  public FactorEmision getFeByCombustibleAndActividad(Combustible combustible, TipoActividad actividad);
  public FactorEmision getFeByCombustibleAndTransporte(Combustible Combustible, TipoTransportePublico tipoTransportePublico);
  public FactorEmision getFeByCombustibleAndVehiculo(Combustible Combustible, TipoVehiculo tipoVehiculo);
  public FactorEmision getFeByActividad(TipoActividad actividad);
}
