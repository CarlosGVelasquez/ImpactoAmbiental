package dominio.usuarios;

import dominio.EntitiesPersistentes;
import dominio.organizacion.Organizacion;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario extends EntitiesPersistentes {

    @Enumerated(EnumType.STRING)
    @Column(name="rol")
    private RolesUsuario rol;

    @Column(name="contrasenia")
    private String contrasenia = "";

    @Column(name="nombre_usuario")
    private String nombreUsuario = "";

    @Column(name="mail")
    private String mail = "";


    public Usuario(String nombreUsuario, String contrasenia, RolesUsuario rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }
    public Usuario(String nombreUsuario, String contrasenia, String mail) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.mail = mail;
    }

    // TODO : revisar - controlar acceso desde el front
    void aceptarOrganizacion (Organizacion newOrganizacion){
            newOrganizacion.setAprobado(true);
    }
}
