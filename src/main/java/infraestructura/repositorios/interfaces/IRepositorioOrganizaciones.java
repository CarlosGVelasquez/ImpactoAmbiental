package infraestructura.repositorios.interfaces;

import dominio.organizacion.Organizacion;

import java.util.List;

public interface IRepositorioOrganizaciones {
  public List<Organizacion> getOrganizaciones();
  public List<Organizacion> getOrganizacionesPorMunicipio(Integer idMunicipio);
  public List<Organizacion> getOrganizacionesPorProvincia(Integer idProvincia);
}
