package presentacion.models.requests;

import com.google.gson.annotations.SerializedName;
import dominio.factoresEmision.Combustible;
import dominio.factoresEmision.FactorEmision;
import dominio.mediosTransportes.*;
import lombok.Getter;

@Getter
public class CrearMedioTransporteModel {
    private String detalle;
    private TipoMedioDeTransporte tipo;
    private Linea linea;
    private Parada paradaInicio;
    private Parada paradaFin;
    private Combustible combustible;
    private TipoVehiculo tipoVehiculo;
    private TipoServContratado tipoServicioContratado;
}


