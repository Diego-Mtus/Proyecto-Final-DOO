package org.udec.visual;

import org.udec.mascotas.Mascota;
import org.udec.util.GestionDeSonido;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.Random;


public class MascotaInteractuable extends JButton {

    private ImageIcon imagenMascota;

    private Clip sonidoMascota;
    private final Clip sonidoEasterEgg = GestionDeSonido.cargarClip("/sonidos/easteregg.wav");

    private PanelEscenario panelEscenario;

    // Variables para animación
    private ImageIcon imagenGrandeMascota;
    private int anchoOriginal;
    private int altoOriginal;
    private int crecimiento = 20;

    public MascotaInteractuable(PanelEscenario panelEscenario, int x, int y, int ancho, int alto) {
        super();
        this.anchoOriginal = ancho;
        this.altoOriginal = alto;
        this.setBounds(x, y, ancho, alto);
        this.setVisible(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setEnabled(false);
        this.panelEscenario = panelEscenario;


        this.addActionListener(e -> {
            if (panelEscenario.getEscenario().getMascotaActual() != null){
                this.animar();
                this.interactuar();
            }
        });
    }

    public void setMascota(Mascota mascota) {
        this.imagenMascota = new ImageIcon(mascota.getImagenMascota());

        this.imagenGrandeMascota = new ImageIcon(
                imagenMascota.getImage().getScaledInstance(anchoOriginal + crecimiento, altoOriginal + crecimiento, java.awt.Image.SCALE_SMOOTH)
        );

        this.sonidoMascota = panelEscenario.getEscenario().getMascotaActual().getSonidoMascota();
        this.setIcon(this.imagenMascota);
        this.setEnabled(true);
        this.setVisible(true);
    }

    public void removerMascota() {
        this.setVisible(false);
        this.setEnabled(false);
        this.setIcon(null);
        this.imagenMascota = null;
        this.imagenGrandeMascota = null;
        this.sonidoMascota = null;
    }

    private void interactuar() {
        if (sonidoMascota != null) {
            Random random = new Random();
            if (random.nextInt(100) < 2) { // 2% de probabilidad de sonar el easter egg
                GestionDeSonido.reproducirClipEnHilo(sonidoEasterEgg);
            }
            else{
            GestionDeSonido.reproducirClipEnHilo(sonidoMascota);
            }
        }
    }

    private void animar() {

        setIcon(imagenGrandeMascota); // Cambia el icono a la versión grande
        setSize(anchoOriginal + crecimiento, altoOriginal + crecimiento);
        setLocation(getX() - crecimiento / 2, getY() - crecimiento / 2);

        Timer timer = new Timer(120, e -> {
            setIcon(imagenMascota); // Restaura el icono original
            setSize(anchoOriginal, anchoOriginal);
            setLocation(getX() + crecimiento / 2, getY() + crecimiento / 2);
        });

        timer.setRepeats(false);
        timer.start();
    }

}
