package dominio.organizacion;

import dominio.EntitiesPersistentes;
import dominio.actividades.Actividad;
import dominio.usuarios.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dominio.ubicacion.Direccion;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name="organizacion")
@NoArgsConstructor
public class Organizacion extends EntitiesPersistentes {

    @Column(name="nombre")
    private String nombre;
    @Column(name="razon_social")
    private String razonSocial;

    @Column(name="mail")
    private String mail;

    @Column(name="celular")
    private String celular;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id",referencedColumnName = "id")
    private Usuario usuario;

    @Embedded
    private Direccion direccion;

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sector> sectores = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="clasificacion_id", referencedColumnName = "id")
    private Clasificacion clasificacion;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Miembro> miembros = new ArrayList<>();

    @Column(name="tipo_organizacion")
    @Enumerated(EnumType.STRING)
    private TipoOrg tipoOrg;

    @Column(name="aprobado")
    private boolean aprobado = false;  // Este lo agregue para que cuando el admin general lo acepte pueda empezar a cargar datos

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="organizacion_id", referencedColumnName = "id")
    private List<Actividad> actividades = new ArrayList<>();

    public Organizacion(Usuario usuario, String razonSocial, String mail, Direccion direccion,
                        Clasificacion clasificacion,TipoOrg tipoOrg) {
        this.usuario = usuario;
        this.razonSocial = razonSocial;
        this.mail = mail;
        this.direccion = direccion;
        this.clasificacion = clasificacion;
        this.tipoOrg = tipoOrg;
    }

    public void addSectores(Sector sector) {
        sectores.add(sector);
    }

    public void removeSectores(Sector sector) {
        sectores.remove(sector);
    }

    public void  addMiembro(Miembro miembro) {
        miembros.add(miembro);
    }

    public void  removeMiembro(Miembro miembro) {
        miembros.remove(miembro);
    }

   public void agregarActividad(List<Actividad> actividades) {
        this.actividades.addAll(actividades);
    }

    public List<Miembro> getMiembrosDeSector(Sector sector) {
        return this.miembros.stream().filter(m -> m.getSector() == sector).collect(Collectors.toList());
    }
}