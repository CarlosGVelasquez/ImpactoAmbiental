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

@Entity
@DiscriminatorValue("vehiculo_particular")
public class VehiculoParticular extends MedioTransporte {
    @Enumerated(EnumType.STRING)
    @Column(name="tipo_vehiculo")
    private TipoVehiculo tipoVehiculo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="combustible_id",referencedColumnName = "id")
    private Combustible combustible;

    public VehiculoParticular(Combustible combustible, TipoVehiculo tipoVehiculo, FactorEmision fe) {
        this.combustible = combustible;
        this.tipoVehiculo = tipoVehiculo;
        this.sePuedeCompartir = true;
        this.factorEmision = fe;
    }

    @Override
    public Double calculaDistancia() {
        return null;
    }

    @Override
    public boolean sePuedeCompartir() {
        return true;
    }
}
