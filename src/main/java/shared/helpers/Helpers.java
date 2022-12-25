package shared.helpers;

import java.text.Normalizer;
import java.util.Objects;

public class Helpers {
  public static Boolean stringCompare(String value1, String value2) {
    return stringNormalize(value1).equalsIgnoreCase(stringNormalize(value2));
  }

  public static String stringNormalize(String value) {
    String result = Normalizer.normalize(value, Normalizer.Form.NFD);
    result = result.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    return result.toUpperCase();
  }

  public static Boolean isNullOrEmpty (String value) {
    return value == null || value.trim().isEmpty();
  }

  public static Boolean equals(Object o1, Object o2) {
    if ((o1 == null && o2 != null) || (o1 != null && o2 == null)) {
      return false;
    }

    if (o1 == null && o2 == null) {
      return true;
    }

    return Objects.equals(o1, o2);
  }


}
