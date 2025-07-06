package org.udec.visual;

import org.udec.util.GestionDeSonido;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.event.ActionListener;

public class JButtonAnimado extends JButton {

    private final ImageIcon icono;
    private final ImageIcon iconoGrande;
    private final Clip sonidoClic = GestionDeSonido.cargarClip("/sonidos/click.wav");

    private final int xOriginal;
    private final int yOriginal;
    private final int anchoOriginal;
    private final int altoOriginal;
    private final int crecimiento = 6;

    public JButtonAnimado(ImageIcon icon, int x, int y, int ancho, int alto) {
        super(icon);

        this.anchoOriginal = ancho;
        this.altoOriginal = alto;
        this.xOriginal = x;
        this.yOriginal = y;

        this.icono = icon;
        this.iconoGrande = new ImageIcon(
                icon.getImage().getScaledInstance(anchoOriginal + crecimiento, altoOriginal + crecimiento, java.awt.Image.SCALE_SMOOTH)
        );

        setContentAreaFilled(false);
        setBorderPainted(false);
        setBounds(x, y, ancho, alto);
        setFocusable(false);
        setOpaque(false);

        if (sonidoClic.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl control = (FloatControl) sonidoClic.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-16); // Ejemplo: -10.0f baja el volumen
        }

    }

    private void animar(Runnable accion) {
        setIcon(iconoGrande);
        setSize(anchoOriginal + crecimiento, altoOriginal + crecimiento);
        setLocation(getX() - crecimiento / 2, getY() - crecimiento / 2);

        Timer timer = new Timer(120, e -> {
            setIcon(icono);
            setSize(anchoOriginal, altoOriginal);
            setLocation(xOriginal, yOriginal);
            if (accion != null) {
                accion.run();
            }
        });

        timer.setRepeats(false);
        timer.start();


        GestionDeSonido.reproducirClipEnHilo(sonidoClic);

    }


    // Se hace override para que se haga la animación antes de ejecutar el ActionListener
    @Override
    public void addActionListener(ActionListener l) {
        super.addActionListener(e -> this.animar(() -> l.actionPerformed(e)));
    }
}
