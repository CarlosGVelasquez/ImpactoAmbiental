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
public class Municipio  {
    @Column(name = "id_municipio")
    private Integer id;

    @Column(name = "nombre_municipio")
    private String nombre;

    public Municipio(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
