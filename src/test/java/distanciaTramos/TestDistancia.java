package distanciaTramos;

import dominio.ubicacion.Localidad;
import infraestructura.APICalculoDistancia.adapter.AdapterDistancia;
import infraestructura.APICalculoDistancia.adapter.AdapterRetrofit;
import dominio.mediosTransportes.MedioTransporte;
import dominio.mediosTransportes.MediosPropios;
import dominio.mediosTransportes.TipoTransportePublico;
import dominio.mediosTransportes.TransportePublico;
import dominio.mediosTransportes.Linea;
import dominio.mediosTransportes.Parada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import dominio.ubicacion.Direccion;
import dominio.viajes.Tramo;
import dominio.viajes.Trayecto;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestDistancia {
    private final List<Parada> listaParadas = new ArrayList<>();
    private Parada parada1;
    private Parada parada2;
    private Parada parada3;
    private Direccion direccionOrigen;
    private Direccion direccionDestino;
    private Linea subteB;
    private Tramo tramoPorParadas;
    private MedioTransporte medioTransporte;
    @Mock
    AdapterDistancia adapterMock = mock(AdapterDistancia.class);

    @BeforeEach
    public void init() {
        when(adapterMock.calcularDistancia(any(), any())).thenReturn(50.0);
        parada1 = new Parada("Medrano", new Direccion(), 120.0, 0.0);
        parada2 = new Parada("Angel Gallardo", new Direccion(), 140.0, 120.0);
        parada3 = new Parada("Malabia", new Direccion(), 0.0, 140.0);
        listaParadas.add(parada1);
        listaParadas.add(parada2);
        listaParadas.add(parada3);
        subteB = new Linea("B",TipoTransportePublico.SUBTE, null,  listaParadas);

        medioTransporte = new TransportePublico(subteB, parada1, parada3, null);

        direccionOrigen = new Direccion();
        direccionDestino = new Direccion();
        tramoPorParadas = new Tramo(direccionOrigen, direccionDestino, "", medioTransporte);
    }


    @DisplayName("La distancia de un tramo es pedida a la api")
    @Test
    // Este test le pega a la API real
    void usamosApiParaPedirDistancia() {
        Direccion primeraDireccion = new Direccion("maipu", "1100", new Localidad(163, ""));
        Tramo unTramo = new Tramo(primeraDireccion, primeraDireccion, "casa", new MediosPropios("caminar"));
        Assertions.assertTrue(unTramo.calcularDistancia(new AdapterRetrofit()) > 0);
    }

    @DisplayName("La distancia de un tramo con transporte publico es calculada por nosotros")
    @Test
    void calculoDeDistanciTramoTransportePublico() {
        Assertions.assertEquals(260.0, tramoPorParadas.calcularDistancia(adapterMock));
    }

    @DisplayName("Calculo Distancia con api mockeada")
    @Test
    void calculoDeDistanciaTramoApiMockeada() {

        Tramo elTramoPrincipal = new Tramo(direccionOrigen, direccionDestino, "casa", new MediosPropios("caminar"));
        Assertions.assertEquals(50.0, elTramoPrincipal.calcularDistancia(adapterMock));
    }

    @DisplayName("Calculo trayecto")
    @Test
    void calculoDistanciaTrayecto(){
        Trayecto trayecto = new Trayecto(direccionOrigen, direccionDestino);
        trayecto.addTramo(tramoPorParadas);
        Assertions.assertEquals(260.0, trayecto.calcularDistancia(adapterMock));
    }

    @DisplayName("Calculo trayecto mixto")
    @Test
    void calculoDistanciaTrayectoMixto() {
        Trayecto trayecto = new Trayecto(new Direccion(), new Direccion());
        Tramo tramoPrincipalPrincipalPorApi = new Tramo(direccionOrigen, direccionDestino, "casa", new MediosPropios("caminar"));

        trayecto.addTramo(tramoPorParadas);
        trayecto.addTramo(tramoPrincipalPrincipalPorApi);

        Assertions.assertEquals(310.0, trayecto.calcularDistancia(adapterMock));
    }
}