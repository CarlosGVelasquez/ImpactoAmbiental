package infraestructura.repositorios;

import dominio.mediosTransportes.Parada;
import dominio.mediosTransportes.ServicioContratado;
import dominio.mediosTransportes.TipoServContratado;

public class RepositorioServicioContratado extends Repository<TipoServContratado>{
  public RepositorioServicioContratado(Class<TipoServContratado> type) {
    super(type);
  }
}
