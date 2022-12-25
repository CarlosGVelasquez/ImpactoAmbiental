package presentacion.models.requests;

import dominio.usuarios.TipoDocumento;
import lombok.Getter;
import presentacion.models.responses.UsuarioModel;
import shared.enums.EnumMetadata;

@Getter
public class PersonaModel {
  private UsuarioModel usuario;
  private String nombre;
  private String apellido;
  private String mail;
  private String nroDocumento;
  private TipoDocumento tipoDoc;

  public PersonaModel(UsuarioModel usuario, String nombre, String apellido, String mail, String nroDocumento, TipoDocumento tipoDoc) {
    this.usuario = usuario;
    this.nombre = nombre;
    this.apellido = apellido;
    this.mail = mail;
    this.nroDocumento = nroDocumento;
    this.tipoDoc = tipoDoc;
  }
}