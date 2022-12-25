package infraestructura.repositorios;

import dominio.organizacion.Clasificacion;

public class RepositorioClasificaciones extends Repository<Clasificacion>{

    private Clasificacion clasificacion;
    public RepositorioClasificaciones(Class type) {
        super(type);
    }

    public Integer getClasificacion(Clasificacion clasificacion){
        return Integer.valueOf(clasificacion.getClasificacion());
    }
}
