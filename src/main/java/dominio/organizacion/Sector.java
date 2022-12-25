package dominio.organizacion;

import dominio.EntitiesPersistentes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "sector")
@Getter
@Setter
public class Sector extends EntitiesPersistentes {
    @Column(name="nombre")
    private String sectorNombre;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="organizacion_id", referencedColumnName = "id")
    private Organizacion organizacion;

    public Sector(String sectorNombre) {
        this.sectorNombre = sectorNombre;
    }

    public Sector(String sectorNombre, Organizacion organizacion) {
        this.sectorNombre = sectorNombre;
        this.organizacion = organizacion;
    }
}
