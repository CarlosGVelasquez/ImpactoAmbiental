package dominio.actividades;

import dominio.actividades.enums.*;
import dominio.factoresEmision.FactorEmision;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@DiscriminatorValue("LOGISTICA")
public class LogisticaProductosResiduos extends Actividad {
  @Column(name="logistica_categoria")
  @Enumerated(EnumType.STRING)
  private CategoriaProductoTranportado categoria;

  @Column(name="logistica_transporte")
  @Enumerated(EnumType.STRING)
  private MedioDeTransporteLogistica medioDeTransporteLogistica;

  @Column(name="logistica_distancia_media")
  private Double distanciaMedia;

  @Column(name="logistica_peso")
  private Double peso;

  // TODO: sacar esto a un archivo de configuración / tabla de configuración
  @Transient
  private static Double K = 5.0;

  public LogisticaProductosResiduos(Periodicidad periodicidad, LocalDate periodo, Double distanciaMedia, Double peso,
                                    MedioDeTransporteLogistica medioDeTransporteLogistica, CategoriaProductoTranportado categoria, FactorEmision factorEmision) {
    super(periodicidad, periodo, factorEmision, Alcance.EMISIONES_INDIRECTAS_NO_CONTROLADAS, TipoActividad.LOGISTICA_PRODUCTOS_RESIDUOS);
    this.distanciaMedia = distanciaMedia;
    this.peso = peso;
    this.medioDeTransporteLogistica = medioDeTransporteLogistica;
    this.categoria = categoria;
  }
  @Override
  public Double huellaDeCarbono(){
    return this.distanciaMedia * this.peso * this.K * this.factorEmision.getFactorEmision();
  }
}
