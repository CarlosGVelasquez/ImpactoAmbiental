package dominio.ubicacion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Localidad {
    @Column(name = "id_localidad")
    private Integer id;

    @Column(name = "nombre_localidad")
    private String nombre;

    public Localidad(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
