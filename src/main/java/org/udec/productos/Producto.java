package org.udec.productos;

import org.udec.util.enumerations.MascotasEnum;

import java.awt.image.BufferedImage;

public abstract class Producto {

    protected String nombre;
    protected MascotasEnum paraQueMascota;
    protected BufferedImage imagen;
    protected int precio;

    public String getNombre() {
        return nombre;
    }

    public MascotasEnum getParaQueMascota() {
        return paraQueMascota;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public int getPrecio() {
        return precio;
    }
}
