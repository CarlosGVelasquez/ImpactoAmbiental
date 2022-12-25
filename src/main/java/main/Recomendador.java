package main;

import dominio.organizacion.Organizacion;
import infraestructura.notificaciones.NotificadorService;
import aplicacion.recomendaciones.GeneradorRecomendaciones;
import infraestructura.repositorios.RepositorioOrganizaciones;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Recomendador {

  public static void Recomendar() {
    GeneradorRecomendaciones recomendaciones = new GeneradorRecomendaciones(new RepositorioOrganizaciones(Organizacion.class),new NotificadorService());
    Timer timer = new Timer(); // Instancia el objeto Timer, disparador de la tarea
    TimerTask tarea = new TimerTask() { // Se describe la tarea que va a ejecutar ese Timer
      @Override
      public void run() {
        System.out.println("ejecutando Tarea:"+ new Date());
        //recomendaciones.generarRecomendaciones();
        System.out.println("Se termino el envio de notificaciones a las Organizaciones:");
      }
    };
    timer.schedule(tarea, 20000, 2000);  // Ejecuta la tarea cada 5 segundos
    //timer.scheduleAtFixedRate(tarea,0,2629000000); // Una vez al mes
  }
}
