package infraestructura.repositorios;

import dominio.viajes.Tramo;
import dominio.viajes.Trayecto;
import shared.helpers.Helpers;
import dominio.actividades.Factory.ParseErrorException;
import dominio.factoresEmision.Combustible;
import infraestructura.repositorios.interfaces.IRepositorioCombustible;

import java.util.Collections;
import java.util.List;

public class RepositorioCombustible extends Repository<Combustible> implements IRepositorioCombustible {
  private List<Combustible> combustibles;
  private static final RepositorioCombustible INSTANCE  = new RepositorioCombustible(Combustible.class);

  public RepositorioCombustible(Class<Combustible> type) {
    super(type);
  }

  public static RepositorioCombustible instance() {
    return INSTANCE;
  }
  public Combustible getPorNombre(String nombre) {
    return getCombustibles().stream().filter(c -> Helpers.stringCompare(c.getNombre(), nombre)).findFirst()
        .orElseThrow(() -> new ParseErrorException("El combustible " + nombre + " no existe"));
  }

  public List<Combustible> getCombustibles() {
    return this.buscarTodos();
  }

  public void cargarRepo(List<Combustible> combustibles) {
    this.combustibles = combustibles;
  }

  public void vaciarRepo() {
    this.combustibles = Collections.emptyList();
  }
}
