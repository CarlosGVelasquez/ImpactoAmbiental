package dominio.mediosTransportes;

import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import lombok.Getter;
import lombok.Setter;
import infraestructura.repositorios.interfaces.IRepositorioFactorEmision;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("transporte_publico")

public class TransportePublico extends MedioTransporte {
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="linea_id",referencedColumnName = "id")
    private Linea linea;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="parada_inicio_id",referencedColumnName = "id")
    private Parada paradaInicio;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="parada_fin_id",referencedColumnName = "id")
    private Parada paradaFinal;

    public TransportePublico(Linea linea, Parada paradaInicio, Parada paradaFinal, FactorEmision fe) {
        this.linea = linea;
        this.paradaInicio = paradaInicio;
        this.paradaFinal = paradaFinal;
        this.factorEmision = fe;
    }

    @Override
    public Double calculaDistancia(){
        return linea.distancia(this.paradaInicio, this.paradaFinal);
    }

    public Combustible getCombustible() {
        return this.linea.getCombustible();
    }
}
