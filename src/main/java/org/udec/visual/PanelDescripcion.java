package org.udec.visual;


import org.udec.util.DineroNoSuficienteException;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.ProductosEnum;
import org.udec.visual.comandos.Command;
import org.udec.visual.comandos.ComprarProductoCommand;
import org.udec.visual.listeners.CompraListener;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PanelDescripcion extends JPanel {

    private final int BOTON_COMPRAR_ANCHO = 100;
    private final int BOTON_COMPRAR_ALTO = 100;
    private final int BOTON_COMPRAR_X_OFFSET = 110;
    private final int BOTON_COMPRAR_Y = 10;
    private final String TEXTO_BIENVENIDA = "¡Bienvenidos a la tienda!\nSeleccione un producto para ver su descripción.";

    private String descripcionString;
    private final Font fuente = new Font("Comic Sans MS", Font.PLAIN, 16);
    private JButton botonComprar;
    private ProductosEnum ultimoProductoSeleccionado;
    private CompraListener compraListener;

    public PanelDescripcion(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setLayout(null);
        setVisible(true);
        descripcionString = TEXTO_BIENVENIDA;

        botonComprar = new JButton("Comprar");
        botonComprar.setBounds(width - BOTON_COMPRAR_X_OFFSET, BOTON_COMPRAR_Y, BOTON_COMPRAR_ANCHO, BOTON_COMPRAR_ALTO);
        botonComprar.setFocusable(false);
        botonComprar.setVisible(false);
        botonComprar.addActionListener(_ -> comprarProducto());
        this.add(botonComprar);

    }

    public void setCompraListener(CompraListener compraListener) {
        this.compraListener = compraListener;
    }

    public void actualizarDescripcion(ProductosEnum item) {
        descripcionString = formatearDescripcion(item);
        ultimoProductoSeleccionado = item;
        botonComprar.setVisible(true);
        repaint();
    }

    private String formatearDescripcion(ProductosEnum item) {
        String paraMascota = (item.getParaQueMascota() == null)
                ? "Para todos los animales"
                : "Para " + Arrays.stream(item.getParaQueMascota())
                .map(MascotasEnum::getNombre)
                .collect(Collectors.joining(", "));

        return item.getNombre() + "\n" +
                paraMascota + "\n" +
                "$" + item.getPrecio() + "\n" +
                "Actualmente tienes: " + item.getInventario();
    }

    public void reiniciarDescripcion() {
        descripcionString = TEXTO_BIENVENIDA;
        ultimoProductoSeleccionado = null;
        botonComprar.setVisible(false);
        repaint();
    }


    private void comprarProducto() {
        if (compraListener != null && ultimoProductoSeleccionado != null) {
            Command comando = new ComprarProductoCommand(ultimoProductoSeleccionado, compraListener, this);
            comando.execute();
        }
    }

    public void actualizarDescripcionTab(String descripcion) {
        descripcionString = "¡Bienvenidos a la sección de " + descripcion +"!\nSeleccione un producto para ver su descripción.";
        botonComprar.setVisible(false);
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

    }
}
