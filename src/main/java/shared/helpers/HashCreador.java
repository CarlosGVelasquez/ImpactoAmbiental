package shared.helpers;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashCreador {

  public static String hashear(String password) {
        MessageDigest md = null;
        try {
           md = MessageDigest.getInstance("SHA-256");

        } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
        }
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        return sha256;
  }

}
