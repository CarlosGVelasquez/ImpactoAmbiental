package dominio.organizacion;

import dominio.EntitiesPersistentes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "clasificacion")
@Getter
@Setter
public class Clasificacion extends EntitiesPersistentes {
    @Column(name="clasificacion")
    private String clasificacion;

    public Clasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

}
