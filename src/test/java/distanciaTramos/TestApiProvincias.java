package distanciaTramos;

import infraestructura.APICalculoDistancia.adapter.AdapterDistancia;
import infraestructura.APICalculoDistancia.adapter.AdapterRetrofit;
import infraestructura.APICalculoDistancia.entities.Localidad;
import infraestructura.APICalculoDistancia.entities.Municipio;
import infraestructura.APICalculoDistancia.entities.Provincia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestApiProvincias {

  @Test
  public void getProvincias() {
    AdapterDistancia adapterDistancia = new AdapterRetrofit();
    List<Provincia> provincias = adapterDistancia.getProvincias();

    Assertions.assertFalse(provincias.isEmpty());
    Assertions.assertEquals(24, provincias.size());
  }

  @Test
  public void getMunicipios() {
    AdapterDistancia adapterDistancia = new AdapterRetrofit();
    List<Municipio> municipios = adapterDistancia.getMunicipios(168);

    Assertions.assertFalse(municipios.isEmpty());
    Assertions.assertEquals(135, municipios.size());
  }

  @Test
  public void getLocalidades() {
    AdapterDistancia adapterDistancia = new AdapterRetrofit();
    List<Localidad> localidades = adapterDistancia.getLocalidades(105);

    Assertions.assertFalse(localidades.isEmpty());
    Assertions.assertEquals(3, localidades.size());
  }
}
