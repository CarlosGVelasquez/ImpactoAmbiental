package infraestructura.repositorios.interfaces;

import dominio.factoresEmision.Combustible;

import java.util.List;

public interface IRepositorioCombustible {
  public Combustible getPorNombre(String nombre);
  public List<Combustible> getCombustibles();
}
