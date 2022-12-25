package dominio.mediosTransportes;


import dominio.EntitiesPersistentes;
import dominio.factoresEmision.FactorEmision;
import dominio.viajes.Tramo;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "medio_transporte")
@DiscriminatorColumn(name = "discriminador")

public abstract class MedioTransporte extends EntitiesPersistentes {
     //TODO: preguntar si este repositorio conviene agregarlo en la firma del método factorEmision()
     //para resolver el problema de que las entidades cuando sean recuperadas de la BD no lo van a tener instanciado
     //TODO: Otra opción: delegar el cálculo de la HC a un servicio, que reciba por inyección de dependencias
     //el repositorio, y reciba cada tramo y devuelva su HC

    @Column (name = "se_puede_compartir")
    protected boolean sePuedeCompartir = false;

    // TODO: Ver si este atributo va o hay que sacarlo
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="tramo_id", referencedColumnName = "id")
    private Tramo tramo;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "factor_emision_id")
    protected FactorEmision factorEmision;

    public abstract Double calculaDistancia();

    public boolean sePuedeCompartir(){
        return this.sePuedeCompartir;
    }

    public Double factorEmision() {
        return this.factorEmision != null ? this.factorEmision.getFactorEmision() : 0.0 ;
    }
}
