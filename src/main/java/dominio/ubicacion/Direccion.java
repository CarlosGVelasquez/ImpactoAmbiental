package dominio.ubicacion;


import dominio.EntitiesPersistentes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;

@Embeddable
@Getter
@Setter
public class Direccion {
    @Column
    private String calle;
    @Column
    private String numero;
    @Embedded
    private Localidad localidad;
    @Embedded
    private Municipio municipio;
    @Embedded
    private Provincia provincia;

    @Column (name = "codigo_postal")
    private String codigoPostal;
    public Direccion(String calle, String numero, Localidad localidad, Municipio municipio, Provincia provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
        this.municipio = municipio;
    }

    public Direccion(String calle, String numero, Localidad localidad, Municipio municipio, Provincia provincia, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.municipio = municipio;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
    }

    public Direccion (){

    }

    public Direccion(String calle, String numero, Localidad localidad) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
    }

}
