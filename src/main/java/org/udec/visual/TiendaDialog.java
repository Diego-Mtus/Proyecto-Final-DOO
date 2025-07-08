package org.udec.visual;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.AlimentosEnum;
import org.udec.util.enumerations.MedicinasEnum;
import org.udec.util.enumerations.ProductosEnum;
import org.udec.visual.listeners.CompraListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;


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


        // Panel de pestañas
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Comida", crearPanelCategoria(AlimentosEnum.values()));
        tabs.addTab("Medicina", crearPanelCategoria(MedicinasEnum.values()));
        add(tabs);

        tabs.setBounds(0, 0, 400, 500);
        tabs.setVisible(true);

        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                panelDescripcion.actualizarDescripcionTab(tabs.getTitleAt(tabs.getSelectedIndex()));
            }
        });

        // Panel de descripción
        panelDescripcion = new PanelDescripcion(0,500, 400, 126);
        add(panelDescripcion);
        panelDescripcion.setCompraListener((CompraListener) parent);

        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                panelDescripcion.reiniciarDescripcion();
            }
        });

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
            BufferedImage imagen = CargadorDeImagenes.cargarImagen(prod.getRutaImagen());
            if(imagen != null) {
                boton.setIcon(new ImageIcon(imagen.getScaledInstance(widthHeightBoton, widthHeightBoton, Image.SCALE_SMOOTH)));
            } else {
                boton.setText(prod.getNombre());
            }
            boton.setFocusable(false);
            boton.setBorderPainted(false);
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
