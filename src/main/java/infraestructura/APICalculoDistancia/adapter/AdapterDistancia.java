package infraestructura.APICalculoDistancia.adapter;

import dominio.ubicacion.Direccion;
import infraestructura.APICalculoDistancia.entities.Localidad;
import infraestructura.APICalculoDistancia.entities.Municipio;
import infraestructura.APICalculoDistancia.entities.Provincia;

import java.util.List;

public interface AdapterDistancia {
    public Double calcularDistancia(Direccion origen, Direccion destino);
    public List<Provincia> getProvincias();
    public List<Municipio> getMunicipios(int municipioId);
    public List<Localidad> getLocalidades(int municipioId);
}
