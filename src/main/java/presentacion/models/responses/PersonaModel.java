package presentacion.models.responses;

import lombok.Getter;
import shared.enums.EnumMetadata;

@Getter
public class PersonaModel {
  private UsuarioModel usuario;
  private String nombre;
  private String apellido;
  private String mail;
  private String nroDocumento;
  private EnumMetadata tipoDoc; //Devolvemos esto para mostrarlo fácil en el front. Ejemplo para crearlo: EnumMetadata.getEnumMetadata(TipoDocumento.DNI);

  public PersonaModel(UsuarioModel usuario, String nombre, String apellido, String mail, String nroDocumento, EnumMetadata tipoDoc) {
    this.usuario = usuario;
    this.nombre = nombre;
    this.apellido = apellido;
    this.mail = mail;
    this.nroDocumento = nroDocumento;
    this.tipoDoc = tipoDoc;
  }
}