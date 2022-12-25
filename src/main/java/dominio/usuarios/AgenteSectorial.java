package dominio.usuarios;

import dominio.EntitiesPersistentes;
import lombok.Getter;

import javax.persistence.*;

import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "agente_sectorial")
public class AgenteSectorial extends EntitiesPersistentes {
    @Column(name="id_municipio")
    private Integer idMunicipio;

    @Column(name="id_provincia")
    private Integer idProvincia;

    @Column(name="nombre_agente")
    private String nombreAgente;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id",referencedColumnName = "id")
    private Usuario usuario;

    public AgenteSectorial(Integer idMunicipio, Integer idProvincia, String nombre,Usuario usuario) {
        this.idMunicipio = idMunicipio;
        this.idProvincia = idProvincia;
        this.nombreAgente = nombre;
        this.usuario = usuario;
    }
}
