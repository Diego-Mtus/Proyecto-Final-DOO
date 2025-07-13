package org.udec.visual;

import org.udec.mascotas.Mascota;
import org.udec.util.GestionDeSonido;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.Random;


/**
 * Clase que representa una mascota interactuable en el escenario.
 * Al hacer clic en la mascota, se reproduce un sonido y se anima el icono.
 */
public class MascotaInteractuable extends JButton {

    private final int PROBABILIDAD_EASTER_EGG = 2; // %
    private final int TIEMPO_ANIMACION_MS = 120;
    private final int CRECIMIENTO = 20;

    private ImageIcon imagenMascota;
    private Clip sonidoMascota;
    private final Clip sonidoEasterEgg = GestionDeSonido.cargarClip("/sonidos/easteregg.wav");

    private PanelEscenario panelEscenario;

    // Variables para animación
    private ImageIcon imagenGrandeMascota;
    private int anchoOriginal;
    private int altoOriginal;
    private int xOriginal;
    private int yOriginal;

    /**
     * Constructor de la mascota interactuable.
     * Inicializa el botón y configura sus propiedades.
     *
     * @param panelEscenario El panel del escenario donde se mostrará la mascota.
     */
    public MascotaInteractuable(PanelEscenario panelEscenario) {
        super();
        this.panelEscenario = panelEscenario;

        this.setVisible(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setEnabled(false);

        this.addActionListener(e -> {
            if (panelEscenario.getEscenario().getMascotaActual() != null){
                this.animarMascota();
                this.reproducirSonido();
            }
        });
    }

    /**
     * Establece la mascota a mostrar en el botón.
     * Configura la imagen, sonido y dimensiones de la mascota.
     *
     * @param mascota La mascota a mostrar.
     */
    public void setMascota(Mascota mascota) {
        this.imagenMascota = new ImageIcon(mascota.getImagenMascota());

        this.anchoOriginal = imagenMascota.getIconWidth();
        this.altoOriginal = imagenMascota.getIconHeight();
        this.xOriginal = (VentanaPrincipal.ANCHO - anchoOriginal) / 2;
        this.yOriginal = (VentanaPrincipal.ALTO - altoOriginal) / 2;
        this.setBounds(xOriginal, yOriginal, anchoOriginal, altoOriginal);

        this.imagenGrandeMascota = new ImageIcon(
                imagenMascota.getImage().getScaledInstance(anchoOriginal + CRECIMIENTO, altoOriginal + CRECIMIENTO, java.awt.Image.SCALE_SMOOTH)
        );

        this.sonidoMascota = panelEscenario.getEscenario().getMascotaActual().getSonidoMascota();
        this.setIcon(this.imagenMascota);
        this.setEnabled(true);
        this.setVisible(true);
    }

    /**
     * Obtiene la posición original de la mascota en el panel.
     * Devuelve un arreglo con las coordenadas x, y, ancho y alto de la mascota.
     *
     * @return Un arreglo con las coordenadas y dimensiones de la mascota en formato {x, y, x + ancho, y + alto}.
     */
    public int[] getPosicionMascota() {
        return new int[]{xOriginal, yOriginal, xOriginal + anchoOriginal, altoOriginal + yOriginal};
    }

    /**
     * Elimina la mascota del panel.
     * Desactiva el botón, elimina el icono y libera los recursos asociados.
     */
    public void removerMascota() {
        this.setVisible(false);
        this.setEnabled(false);
        this.setIcon(null);
        this.imagenMascota = null;
        this.imagenGrandeMascota = null;
        this.sonidoMascota = null;
    }

    private void reproducirSonido() {
        if (sonidoMascota != null) {
            Random random = new Random();
            if (random.nextInt(100) < PROBABILIDAD_EASTER_EGG) { // 2% de probabilidad de sonar el easter egg
                GestionDeSonido.reproducirClipEnHilo(sonidoEasterEgg);
            }
            else{
            GestionDeSonido.reproducirClipEnHilo(sonidoMascota);
            }
        }
    }

    private void animarMascota() {
        setIcon(imagenGrandeMascota); // Cambia el icono a la versión grande
        setSize(anchoOriginal + CRECIMIENTO, altoOriginal + CRECIMIENTO);
        setLocation(xOriginal - CRECIMIENTO / 2, yOriginal - CRECIMIENTO / 2);

        Timer timer = new Timer(TIEMPO_ANIMACION_MS, e -> restaurarMascota());
        timer.setRepeats(false);
        timer.start();
    }

    private void restaurarMascota(){
        setIcon(imagenMascota); // Restaura el icono original
        setSize(anchoOriginal, altoOriginal);
        setLocation(xOriginal, yOriginal);
    }

}
