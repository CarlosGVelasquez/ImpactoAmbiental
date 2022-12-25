package notificaciones;

import infraestructura.notificaciones.NotificacionException;
import infraestructura.notificaciones.Notificador;
import infraestructura.notificaciones.NotificadorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class Notificaciones {

  @DisplayName("Se envía notificaciones de forma correcta a una organización (mockeado)")
  @Test
  public void enviaNotificacionOkMock() {
    Notificador emailSenderMock = mock(Notificador.class);
    doNothing().when(emailSenderMock).notificar(any(), any());

    emailSenderMock.notificar("carlosdesiderio96@gmail.con", "1154760686");

    verify(emailSenderMock, times(1)).notificar(any(), any());
  }

  @DisplayName("Si ocurre algún error, lanza excepción")
  @Test
  public void falloEnEnvioDeNotificacion() {
    Exception exception = Assertions.assertThrows(NotificacionException.class, ()
        -> new NotificadorService().notificar("", ""));

    System.out.println(exception.getMessage());
  }

//  @DisplayName("Se envía notificaciones de forma correcta a una organización (sin mockear)")
//  @Test
//  public void enviaNotificacionOkReal() {
//    Notificador emailSender = new NotificadorService();
//    emailSender.notificar("carlosdesiderio96@gmail.com", "1154760686");
//  }
}
