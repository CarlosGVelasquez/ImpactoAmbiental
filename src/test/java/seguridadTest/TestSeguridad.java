package seguridadTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import aplicacion.seguridad.*;


public class TestSeguridad {

  @DisplayName("Si la contraseña no cumple la longitud, no valida")
  @Test
  void contraseniaNoCumpleLongitud() {
    Verificable validacionLongitud = new Longitud();
    Assertions.assertFalse(validacionLongitud.verificar("corta"));
    Assertions.assertTrue(validacionLongitud.verificar("contrasenialarga"));
  }

  @DisplayName("Si la contraseña no tiene mayúscula, no valida")
  @Test
  void contraseniaNoCumpleMayuscula() {
    Verificable validacionMayuscula = new Mayuscula();

    Assertions.assertFalse(validacionMayuscula.verificar("contrasenia-sin-mayuscula"));
    Assertions.assertTrue(validacionMayuscula.verificar("ContraseniaConMayuscula"));
  }

  @DisplayName("Si la contraseña no tiene minúscula, no valida")
  @Test
  void contraseniaNoCumpleMinuscula() {
    Verificable validacionMinuscula = new Minuscula();

    Assertions.assertFalse(validacionMinuscula.verificar("CONTRASENIA-SIN-MINUSCULA"));
    Assertions.assertTrue(validacionMinuscula.verificar("ContraseniaConMayuscula"));
  }

  @DisplayName("Si la contraseña no tiene digito, no valida")
  @Test
  void contraseniaNoCumpleDigito() {
    Verificable validacionDigito = new Digito();

    Assertions.assertFalse(validacionDigito.verificar("ContraseniaSinDigito"));
    Assertions.assertTrue(validacionDigito.verificar("Contrasen1aConDigito"));
  }

  @DisplayName("Si la contraseña está en el listado de peores contraseñs, no valida")
  @Test
  void contraseniaEstaEnElListadoPeoresContrasenias() {
    Verificable validacionPeoresContrasenias = new PeoresContrasenias();

    Assertions.assertFalse(validacionPeoresContrasenias.verificar("password"));
    Assertions.assertTrue(validacionPeoresContrasenias.verificar("ContraseniaP1ola"));
  }

  @DisplayName("Una contraseña que no cumple alguna de varias restricciones, no valida")
  @Test
  void contraseniaInvalida() {

    Validador validador = new Validador();

    validador.agregarValidacion(new Mayuscula());
    validador.agregarValidacion(new Minuscula());
    validador.agregarValidacion(new Digito());
    validador.agregarValidacion(new PeoresContrasenias());

    Assertions.assertFalse(validador.validarPassword("ContraseniaSinDigito"));
    Assertions.assertFalse(validador.validarPassword("c0rta"));
    Assertions.assertFalse(validador.validarPassword("SIN-MINUSCULA-PERO-CON-D1GITO"));
    Assertions.assertFalse(validador.validarPassword("sin-mayuscula-pero-con-d1gito"));

    Assertions.assertTrue(validador.validarPassword("UnaContraseniaP1ola"));
  }
}
