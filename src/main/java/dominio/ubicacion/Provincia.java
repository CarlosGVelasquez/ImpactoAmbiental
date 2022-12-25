package dominio.ubicacion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Provincia  {
    @Column(name = "id_provincia")
    private Integer id;

    @Column(name = "nombre_provincia")
    private String nombre;

    public Provincia() {
    }

    public Provincia(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
