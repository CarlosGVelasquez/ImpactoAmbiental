package dominio.factoresEmision;

import dominio.EntitiesPersistentes;
import dominio.actividades.enums.TipoActividad;
import dominio.mediosTransportes.TipoTransportePublico;
import dominio.mediosTransportes.TipoVehiculo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "factor_emision")
public class FactorEmision extends EntitiesPersistentes {
  @Column(name="nombre")
  private String nombre;

  @Column(name="tipo_actividad")
  @Enumerated(EnumType.STRING)
  private TipoActividad tipoActividad;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name="combustible_id",referencedColumnName = "id")
  private Combustible combustible;

  @Column(name="valor")
  private Double valor;

  @Column(name="tipo_factor_emision")
  @Enumerated(EnumType.STRING)
  private TipoFactorEmision tipoFactorEmision;

  @Column(name="tipo_vehiculo")
  @Enumerated(EnumType.STRING)
  private TipoVehiculo tipoVehiculo;

  @Column(name="tipo_transporte_publico")
  @Enumerated(EnumType.STRING)
  private TipoTransportePublico tipoTransportePublico;

  public FactorEmision(String nombre, TipoActividad tipoActividad, Combustible combustible, Double valor, TipoFactorEmision tipoFactorEmision, TipoVehiculo tipoVehiculo, TipoTransportePublico tipoTransportePublico) {
    this.nombre = nombre;
    this.tipoActividad = tipoActividad;
    this.combustible = combustible;
    this.valor = valor;
    this.tipoFactorEmision = tipoFactorEmision;
    this.tipoVehiculo = tipoVehiculo;
    this.tipoTransportePublico = tipoTransportePublico;
  }

  public FactorEmision(TipoActividad tipoActividad, Combustible combustible, Double valor,
                       TipoFactorEmision tipoFactorEmision) {
    this.tipoActividad = tipoActividad;
    this.combustible = combustible;
    this.valor = valor;
    this.tipoFactorEmision = tipoFactorEmision;
  }

  public FactorEmision(TipoVehiculo tipoVehiculo, Combustible combustible, Double valor,
                       TipoFactorEmision tipoFactorEmision) {
    this.tipoVehiculo = tipoVehiculo;
    this.combustible = combustible;
    this.valor = valor;
    this.tipoFactorEmision = tipoFactorEmision;
  }

  public FactorEmision(TipoTransportePublico tipoTransportePublico, Combustible combustible, Double valor,
                       TipoFactorEmision tipoFactorEmision) {
    this.tipoTransportePublico = tipoTransportePublico;
    this.combustible = combustible;
    this.valor = valor;
    this.tipoFactorEmision = tipoFactorEmision;
  }

  public Double getFactorEmision() {
    return this.valor;
  }
}
