package dominio.viajes;
import dominio.EntitiesPersistentes;
import dominio.ubicacion.Direccion;
import infraestructura.APICalculoDistancia.adapter.AdapterDistancia;
import lombok.Getter;
import lombok.Setter;
import dominio.mediosTransportes.MedioTransporte;

import javax.persistence.*;

import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tramo")
public class Tramo extends EntitiesPersistentes {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "calle", column = @Column(name = "inicio_calle")),
            @AttributeOverride( name = "numero", column = @Column(name = "inicio_numero")),
            @AttributeOverride( name = "localidad.id", column = @Column(name = "inicio_localidad_id")),
            @AttributeOverride( name = "localidad.nombre", column = @Column(name = "inicio_localidad_nombre")),
            @AttributeOverride( name = "codigoPostal", column = @Column(name = "inicio_codigo_postal")),
            @AttributeOverride( name = "municipio.id", column = @Column(name = "inicio_municipio_id")),
            @AttributeOverride( name = "municipio.nombre", column = @Column(name = "inicio_municipio_nombre")),
            @AttributeOverride( name = "provincia.id", column = @Column(name = "inicio_provincia_id")),
            @AttributeOverride( name = "provincia.nombre", column = @Column(name = "inicio_provincia_nombre"))


    })
    private Direccion inicio;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "calle", column = @Column(name = "fin_calle")),
            @AttributeOverride( name = "numero", column = @Column(name = "fin_numero")),
            @AttributeOverride( name = "localidad.id", column = @Column(name = "fin_localidad_id")),
            @AttributeOverride( name = "localidad.nombre", column = @Column(name = "fin_localidad_nombre")),
            @AttributeOverride( name = "codigoPostal", column = @Column(name = "fin_codigo_postal")),
            @AttributeOverride( name = "municipio.id", column = @Column(name = "fin_municipio_id")),
            @AttributeOverride( name = "municipio.nombre", column = @Column(name = "fin_municipio_nombre")),
            @AttributeOverride( name = "provincia.id", column = @Column(name = "fin_provincia_id")),
            @AttributeOverride( name = "provincia.nombre", column = @Column(name = "fin_provincia_nombre"))
    })
    private Direccion fin;

    @Column(name="nombre")
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="medio_transporte_id", referencedColumnName = "id")
    private MedioTransporte medioTransporte;

    @Column(name="es_compartido")
    private boolean esCompartido;

    @ManyToMany(mappedBy = "tramo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name="trayecto")
    //@JoinTable(name="trayecto_tramo")
    private List<Trayecto> trayecto;

    public Tramo(Direccion inicio, Direccion fin, String nombre, MedioTransporte medioTransporte) {
        this.inicio = inicio;
        this.fin = fin;
        this.nombre = nombre;
        this.medioTransporte = medioTransporte;
    }

    public Double calcularDistancia(AdapterDistancia adapter) {
        return this.medioTransporte.calculaDistancia() != null
            ? this.getMedioTransporte().calculaDistancia()
            : adapter.calcularDistancia(inicio, fin);
    }

    public Double calcularHC(AdapterDistancia adapter) {
        return medioTransporte.factorEmision() * calcularDistancia(adapter);
    }

    public void compartir() {
        esCompartido = true;
    }
}
