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

    private void dibujarBarraEstado(Graphics g, int estado, String nombre, int x, int y){

        Graphics2D g2d = (Graphics2D) g;
        Font font = new Font("Arial", Font.PLAIN, 12);
        int barraAncho = 10;
        int barraAlto = 40; // Altura cuando está al 100%

        // Que la altura de la barra sea proporcional al estado
        int llenado = (int) ((estado / 100.0) * barraAlto);

        float hue = estado / 100.0f; // Hue para el color, de 0 a 1
        g.setColor(Color.getHSBColor(hue * 0.33f, 1.0f, 1.0f)); // Color basado en el estado

        // Dibu jar la barra
        g2d.fillRoundRect(x, y + (barraAlto - llenado), barraAncho, llenado, 10, 10);

        // Dibujar el borde de la barra
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(x, y, barraAncho, barraAlto, 10, 10);

        // Dibujar el porcentaje
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        String porcentaje = estado + "%";
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int porcentajeX = x + (barraAncho - fontMetrics.stringWidth(porcentaje)) / 2;
        int porcentajeY = y + barraAlto + fontMetrics.getHeight();
        g2d.drawString(porcentaje, porcentajeX, porcentajeY);

        // Dibujar el nombre de la barra
        int nombreX = x + (barraAncho - fontMetrics.stringWidth(nombre)) / 2;
        g2d.drawString(nombre, nombreX, y - 5);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(mascota != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

            String estado = mascota.getNombrePropio() + " - " + mascota.getNombreAnimal();

            FontMetrics fontMetrics = g2d.getFontMetrics();

            int xEstado = (getWidth() - fontMetrics.stringWidth(estado)) / 2;
            g2d.drawString(estado, xEstado, fontMetrics.getHeight());

            // Dibujar las barras de estado debajo del nombre
            dibujarBarraEstado(g, mascota.getEstado().verHambre(), "Hambre", 44, fontMetrics.getHeight() + 32);
            dibujarBarraEstado(g, mascota.getEstado().verSalud(), "Salud", 124, fontMetrics.getHeight() + 32);
            dibujarBarraEstado(g, mascota.getEstado().verFelicidad(), "Felicidad", 204, fontMetrics.getHeight() + 32);

            g2d.dispose();
        }

    }
}
