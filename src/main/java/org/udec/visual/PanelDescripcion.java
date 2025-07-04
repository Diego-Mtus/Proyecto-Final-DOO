package org.udec.visual;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.ProductosEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelDescripcion extends JPanel {

    private String descripcionString;
    private BufferedImage descripcionImagen;
    private final Font fuente = new Font("Comic Sans MS", Font.PLAIN, 16);

    public PanelDescripcion(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setLayout(null);
        setVisible(true);
        descripcionString = "Bienvenidos a mi tienda jeje";
    }

    public void actualizarDescripcion(ProductosEnum item) {
        descripcionString = "\"" + item.getNombre() + "\"\nPara " + item.getParaQueMascota().getNombre() + "\n$" + item.getPrecio();
        descripcionImagen = CargadorDeImagenes.cargarImagen(item.getRutaImagen());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (descripcionString != null) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(fuente);
            FontMetrics fontMetrics = g2d.getFontMetrics();
            String[] lineas = descripcionString.split("\\n");
            int y = fontMetrics.getHeight();
            for (String linea : lineas) {
                g2d.drawString(linea, 10, y);
                y += fontMetrics.getHeight();
            }
        }


        if (descripcionImagen != null) {
            int imgX = getWidth() - descripcionImagen.getWidth() - 10;
            int imgY = 10;
            g.drawImage(descripcionImagen, imgX, imgY, null);
        }

        g2d.dispose();

    }
}
