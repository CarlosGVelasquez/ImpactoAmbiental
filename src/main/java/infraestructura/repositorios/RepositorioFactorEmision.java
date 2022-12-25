package infraestructura.repositorios;

import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import dominio.mediosTransportes.TipoTransportePublico;
import dominio.mediosTransportes.TipoVehiculo;
import dominio.viajes.Tramo;
import dominio.viajes.Trayecto;
import infraestructura.repositorios.interfaces.IRepositorioFactorEmision;

import java.util.List;

public class RepositorioFactorEmision extends Repository<FactorEmision> implements IRepositorioFactorEmision
{
  public RepositorioFactorEmision(Class<FactorEmision> type) {
    super(type);
  }


  @Override
  public List<FactorEmision> getFactoresEmision() {
    return this.buscarTodos();
  }

  @Override
  public FactorEmision getFeByCombustibleAndActividad(Combustible combustible, TipoActividad actividad) {
    return null;
  }

  @Override
  public FactorEmision getFeByCombustibleAndTransporte(Combustible combustible, TipoTransportePublico tipoTransportePublico) {
    return null;
  }

  @Override
  public FactorEmision getFeByCombustibleAndVehiculo(Combustible Combustible, TipoVehiculo tipoVehiculo) {
    return null;
  }

  @Override
  public FactorEmision getFeByActividad(TipoActividad actividad) {
    return null;
  }

}
