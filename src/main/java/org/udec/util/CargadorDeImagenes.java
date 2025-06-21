package org.udec.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class CargadorDeImagenes {

    public static BufferedImage cargarImagen(String ruta) {
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(Objects.requireNonNull(CargadorDeImagenes.class.getResource(ruta)));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }
        return imagen;
    }
}
