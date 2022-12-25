package infraestructura.repositorios;

import dominio.organizacion.Miembro;
import dominio.viajes.Tramo;
import dominio.viajes.Trayecto;

import java.util.List;

public class RepositorioMiembros extends Repository<Miembro>{

    public RepositorioMiembros(Class<Miembro> type) {
        super(type);
    }

    public Miembro getByUsuarioId(Integer usuarioId) {
        // Esto está horrible, pero funciona
        return this.buscarTodos().stream().filter(m -> m.getPersona().getUsuario().getId().equals(usuarioId)).findFirst().orElse(null);
    }
}