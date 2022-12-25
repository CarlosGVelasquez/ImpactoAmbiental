package infraestructura.notificaciones;

import infraestructura.notificaciones.implementaciones.EmailSender;
import infraestructura.notificaciones.implementaciones.WhatsappSender;
import shared.helpers.Helpers;

public class NotificadorService implements Notificador {
  EmailSender emailSender =  new EmailSender();
  WhatsappSender whatsappSender = new WhatsappSender();

  @Override
  public void notificar(String mail, String celular) {
    if (Helpers.isNullOrEmpty(mail) && Helpers.isNullOrEmpty(celular))
      throw new NotificacionException("Los contactos no pueden estar vacíos");

    this.emailSender.enviarEmail(mail);
    this.whatsappSender.enviarWhatsapp(celular);
  }
}
