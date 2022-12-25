package presentacion.models.requests;

import dominio.ubicacion.Direccion;
import lombok.Getter;

import java.util.List;

@Getter
public class CrearTramoModel {
    private Direccion inicio;
    private Direccion fin;
    private String nombre;
    private CrearMedioTransporteModel medioDeTransporte;
    private boolean esCompartido;


}
