package presentacion.models.requests;

import dominio.organizacion.Sector;
import dominio.organizacion.TipoOrg;
import dominio.ubicacion.Direccion;
import lombok.Getter;
import shared.json.JsonRequired;

import java.util.List;

@Getter
public class CrearOrganizacionModel {
 //   @JsonRequired(message = "El campo es requerido")
    private String nombre;
   // @JsonRequired(message = "El campo es requerido")
    private String razonSocial;
    //@JsonRequired(message = "El campo es requerido")
    private TipoOrg tipoOrganizacion;
    //@JsonRequired(message = "El campo es requerido")
    private Integer clasificacion;
    //@JsonRequired(message = "El campo es requerido")
    private String email;
    //@JsonRequired(message = "El campo es requerido")
    private String numeroDeTelefono;
    private Direccion direccion;
    private List<String> sectores;
}
