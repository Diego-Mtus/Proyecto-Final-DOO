package org.udec.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Clase que se encarga de cargar imágenes desde los recursos del proyecto.
 * Utiliza el método getResource para obtener la URL de la imagen y luego la lee
 * utilizando ImageIO.
 */
public class CargadorDeImagenes {

    /**
     * Carga una imagen desde la ruta especificada en los recursos del proyecto.
     *
     * @param ruta Ruta de la imagen dentro de los recursos del proyecto.
     * @return BufferedImage si se carga correctamente, null si hay un error.
     */
    public static BufferedImage cargarImagen(String ruta) {
        URL recurso = CargadorDeImagenes.class.getResource(ruta);
        if (recurso == null) {
            System.err.println("No se encontró el recurso de imagen: " + ruta);
            return null;
        }
        try {
            BufferedImage imagen = ImageIO.read(recurso);
            if (imagen == null) {
                System.err.println("No se pudo leer la imagen: " + ruta);
                return null;
            }
            return imagen;
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + ruta);
            return null;
        }
    }
}