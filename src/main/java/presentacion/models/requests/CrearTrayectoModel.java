package presentacion.models.requests;

import dominio.ubicacion.Direccion;
import dominio.viajes.Tramo;
import dominio.viajes.Trayecto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CrearTrayectoModel {
    //@JsonRequired(message = "El nombre es requerido")
    private String nombre;

    private Direccion direccioInicio;

    private Direccion direccionFin;

    //private List<Tramo> tramo;
    private List<CrearTramoModel> tramo;
}
