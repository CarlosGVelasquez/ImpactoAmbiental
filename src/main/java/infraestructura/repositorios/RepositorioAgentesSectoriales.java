package infraestructura.repositorios;

import dominio.organizacion.Miembro;
import dominio.usuarios.AgenteSectorial;

public class RepositorioAgentesSectoriales extends Repository<AgenteSectorial>{

    public RepositorioAgentesSectoriales(Class<AgenteSectorial> type) {
        super(type);
    }

    public AgenteSectorial getByUsuarioId(Integer usuarioId) {
        //TODO: select * from AgenteSectorial where usuario_id = :usuarioID
        return null;
    }

}