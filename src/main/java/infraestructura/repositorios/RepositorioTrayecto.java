package infraestructura.repositorios;

import dominio.viajes.Tramo;
import dominio.viajes.Trayecto;

import java.util.List;

public class RepositorioTrayecto extends Repository<Trayecto> {

    public RepositorioTrayecto(Class<Trayecto> type) {
        super(type);
    }
    public List<Trayecto> getTrayecto() {
        return null;
    }

    public Trayecto getTrayectoByTramo(Tramo tramo) {
        return null;
    }
}

