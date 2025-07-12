package org.udec.util;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Clase que gestiona la carga y reproducción de sonidos en la aplicación.
 * Utiliza Java Sound para manejar clips de audio.
 */
public class GestionDeSonido {

    /**
     * Carga un clip de audio desde la ruta especificada.
     *
     * @param ruta Ruta del archivo de audio dentro del classpath.
     * @return Un objeto Clip que representa el audio cargado, o null si ocurre un error.
     */
    public static Clip cargarClip(String ruta) {
        try {
            InputStream sonidoStream = GestionDeSonido.class.getResourceAsStream(ruta);
            if (sonidoStream == null) {
                throw new IllegalArgumentException("Recurso no encontrado: " + ruta);
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(sonidoStream));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | IllegalArgumentException e) {
            System.err.println("Error al cargar el clip de audio: " + e.getMessage());
            return null;
        }
    }

    /**
     * Reproduce un clip de audio en un hilo separado.
     * Esto permite que la reproducción del sonido no bloquee el hilo principal de la aplicación.
     *
     * @param clip El clip de audio a reproducir.
     */
    public static void reproducirClipEnHilo(Clip clip) {
        new Thread(() -> {
            clip.stop();
            clip.setFramePosition(0); // Reinicia el clip al inicio
            clip.start();
            try {
                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
