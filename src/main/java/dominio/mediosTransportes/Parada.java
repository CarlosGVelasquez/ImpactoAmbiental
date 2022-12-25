package dominio.mediosTransportes;

import dominio.EntitiesPersistentes;
import lombok.Getter;
import lombok.Setter;
import dominio.ubicacion.Direccion;

import javax.persistence.*;

import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "parada")
public class Parada extends EntitiesPersistentes {
    @Column(name="nombre")
    String nombre;
    @Embedded
    Direccion direccion;

    @Column(name="distancia_parada_siguiente")
    Double distanciaParadaSiguiente; // Direccion paradaSiguiente
    @Column(name="distancia_parada_anterior")
    Double distanciaParadaAnterior; // Direccion paradaAnterior

    public Parada(String nombre, Direccion direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.distanciaParadaAnterior = 0.0;
        this.distanciaParadaSiguiente = 0.0;
    }

    public Parada(String nombre, Direccion direccion, Double paradaSiguiente, Double paradaAnterior) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.distanciaParadaSiguiente = paradaSiguiente;
        this.distanciaParadaAnterior = paradaAnterior;
    }
}
