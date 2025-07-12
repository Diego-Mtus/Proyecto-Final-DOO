package org.udec.visual;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.AlimentosEnum;
import org.udec.util.enumerations.MedicinasEnum;
import org.udec.util.enumerations.ProductosEnum;
import org.udec.util.listeners.CompraListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;


public class TiendaDialog extends JDialog {

    private static TiendaDialog singletonTienda;
    private final PanelDescripcion panelDescripcion;

    private final int COLUMNAS = 3;
    private final int TAM_BOTON = 100;
    private final int ESPACIO = 20;

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

        int filas = (int) Math.ceil(items.length / (double) COLUMNAS);
        int panelWidth = COLUMNAS * (TAM_BOTON + ESPACIO) + ESPACIO;
        int panelHeight = filas * (TAM_BOTON + ESPACIO) + ESPACIO;
        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        for (int i = 0; i < items.length; i++) {
            ProductosEnum prod = items[i];
            JButton boton = crearBotonProducto(prod, i);
            panel.add(boton);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        return scrollPane;
    }

    private JButtonAnimado crearBotonProducto(ProductosEnum prod, int i) {
        BufferedImage imagen = CargadorDeImagenes.cargarImagen(prod.getRutaImagen());
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(imagen).getScaledInstance(TAM_BOTON, TAM_BOTON, Image.SCALE_SMOOTH));

        int col = i % COLUMNAS;
        int fila = i / COLUMNAS;
        int x = ESPACIO + col * (TAM_BOTON + ESPACIO);
        int y = ESPACIO + fila * (TAM_BOTON + ESPACIO);

        JButtonAnimado boton = new JButtonAnimado(icono, x, y, TAM_BOTON, TAM_BOTON);
        boton.addActionListener(_ -> actualizarDescripcion(prod));
        return boton;
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
