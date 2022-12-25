package shared.json;
import com.google.gson.*;
import shared.helpers.Helpers;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

class AnnotatedDeserializer<T> implements JsonDeserializer<T> {

  public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) {
    T pojo = new Gson().fromJson(je, type);

    List<Field> fields = Arrays.asList(pojo.getClass().getDeclaredFields());
    fields.forEach(f -> {
      if (f.getAnnotation(JsonRequired.class) != null) {
        try {
          f.setAccessible(true);
          if (f.get(pojo) == null || emptyString(f.get(pojo)) ) {
            String message = f.getAnnotation(JsonRequired.class).message();
            throw new JsonParseException(message);
          }
        } catch (Exception ex) {
          throw new JsonParseException(ex.getMessage());
        }
      }
    });
    return pojo;
  }

  private Boolean emptyString(Object o) {
    return o.getClass().equals(String.class) && Helpers.isNullOrEmpty((String) o);
  }
}