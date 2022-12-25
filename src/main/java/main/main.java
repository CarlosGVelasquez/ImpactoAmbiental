package main;

import spark.Spark;

public class main {
    public static void main(String[] argumentos){
      String port = System.getenv("SERVER_PORT");
      int intPort =  port != null && !port.equals("") ? Integer.parseInt(port) : 9000;
      Spark.port(intPort);
      //Recomendador.Recomendar();
      new Routes().init();
    }
}
