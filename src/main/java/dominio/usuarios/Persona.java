package dominio.usuarios;

import com.google.gson.annotations.Expose;
import dominio.EntitiesPersistentes;
import dominio.organizacion.Miembro;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "persona")
public class Persona extends EntitiesPersistentes {
    @OneToMany(mappedBy = "persona")
    List<Miembro> miembros;
    @Expose
    @Column(name="nombre")
    private String nombre;
    @Expose
    @Column(name="nro_documento")
    private String nroDocumento;
    @Expose
    @Column(name="apellido")
    private String apellido;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id",referencedColumnName = "id")
    private Usuario usuario;

    @Expose
    @Column(name="mail")
    private String mail;

    @Expose
    @Enumerated(EnumType.STRING)
    @Column(name="tipo_documento")
    private TipoDocumento tipoDoc;

    public Persona(String nombre, String nroDocumento, String apellido, Usuario usuario, String mail, TipoDocumento tipoDni) {
        this.nombre = nombre;
        this.nroDocumento = nroDocumento;
        this.apellido = apellido;
        this.usuario = usuario;
        this.mail = mail;
        this.tipoDoc = tipoDni;
    }

    public Persona(String nombre,  String apellido, String mail, String nroDocumento, TipoDocumento tipoDni) {
        this.nombre = nombre;
        this.nroDocumento = nroDocumento;
        this.apellido = apellido;
        this.mail = mail;
        this.tipoDoc = tipoDni;
    }
}
