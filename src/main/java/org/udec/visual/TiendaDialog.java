package org.udec.visual;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.AlimentosEnum;
import org.udec.util.enumerations.ProductosEnum;

import javax.swing.*;
import java.awt.*;


public class TiendaDialog extends JDialog {

    private static TiendaDialog singletonTienda;
    private final PanelDescripcion panelDescripcion;

    private TiendaDialog(JFrame parent) {
        super(parent, "Tienda", true);
        setSize(414, 660);

        // Para ubicarlo a la derecha de la ventana principal
        Point puntosPadre = parent.getLocationOnScreen();
        int x = puntosPadre.x + parent.getWidth();
        int y = puntosPadre.y + 50;
        setLocation(x, y);

        setLayout(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Comida", crearPanelCategoria(AlimentosEnum.values()));
        tabs.addTab("Juguetes", crearPanelCategoria(AlimentosEnum.values()));
        tabs.addTab("Medicina", crearPanelCategoria(AlimentosEnum.values()));
        add(tabs);

        tabs.setBounds(0, 0, 400, 500);
        tabs.setVisible(true);

        // Panel de descripción
        panelDescripcion = new PanelDescripcion(0,500, 400, 126);
        add(panelDescripcion);

        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }

    // Se hizo así pensando en que se pueden agregar más productos a futuro.
    private JScrollPane crearPanelCategoria(ProductosEnum[] items) {
        JPanel panel = new JPanel(null);
        int columnas = 3;
        int widthHeightBoton = 100; // Ancho de los botones
        int espacio = 20;
        int filas = (int) Math.ceil(items.length / (double) columnas);
        int panelWidth = columnas * (widthHeightBoton + espacio) + espacio;
        int panelHeight = filas * (widthHeightBoton + espacio) + espacio;
        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        for (int i = 0; i < items.length; i++) {
            ProductosEnum prod = items[i];
            JButton boton = new JButton();
            boton.setIcon(new ImageIcon(CargadorDeImagenes.cargarImagen(prod.getRutaImagen()).getScaledInstance(widthHeightBoton, widthHeightBoton, Image.SCALE_SMOOTH)));
            int col = i % columnas;
            int fila = i / columnas;
            int x = espacio + col * (widthHeightBoton + espacio);
            int y = espacio + fila * (widthHeightBoton + espacio);
            boton.setBounds(x, y, widthHeightBoton, widthHeightBoton);
            boton.addActionListener(e -> actualizarDescripcion(prod));
            panel.add(boton);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        return scrollPane;
    }

    private void actualizarDescripcion(ProductosEnum item) {
        // TODO
        System.out.println("PENDIENTE: " + item.getNombre());
        panelDescripcion.actualizarDescripcion(item);
    }

    public static TiendaDialog getInstance(JFrame parent) {
        if (singletonTienda == null) {
            singletonTienda = new TiendaDialog(parent);
        } else {
            singletonTienda.setVisible(true);
        }
        return singletonTienda;
    }

}
