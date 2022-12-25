package dominio.mediosTransportes;

import dominio.EntitiesPersistentes;
import dominio.factoresEmision.Combustible;
import lombok.Getter;
import lombok.Setter;
import infraestructura.repositorios.interfaces.IRepositorioFactorEmision;

import javax.persistence.*;

import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tipo_servicio_contratado")
public class TipoServContratado extends EntitiesPersistentes {
    @Column(name="nombre")
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_vehiculo")
    private TipoVehiculo tipoVehiculo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="combustible_id",referencedColumnName = "id")
    private Combustible combustible;

    public TipoServContratado(String nombre, TipoVehiculo tipoVehiculo, Combustible combustible) {
        this.nombre = nombre;
        this.tipoVehiculo = tipoVehiculo;
        this.combustible = combustible;
    }
}
