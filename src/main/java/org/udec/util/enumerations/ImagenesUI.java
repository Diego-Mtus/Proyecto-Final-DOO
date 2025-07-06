package org.udec.util.enumerations;

import org.udec.util.CargadorDeImagenes;

import java.awt.image.BufferedImage;

public enum ImagenesUI {

    ICONO_ISHERIDO("/interfaz/iconoIsHerido.png"),
    ICONO_QUIEREJUGAR("/interfaz/iconoQuiereJugar.png"),
    BOTON_IZQUIERDAGRANDE("/interfaz/botonIzquierdaGrande.png"),
    BOTON_DERECHAGRANDE("/interfaz/botonDerechaGrande.png"),
    BOTON_IZQUIERDASMALL("/interfaz/botonIzquierdaSmall.png"),
    BOTON_DERECHASMALL("/interfaz/botonDerechaSmall.png"),
    BOTON_COMPRARESCENARIO("/interfaz/botonComprarEscenario.png"),
    BOTON_TIENDA("/interfaz/botonTienda.png");

    private final BufferedImage imagen;

    ImagenesUI(String ruta) {
        this.imagen = CargadorDeImagenes.cargarImagen(ruta);
    }

    public BufferedImage getImagen() {
        return imagen;
    }
}
