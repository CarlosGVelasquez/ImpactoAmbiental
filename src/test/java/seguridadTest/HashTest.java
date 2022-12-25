package seguridadTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shared.helpers.HashCreador;

public class HashTest {

    @Test
    @DisplayName("Hasheo contaseña contra el ejemplo")
    void hasheoContrasenia() {
        String passwordHash = HashCreador.hashear("SHA-256");
        Assertions.assertEquals("bbd07c4fc02c99b97124febf42c7b63b5011c0df28d409fbb486b5a9d2e615ea", passwordHash);

    }
}
