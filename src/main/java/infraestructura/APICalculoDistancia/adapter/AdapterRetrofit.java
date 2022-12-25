package infraestructura.APICalculoDistancia.adapter;

import infraestructura.APICalculoDistancia.ApiLocalizacionException;
import infraestructura.APICalculoDistancia.CalculoDeDistanciaException;
import infraestructura.APICalculoDistancia.entities.Localidad;
import infraestructura.APICalculoDistancia.entities.Municipio;
import infraestructura.APICalculoDistancia.entities.Provincia;
import infraestructura.APICalculoDistancia.retrofit.Servicioddstpa;
import infraestructura.APICalculoDistancia.entities.Distancia;
import dominio.ubicacion.Direccion;

import java.util.List;

public class AdapterRetrofit implements AdapterDistancia {
    public Double calcularDistancia(Direccion origen, Direccion destino) throws CalculoDeDistanciaException {
        Distancia distanciaCalculada;
        try {
            distanciaCalculada = Servicioddstpa.instancia().distancia(origen.getLocalidad().getId(), origen.getCalle(), origen.getNumero(), destino.getLocalidad().getId(), destino.getCalle(), destino.getNumero());
        } catch (Exception e) {
            throw new CalculoDeDistanciaException(
                "Error al calcular la distancia - Servicio no disponible. Error: "
                    + e.getClass().getCanonicalName() + ": " + e.getMessage());
        }
        return Double.parseDouble(distanciaCalculada.valor);
    }

    public List<Provincia> getProvincias() {
        List<Provincia> provincias;
        try {
            provincias = Servicioddstpa.instancia().provincias();
        } catch (Exception e) {
            throw new ApiLocalizacionException(
                "Error al obtener las provincias. Error: " + e.getClass().getCanonicalName() + ": " + e.getMessage());
        }
        return provincias;
    }

    public List<Municipio> getMunicipios(int provinciaId) {
        List<Municipio> municipios;
        try {
            municipios = Servicioddstpa.instancia().municipios(provinciaId);
        } catch (Exception e) {
            throw new ApiLocalizacionException(
                "Error al obtener los municipios. Error: " + e.getClass().getCanonicalName() + ": " + e.getMessage());
        }
        return municipios;
    }

    public List<Localidad> getLocalidades(int municipioId) {
        List<Localidad> localidades;
        try {
            localidades = Servicioddstpa.instancia().localidades(municipioId);
        } catch (Exception e) {
            throw new ApiLocalizacionException(
                "Error al obtener las localidades. Error: " + e.getClass().getCanonicalName() + ": " + e.getMessage());
        }
        return localidades;
    }
}
