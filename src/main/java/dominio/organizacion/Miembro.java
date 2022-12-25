package dominio.organizacion;

import com.google.gson.annotations.Expose;
import dominio.EntitiesPersistentes;
import dominio.organizacion.Organizacion;
import dominio.organizacion.Sector;
import dominio.usuarios.Persona;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dominio.viajes.Trayecto;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "miembro")
@Getter
@Setter
public class Miembro extends EntitiesPersistentes {
    @Expose
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="persona_id",referencedColumnName = "id")
    private Persona persona;


    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="organizacion_id",referencedColumnName = "id")
    private Organizacion organizacion;

    @Expose
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="trayecto_id",referencedColumnName = "id")
    private Trayecto trayecto;

    @Expose
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="sector_id",referencedColumnName = "id")
    private Sector sector;

    @Expose
    @Column(name="aprobado")
    private boolean aprobado;

    public Miembro(Persona persona, Organizacion organizacion, Sector sector, boolean aprobado) {
        this.persona = persona;
        this.organizacion = organizacion;
        this.sector = sector;
        this.aprobado = false;
    }
    public void cambiarEstadoMiembro(boolean estado){
        aprobado = estado;
    }
}
