package dominio.viajes;

import dominio.EntitiesPersistentes;
import dominio.organizacion.Miembro;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name="invitacion_tramo")
public class InvitacionTramo extends EntitiesPersistentes {
  @ManyToOne
  @JoinColumn(name="invitador_id")
  private Miembro invitador;

  @ManyToOne
  @JoinColumn(name="invitado_id")
  private Miembro invitado;

  @ManyToOne
  @JoinColumn(name="tramo_id")
  private Tramo tramo;

  @Column(name="estado")
  @Enumerated(EnumType.STRING)
  private EstadoInvitacion estado;

  public InvitacionTramo(Miembro invitador, Miembro invitado, Tramo tramo) {
    this.invitador = invitador;
    this.invitado = invitado;
    this.tramo = tramo;
    this.estado = EstadoInvitacion.PENDIENTE;
  }

  public void aceptar() {
    this.tramo.compartir();
    this.estado = EstadoInvitacion.ACEPTADA;
    this.invitado.getTrayecto().addTramo(tramo);
  }
}
