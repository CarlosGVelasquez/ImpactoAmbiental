package dominio.viajes;

import dominio.EntitiesPersistentes;
import dominio.actividades.enums.TipoActividad;
import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.TipoFactorEmision;
import dominio.mediosTransportes.TipoTransportePublico;
import dominio.mediosTransportes.TipoVehiculo;
import dominio.organizacion.Miembro;
import dominio.ubicacion.Direccion;
import infraestructura.APICalculoDistancia.adapter.AdapterDistancia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Entity
@Table(name = "trayecto")
@Getter
@Setter
public class Trayecto extends EntitiesPersistentes {
    @Column(name="nombre")
    private String nombre;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinTable(name="trayecto_tramo")
    @Column(name="tramo")
    @OrderColumn(name="orden")
    List<Tramo> tramo = new ArrayList<>();

    public Trayecto(Direccion inicio, Direccion fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public Trayecto(String nombre, Direccion direccionInicial, Direccion direccionFinal, List<Tramo> tramo) {
        this.nombre = nombre;
        this.inicio= direccionInicial;
        this.fin = direccionFinal;
        this.tramo = (List<Tramo>) tramo;
    }

    public void addTramo(Tramo tramoPrincipal){
        tramo.add(tramoPrincipal);
    }

    public void removeTramo(Tramo tramoPrincipal){
        tramo.remove(tramoPrincipal);
    }

    public List<Tramo> getTramo() {
        return tramo;
    }

    public Double calcularDistancia(AdapterDistancia adapter){
        return tramo.stream().mapToDouble(t -> t.calcularDistancia(adapter)).sum();
    }

   public Double hcSinTramosCompartidos(AdapterDistancia adapter) {
        List<Tramo> tramosNoCompartidos = tramo.stream().filter(t -> !t.isEsCompartido()).collect(Collectors.toList());
        return tramosNoCompartidos.stream().mapToDouble(t -> t.calcularHC(adapter)).sum();
   }

   public List<Tramo> tramosCompartidos() {
       return tramo.stream().filter(t -> t.isEsCompartido()).collect(Collectors.toList());
   }

    // Para el cálculo propio de cada miembro
    public Double hcConTramosCompartidos(AdapterDistancia adapter) {
        return tramo.stream().mapToDouble(t -> t.calcularHC(adapter)).sum();
    }
}
