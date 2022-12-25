package notificaciones;

import infraestructura.notificaciones.Notificador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import aplicacion.recomendaciones.GeneradorRecomendaciones;
import infraestructura.repositorios.interfaces.IRepositorioOrganizaciones;
import dominio.organizacion.Organizacion;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

public class Recomendaciones {

  @DisplayName("Se generan recomendaciones para una lista de organizaciones (mock)")
  @Test
  public void seGeneranRecomendacionesMock() {
    IRepositorioOrganizaciones mockRepo = mock(IRepositorioOrganizaciones.class);
    Notificador mockNotificador = mock(Notificador.class);

    when(mockRepo.getOrganizaciones()).thenReturn(generarOrganizaciones());

    GeneradorRecomendaciones generador = new GeneradorRecomendaciones(mockRepo, mockNotificador);

    generador.generarRecomendaciones();

    verify(mockNotificador, times(5)).notificar(any(), any());
  }

//  @DisplayName("Se generan recomendaciones para una lista de organizaciones (sin mockear)")
//  @Test
//  public void seGeneranRecomendacionesReal() {
//    IRepositorioOrganizaciones mockRepo = mock(IRepositorioOrganizaciones.class);
//
//    when(mockRepo.getOrganizaciones()).thenReturn(generarOrganizaciones());
//
//    GeneradorRecomendaciones generador = new GeneradorRecomendaciones(mockRepo, new NotificadorService());
//
//    generador.generarRecomendaciones();
//  }

  private List<Organizacion> generarOrganizaciones() {
    return Arrays.asList(
          new Organizacion(null, null, "andgomez@frba.utn.edu.ar", null, null, null),
          new Organizacion(null, null, "cdesiderio@frba.utn.edu.ar", null, null, null),
          new Organizacion(null, null, "framirez@frba.utn.edu.ar", null, null, null),
          new Organizacion(null, null, "carlovelasquezmenchaca@frba.utn.edu.ar", null, null, null),
          new Organizacion(null, null, "sbramuglia@frba.utn.edu.ar", null, null, null)
        );
  }
}
