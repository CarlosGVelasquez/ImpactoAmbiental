package dominio.mediosTransportes;

import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import infraestructura.repositorios.interfaces.IRepositorioFactorEmision;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@Getter
@Setter

@Entity
@DiscriminatorValue("servicio_contratado")
public class ServicioContratado extends MedioTransporte{
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="tipo_serv_contratado_id",referencedColumnName = "id")
    private TipoServContratado tipoServicio;

    public ServicioContratado(TipoServContratado tipoServicio, FactorEmision fe) {
        this.tipoServicio = tipoServicio;
        this.sePuedeCompartir = true;
        this.factorEmision = fe;
    }

    @Override
    public Double calculaDistancia() {
        return null;
    }

    public boolean sePuedeCompartir(){
        return sePuedeCompartir;
    }

    public Combustible getCombustible() {
        return this.tipoServicio.getCombustible();
    }

    public TipoVehiculo getTipoVehiculo() {
        return this.tipoServicio.getTipoVehiculo();
    }
}
