package presentacion.models.requests;

import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import dominio.factoresEmision.TipoFactorEmision;
import dominio.mediosTransportes.TipoTransportePublico;
import dominio.mediosTransportes.TipoVehiculo;
import lombok.Getter;
import shared.json.JsonRequired;

@Getter
public class CrearFactorEmisionModel {
  //@JsonRequired(message = "El nombre es requerido")
  private String nombre;
// @JsonRequired(message = "El tipo de factor de emisión es requerido")
  private TipoFactorEmision tipoFactorEmision;
  private TipoTransportePublico tipoTransportePublico;
  private TipoActividad tipoActividad;
  private TipoVehiculo tipoVehiculo;
//  @JsonRequired(message = "El combustible es requerido")
  private Integer combustibleId;
  private Double valor;

}

