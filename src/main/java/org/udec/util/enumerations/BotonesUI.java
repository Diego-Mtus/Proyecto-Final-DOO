package org.udec.util.enumerations;

import org.udec.util.CargadorDeImagenes;

import javax.swing.*;
import java.awt.image.BufferedImage;

public enum BotonesUI {

    BOTON_IZQUIERDAGRANDE("/interfaz/botonIzquierdaGrande.png"),
    BOTON_DERECHAGRANDE("/interfaz/botonDerechaGrande.png"),
    BOTON_IZQUIERDASMALL("/interfaz/botonIzquierdaSmall.png"),
    BOTON_DERECHASMALL("/interfaz/botonDerechaSmall.png"),
    BOTON_COMPRARESCENARIO("/interfaz/botonComprarEscenario.png"),
    BOTON_TIENDA("/interfaz/botonTienda.png");

    private final ImageIcon imagen;

    BotonesUI(String ruta) {
        this.imagen = new ImageIcon(CargadorDeImagenes.cargarImagen(ruta));
    }

    public ImageIcon getImagen() {
        return imagen;
    }
}
