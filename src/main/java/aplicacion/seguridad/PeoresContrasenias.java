package aplicacion.seguridad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PeoresContrasenias implements Verificable {

  private final String pathPasswords = "src/main/resources/PeoresContrasenas.txt";

  public boolean verificar(String password) { {
      try (Stream<String> peoresPass = Files.lines(Paths.get(pathPasswords))) {
        if (peoresPass.anyMatch(p -> p.equals(password))) {
          return false;
        }
      } catch (IOException e) {
          throw new RuntimeException("No se puede acceder al listado de 10.000 peores contrasenas. Error: " + e + ". Mensaje: " + e.getMessage());
      }
      return true;
    }
  }
}
