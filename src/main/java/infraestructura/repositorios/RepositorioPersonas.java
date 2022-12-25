package infraestructura.repositorios;

import dominio.usuarios.Persona;
import dominio.viajes.Tramo;
import dominio.viajes.Trayecto;

import java.util.List;

public class RepositorioPersonas extends Repository<Persona>{
  public RepositorioPersonas(Class<Persona> type) {
    super(type);
  }


}
