package dominio.actividades;

import dominio.actividades.enums.Alcance;
import dominio.actividades.enums.Periodicidad;
import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import infraestructura.repositorios.interfaces.IRepositorioFactorEmision;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@DiscriminatorValue("COMBUSTION_MOVIL")
public class CombustionMovil extends Actividad  {
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name="combustible_id", referencedColumnName = "id")
  private Combustible combustible;

  @Column(name="valor")
  private Double valor;

  public CombustionMovil(Combustible combustible, Double valor, Periodicidad periodicidad, LocalDate periodo,
                         FactorEmision factorEmision) {
    super(periodicidad, periodo, factorEmision, Alcance.EMISIONES_DIRECTAS, TipoActividad.COMBUSTION_MOVIL);
    this.combustible = combustible;
    this.valor = valor;
  }

  @Override
  public Double huellaDeCarbono(){
    return this.valor * this.factorEmision.getFactorEmision();
  }
}
