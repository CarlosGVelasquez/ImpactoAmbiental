package infraestructura.repositorios;

import dominio.mediosTransportes.Linea;

public class RepositorioLineas extends Repository<Linea> {
  public RepositorioLineas(Class<Linea> type) {
    super(type);
  }
}
