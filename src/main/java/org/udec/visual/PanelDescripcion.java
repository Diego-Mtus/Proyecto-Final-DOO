package org.udec.visual;


import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.ProductosEnum;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PanelDescripcion extends JPanel {

    private String descripcionString;
    private final Font fuente = new Font("Comic Sans MS", Font.PLAIN, 16);

    public PanelDescripcion(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setLayout(null);
        setVisible(true);
        descripcionString = "¡Bienvenidos a la tienda!\nSeleccione un producto para ver su descripción.";
    }

    public void actualizarDescripcion(ProductosEnum item) {
        descripcionString = "\"" + item.getNombre() + "\"\nPara " + Arrays.stream(item.getParaQueMascota()).map(MascotasEnum::getNombre).collect(Collectors.joining(", ")) + "\n$" + item.getPrecio();;
        repaint();
    }

    public void actualizarDescripcionTab(String descripcion) {
        descripcionString = "¡Bienvenidos a la sección de " + descripcion +"!\nSeleccione un producto para ver su descripción.";
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

        g2d.dispose();

    }
}
