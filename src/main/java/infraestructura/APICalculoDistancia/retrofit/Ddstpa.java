package infraestructura.APICalculoDistancia.retrofit;

import infraestructura.APICalculoDistancia.entities.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Ddstpa {

/*   */ @Headers({"accept: application/json", "Authorization: Bearer PdazKo/cXANtj9VMG3dgxxNs7Mdosruc0TEZg0YW+vc=" })  //Esto hay qe revisar  -> Hacer dinamico para mas adelante
        @GET("paises")
        Call<List<Pais>> paises(@Query("offset") Integer id);

        @Headers({"accept: application/json", "Authorization: Bearer PdazKo/cXANtj9VMG3dgxxNs7Mdosruc0TEZg0YW+vc="})
        @GET("provincias")
        Call<List<Provincia>> provincias(@Query("offset") Integer id);

        @Headers({"accept: application/json", "Authorization: Bearer PdazKo/cXANtj9VMG3dgxxNs7Mdosruc0TEZg0YW+vc="})
        @GET("municipios")
        Call<List<Municipio>> municipios(@Query("offset") Integer id, @Query("provinciaId") Integer provinciaId);

        @Headers({"accept: application/json", "Authorization: Bearer PdazKo/cXANtj9VMG3dgxxNs7Mdosruc0TEZg0YW+vc="})
        @GET("localidades")
        Call<List<Localidad>> localidades(@Query("offset") Integer id, @Query("municipioId")Integer municipioId);

        @Headers({"accept: application/json", "Authorization: Bearer PdazKo/cXANtj9VMG3dgxxNs7Mdosruc0TEZg0YW+vc="})
        @GET("distancia")
        Call<Distancia> distancias(@Query("localidadOrigenId") Integer localidadOrigenId,
                                   @Query("calleOrigen")String calleOrigen,
                                   @Query("alturaOrigen") String alturaOrigen,
                                   @Query("localidadDestinoId") Integer localidadDestinoId,
                                   @Query("calleDestino")String calleDestino,
                                   @Query("alturaDestino") String alturaDestino);

        @POST("user")
        Call<User> usser(@Body() User user);
}