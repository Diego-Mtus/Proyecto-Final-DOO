package org.udec.util.enumerations;

import org.udec.util.CargadorDeImagenes;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Enumeración que define los botones de la interfaz de usuario del juego.
 * Cada botón tiene una imagen asociada que se carga desde un recurso.
 */
public enum BotonesUI {

    BOTON_IZQUIERDAGRANDE("/interfaz/botonIzquierdaGrande.png"),
    BOTON_DERECHAGRANDE("/interfaz/botonDerechaGrande.png"),
    BOTON_IZQUIERDASMALL("/interfaz/botonIzquierdaSmall.png"),
    BOTON_DERECHASMALL("/interfaz/botonDerechaSmall.png"),
    BOTON_COMPRARESCENARIO("/interfaz/botonComprarEscenario.png"),
    BOTON_TIENDA("/interfaz/botonTienda.png");

    private final ImageIcon imagen;

    /**
     * Constructor de la enumeración BotonesUI.
     * Carga la imagen asociada al botón desde el recurso especificado.
     *
     * @param ruta Ruta del recurso de la imagen del botón.
     */
    BotonesUI(String ruta) {
        this.imagen = new ImageIcon(CargadorDeImagenes.cargarImagen(ruta));
    }

    /**
     * Método para obtener la imagen asociada al botón.
     *
     * @return La imagen del botón como un objeto ImageIcon.
     */
    public ImageIcon getImagen() {
        return imagen;
    }
}
