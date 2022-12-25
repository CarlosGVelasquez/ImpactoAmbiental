package dominio.actividades;

import dominio.EntitiesPersistentes;
import dominio.actividades.enums.Alcance;
import dominio.actividades.enums.Periodicidad;
import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.FactorEmision;
import lombok.Getter;
import infraestructura.repositorios.interfaces.IRepositorioFactorEmision;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "actividad")
@DiscriminatorColumn(name = "discriminador")
public abstract class Actividad extends EntitiesPersistentes {
  @Enumerated(EnumType.STRING)
  protected Periodicidad periodicidad;

  //@Column(name="periodo", columnDefinition = "DATE")
  @Transient
  protected LocalDate periodo;

  @Column(name = "alcance")
  @Enumerated(EnumType.STRING)
  protected Alcance alcance;

  @Column(name = "tipo_actividad")
  @Enumerated(EnumType.STRING)
  protected TipoActividad tipoActividad;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name="factor_emision_id", referencedColumnName = "id")
  protected FactorEmision factorEmision;

  public Actividad(Periodicidad periodicidad, LocalDate periodo, FactorEmision factorEmision, Alcance alcance, TipoActividad tipoActividad) {
    this.periodicidad = periodicidad;
    this.periodo = periodo;
    this.factorEmision = factorEmision;
    this.alcance = alcance;
    this.tipoActividad = tipoActividad;
  }

  public abstract Double huellaDeCarbono();
}
