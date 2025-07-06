package org.udec.util.enumerations;

import org.udec.util.CargadorDeImagenes;

import java.awt.image.BufferedImage;

public enum ImagenesUI {

    ICONO_ISHERIDO("/interfaz/iconoIsHerido.png"),
    ICONO_QUIEREJUGAR("/interfaz/iconoQuiereJugar.png");

    private final BufferedImage imagen;

    ImagenesUI(String ruta) {
        this.imagen = CargadorDeImagenes.cargarImagen(ruta);
    }

    public BufferedImage getImagen() {
        return imagen;
    }
}
