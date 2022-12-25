package aplicacion.recomendaciones;

import infraestructura.notificaciones.Notificador;
import infraestructura.repositorios.interfaces.IRepositorioOrganizaciones;

public class GeneradorRecomendaciones {
    private IRepositorioOrganizaciones organizaciones;
    private Notificador notificador;

    public GeneradorRecomendaciones(IRepositorioOrganizaciones organizaciones, Notificador notificador) {
        this.organizaciones = organizaciones;
        this.notificador = notificador;
    }

    public void generarRecomendaciones() {
        organizaciones.getOrganizaciones().forEach(
                a -> this.notificador.notificar(a.getMail(), a.getCelular()));
    }
}


