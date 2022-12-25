package presentacion.models.responses;

import aplicacion.servicios.calculadoraHc.CalculadoraHc;
import dominio.organizacion.Miembro;
import dominio.organizacion.Organizacion;
import dominio.organizacion.Sector;
import dominio.usuarios.Persona;
import dominio.usuarios.Usuario;
import dominio.viajes.Trayecto;
import lombok.NoArgsConstructor;
import shared.enums.EnumMetadata;

import java.util.List;
import java.util.stream.Collectors;

/*
* Esta clase cumple el rol de generar un único punto de acople entre las clases de dominio y las clases
* que se devuelven como modelo al front end
* */
@NoArgsConstructor
public class ResponseMapper {

  // Instanciar los servicios o repositorios que sean necesarios
  private CalculadoraHc servicioCalculadoraHc = new CalculadoraHc(null, null); // Estos null null hay que revisarlos

  public ResponseMapper(CalculadoraHc servicioCalculadoraHc) {
    this.servicioCalculadoraHc = servicioCalculadoraHc;
  }

  public MiembroModel toResponse(Miembro miembro) {
    OrganizacionModel organizacionModel = miembro.getOrganizacion() != null? this.organizacionResponse(miembro.getOrganizacion()) : null;
    PersonaModel personaModel = miembro.getPersona() != null? this.personaResponse(miembro.getPersona()):null;
    SectorModel sectorModel = this.sectorResponse(miembro.getSector());
    TrayectoModel trayectoModel=null;
    Double huella = 0.0;
    if (miembro.getTrayecto() != null){
      trayectoModel = this.trayectoResponse(miembro.getTrayecto());
      huella = servicioCalculadoraHc.hcUnMiembroOrganizacion(miembro, miembro.getOrganizacion()).getHcMiembro();}

    return new MiembroModel(miembro.getId(), organizacionModel, personaModel, trayectoModel, huella,  sectorModel, miembro.isAprobado());
  }

  public PersonaModel personaResponse (Persona persona){
    UsuarioModel usuarioModel = this.usuarioResponse(persona.getUsuario());

  return new PersonaModel(usuarioModel, persona.getNombre(), persona.getApellido(),persona.getMail(),
          persona.getNroDocumento(), EnumMetadata.getEnumMetadata(persona.getTipoDoc()));
  }

  private UsuarioModel usuarioResponse(Usuario usuario) {
    return new UsuarioModel(usuario.getNombreUsuario());
  }

  public OrganizacionModel organizacionResponse(Organizacion organizacion) {
    List<SectorModel> sectoresModel = organizacion.getSectores().stream().map(s -> new SectorModel(s.getSectorNombre(), s.getId())).collect(Collectors.toList());
    return new OrganizacionModel(organizacion.getNombre(), null, sectoresModel, organizacion.getId());
    //servicioCalculadoraHc.hcOrganizacionActual(organizacion)
  }

  public SectorModel sectorResponse(Sector sector){
    return new SectorModel(sector.getSectorNombre(), sector.getId());
  }
  public TrayectoModel trayectoResponse(Trayecto trayecto){
  //por ahora vacio
    return new TrayectoModel();
  }
}
