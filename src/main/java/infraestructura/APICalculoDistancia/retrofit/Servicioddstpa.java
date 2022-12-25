package infraestructura.APICalculoDistancia.retrofit;

import infraestructura.APICalculoDistancia.entities.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Servicioddstpa {
    private static  Servicioddstpa instancia = null;
    private static final String urlApi = "https://ddstpa.com.ar/api/";
    private Retrofit retrofit ;
    private static final int OFFSET_INICIAL = 1;
    private static final int MAX_RESPONSE = 50;

    private Servicioddstpa(){
        this.retrofit= new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Servicioddstpa instancia(){
        if (instancia == null){
            instancia = new Servicioddstpa();
        }
        return instancia;
    }

    public Pais pais() throws IOException {
        Ddstpa ddstpa = this.retrofit.create(Ddstpa.class);
        Call<List<Pais>> reqPais = ddstpa.paises(1);
        Response<List<Pais>> resPais = reqPais.execute();
        Pais pais = resPais.body().get(0);
        return  pais;
    }

    public List<Provincia> provincias() throws IOException {
        Ddstpa ddstpa = this.retrofit.create(Ddstpa.class);
        Call<List<Provincia>> reqProvincias = ddstpa.provincias(1);
        Response<List<Provincia>> resProvincia = reqProvincias.execute();
        List<Provincia> provincia = resProvincia.body();
        return  provincia;
    }

    public List<Municipio> municipios(Integer provinciaId) throws IOException {
        Ddstpa ddstpa = this.retrofit.create(Ddstpa.class);
        List<Municipio> municipios = new ArrayList<>();
        for (int offset = OFFSET_INICIAL; ; offset ++) {
            Call<List<Municipio>> reqMunicipio = ddstpa.municipios(offset, provinciaId);
            Response<List<Municipio>> resMunicipio = reqMunicipio.execute();
            municipios.addAll(resMunicipio.body());
            if (resMunicipio.body().size() == 0 ||resMunicipio.body().size() < MAX_RESPONSE) {
                break;
            }
        }
        return municipios.stream().sorted((mun1, mun2) -> String.CASE_INSENSITIVE_ORDER.compare(mun1.nombre, mun2.nombre)).collect(Collectors.toList());
    }

    public List<Localidad> localidades(Integer municipioId) throws IOException {
        Ddstpa ddstpa = this.retrofit.create(Ddstpa.class);
        List<Localidad> localidades = new ArrayList<>();
        for (int offset = OFFSET_INICIAL; ; offset ++) {
            Call<List<Localidad>> reqLocalidades = ddstpa.localidades(offset, municipioId);
            Response<List<Localidad>> resLocalidades = reqLocalidades.execute();
            localidades.addAll(resLocalidades.body());
            if (resLocalidades.body().size() == 0 ||resLocalidades.body().size() < MAX_RESPONSE) {
                break;
            }
        }
        return localidades.stream().sorted((mun1, mun2) -> String.CASE_INSENSITIVE_ORDER.compare(mun1.nombre, mun2.nombre)).collect(Collectors.toList());
    }

    public Distancia distancia(Integer localidadOrigenId, String calleOrigen, String alturaOrigen,
                               Integer localidadDestinoId, String calleDestino, String alturaDestino)
            throws IOException {
        Ddstpa ddstpa = this.retrofit.create(Ddstpa.class);
        Call<Distancia> reqDistancia = ddstpa.distancias(localidadOrigenId,  calleOrigen, alturaOrigen,
                localidadDestinoId, calleDestino,alturaDestino );
        Response<Distancia> resDistancia = reqDistancia.execute();
        return  resDistancia.body();
    }

    public User user (User user) throws IOException {
        Ddstpa ddstpa = this.retrofit.create(Ddstpa.class);
        Call<User> reqUsser = ddstpa.usser(user);
        Response<User> resUsser = reqUsser.execute();
        return  resUsser.body();
    }
}

