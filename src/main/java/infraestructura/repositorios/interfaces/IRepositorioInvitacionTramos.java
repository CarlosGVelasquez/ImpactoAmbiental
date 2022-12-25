package infraestructura.repositorios.interfaces;

import dominio.organizacion.Miembro;
import dominio.viajes.InvitacionTramo;

import java.util.List;

public interface IRepositorioInvitacionTramos {
  public List<InvitacionTramo> getInvitacionesByMiembroId(Miembro miembro);
  public Long add(InvitacionTramo invitacionTramo);
  public void update(InvitacionTramo invitacionTramo);
}
