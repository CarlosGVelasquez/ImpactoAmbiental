package dominio.mediosTransportes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("medios_propios")
public class MediosPropios extends MedioTransporte {
    @Column(name="detalle")
    private String detalle;

    public MediosPropios(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public Double calculaDistancia() {
        return null;
    }

    public boolean sePuedeCompartir() {
        return true;
    }
}
