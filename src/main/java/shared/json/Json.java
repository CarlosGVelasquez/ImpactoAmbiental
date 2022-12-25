package shared.json;

import com.google.gson.*;
import presentacion.models.requests.CrearOrganizacionModel;


public  class Json {

  public static <T> T fromJson(String json, Class<T> type) {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(type, new AnnotatedDeserializer<T>())
            .create();

    T tab = gson.fromJson(json, type);

    return tab;
  }

  public static String toJson(Object ob) {
    return ob != null ? new Gson().toJson(ob) : "";
  }


  public  static  String toJsonWithExpose(Object ob){
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    return ob != null ? gson.toJson(ob) : "";

  }

}



