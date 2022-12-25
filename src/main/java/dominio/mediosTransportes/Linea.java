package dominio.mediosTransportes;

import dominio.EntitiesPersistentes;
import lombok.Getter;
import lombok.Setter;
import dominio.factoresEmision.Combustible;

import java.util.List;
import javax.persistence.*;

import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "linea")
public class Linea extends EntitiesPersistentes {
    @Column(name="nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="linea_id",referencedColumnName = "id")
    private List<Parada> paradas;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="combustible_id",referencedColumnName = "id")
    private Combustible combustible;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_transporte_publico")
    private TipoTransportePublico tipoTransportePublico;

    public Double distancia(Parada origen, Parada destino){
        int inicio = paradas.indexOf(origen);
        int fin = paradas.indexOf(destino);

        //Tambien es posible crear una instancia de Linea distinta para la ida y la vuelta, haciendo el if innecesario
        List<Parada> recorrido = inicio < fin ? paradas.subList(inicio, fin) : paradas.subList(fin , inicio);

        return recorrido.stream().mapToDouble(a-> a.distanciaParadaSiguiente).sum();
    }
    
    public Linea(String nombre, TipoTransportePublico tipoTransportePublico, Combustible combustible, List<Parada> paradas) {
        this.nombre = nombre;
        this.paradas = paradas;
        this.combustible = combustible;
        this.tipoTransportePublico = tipoTransportePublico;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void addParada(Parada parada) {
        if(!paradas.isEmpty())
            paradas.get(paradas.size()-1).setDistanciaParadaSiguiente(parada.getDistanciaParadaAnterior());
        paradas.add(parada);
    }

    public void removeParadas(Parada parada) {
        paradas.remove(parada);
    }
}
