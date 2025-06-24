package org.udec.util;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GestionDeSonido {

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
