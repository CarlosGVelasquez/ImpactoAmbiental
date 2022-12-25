package aplicacion.servicios;

import dominio.organizacion.Miembro;
import infraestructura.repositorios.interfaces.IRepositorioInvitacionTramos;
import dominio.viajes.InvitacionTramo;
import dominio.viajes.Tramo;

import java.util.List;

public class ServicioTramos {
    IRepositorioInvitacionTramos repositorioInvitacionTramos;

    public ServicioTramos(IRepositorioInvitacionTramos repositorioInvitacionTramos) {
        this.repositorioInvitacionTramos = repositorioInvitacionTramos;
    }

    public InvitacionTramo compartirTramoEntre(Miembro invitador, Miembro invitado, Tramo tramo) {
        InvitacionTramo invitacion = new InvitacionTramo(invitador, invitado, tramo);
        repositorioInvitacionTramos.add(invitacion);
        return invitacion;
    }

    public List<InvitacionTramo> invitacionesDisponibles(Miembro miembro) {
        return repositorioInvitacionTramos.getInvitacionesByMiembroId(miembro);
    }

    public void aceptarInvitacionTramo(InvitacionTramo invitacionTramo) {
        invitacionTramo.aceptar();
        repositorioInvitacionTramos.update(invitacionTramo);
    }
}
