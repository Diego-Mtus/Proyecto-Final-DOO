package org.udec.visual;

import org.udec.util.GestionDeSonido;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.event.ActionListener;

public class JButtonAnimado extends JButton {

    private final int CRECIMIENTO = 6; // Crecimiento del botón al hacer clic
    private final int TIEMPO_ANIMACION_MS = 120;
    private final float VOLUMEN_CLICK = -16f;

    private final ImageIcon icono;
    private final ImageIcon iconoGrande;
    private final Clip sonidoClic = GestionDeSonido.cargarClip("/sonidos/click.wav");

    private final int xOriginal;
    private final int yOriginal;
    private final int anchoOriginal;
    private final int altoOriginal;

    public JButtonAnimado(ImageIcon icon, int x, int y, int ancho, int alto) {
        super(icon);
        this.anchoOriginal = ancho;
        this.altoOriginal = alto;
        this.xOriginal = x;
        this.yOriginal = y;

        this.icono = icon;
        this.iconoGrande = new ImageIcon(
                icon.getImage().getScaledInstance(anchoOriginal + CRECIMIENTO, altoOriginal + CRECIMIENTO, java.awt.Image.SCALE_SMOOTH)
        );

        setContentAreaFilled(false);
        setBorderPainted(false);
        setBounds(x, y, ancho, alto);
        setFocusable(false);
        setOpaque(false);

        if (sonidoClic.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl control = (FloatControl) sonidoClic.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(VOLUMEN_CLICK);
        }

    }

    private void animar(Runnable accion) {
        setIcon(iconoGrande);
        setSize(anchoOriginal + CRECIMIENTO, altoOriginal + CRECIMIENTO);
        setLocation(xOriginal - CRECIMIENTO / 2, yOriginal - CRECIMIENTO / 2);

        Timer timer = new Timer(TIEMPO_ANIMACION_MS, e -> {
            restaurarBoton();
            if (accion != null) {
                accion.run();
            }
        });

        timer.setRepeats(false);
        timer.start();

        GestionDeSonido.reproducirClipEnHilo(sonidoClic);

    }

    private void restaurarBoton() {
        setIcon(icono);
        setSize(anchoOriginal, altoOriginal);
        setLocation(xOriginal, yOriginal);
    }


    // Se hace override para que se haga la animación antes de ejecutar el ActionListener
    @Override
    public void addActionListener(ActionListener l) {
        super.addActionListener(e -> this.animar(() -> l.actionPerformed(e)));
    }
}
