package aplicacion.servicios.calculadoraHc;

import lombok.Getter;
import dominio.organizacion.Miembro;

@Getter
public class ResultadosHcMiembro {
  private final Miembro miembro;
  private final Double hcMiembro;
  private final Double hcOrganizacion;
  private final Double impacto;

  public ResultadosHcMiembro(Miembro miembro, Double hcMiembro, Double hcOrganizacion) {
    this.miembro = miembro;
    this.hcMiembro = hcMiembro;
    this.hcOrganizacion = hcOrganizacion;
    this.impacto = (hcMiembro / hcOrganizacion) * 100;
  }
}
