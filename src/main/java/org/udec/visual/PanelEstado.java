package org.udec.visual;

import org.udec.mascotas.Mascota;
import org.udec.util.threads.HiloActualizadorEstado;

import javax.swing.*;
import java.awt.*;

public class PanelEstado extends JPanel {

    private Mascota mascota;
    private Thread hiloActualizador;

    public PanelEstado(PanelEscenario panel, int x, int y) {
        setBounds(x, y, 260, 120);
        setLayout(null);
        setVisible(false);
        setOpaque(false);
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(java.awt.Color.black, 1)); // Para verlo mientras se desarrolla
        panel.add(this);
    }

    public void inicializarEstado(Mascota mascota){
        if (mascota != null){
            this.mascota = mascota;
            System.out.println("Inicializando hilo actualizador de estado para la mascota: " + mascota.getNombrePropio());
            hiloActualizador = new Thread(new HiloActualizadorEstado(this, mascota));
            hiloActualizador.start();
            this.setVisible(true);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(mascota != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

            String estado = mascota.getNombrePropio() + " (" + mascota.getNombreAnimal() + ")";
            String salud = "Salud: " + mascota.getEstado().verSalud();
            String hambre = "Energía: " + mascota.getEstado().verHambre();
            String felicidad = "Hambre: " + mascota.getEstado().verFelicidad();

            FontMetrics fontMetrics = g2d.getFontMetrics();
            int y = fontMetrics.getHeight();

            int panelWidth = getWidth();

            // Centrar cada string
            int xEstado = (panelWidth - fontMetrics.stringWidth(estado)) / 2;
            int xSalud = (panelWidth - fontMetrics.stringWidth(salud)) / 2;
            int xHambre = (panelWidth - fontMetrics.stringWidth(hambre)) / 2;
            int xFelicidad = (panelWidth - fontMetrics.stringWidth(felicidad)) / 2;

            g2d.drawString(estado, xEstado, y);
            g2d.drawString(salud, xSalud, y + fontMetrics.getHeight());
            g2d.drawString(hambre, xHambre, y + 2 * fontMetrics.getHeight());
            g2d.drawString(felicidad, xFelicidad, y + 3 * fontMetrics.getHeight());

        }

    }
}
