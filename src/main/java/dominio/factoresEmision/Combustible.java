package dominio.factoresEmision;

import dominio.EntitiesPersistentes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "combustible")
public class Combustible extends EntitiesPersistentes {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "unidad")
    private String unidad;

    public Combustible(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Combustible)) {
            return false;
        }
        Combustible c = (Combustible) o;
        return this.getId().equals(c.getId());
    }
}

