package org.udec.mascotas;

import org.udec.util.enumerations.TiposEnum;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;

public abstract class Mascota {

    protected String nombrePropio;
    protected String nombreAnimal;
    protected BufferedImage imagenMascota;
    protected Clip sonidoMascota;
    protected TiposEnum tipo;
    protected Estado estado;

    public TiposEnum getTipo(){
        return this.tipo;
    }

    public String getNombreAnimal(){
        return this.nombreAnimal;
    }

    public String getNombrePropio(){
        return this.nombrePropio;
    }

    public BufferedImage getImagenMascota() {
        return imagenMascota;
    }

    public Clip getSonidoMascota() {
        return sonidoMascota;
    }

    @Override
    public String toString() {
        return "Mascota: " +
                "nombrePropio='" + nombrePropio + '\'' +
                ", nombreAnimal='" + nombreAnimal + '\'' +
                ", tipo=" + tipo;
    }

    public Estado getEstado(){
        return estado;
    }

}
