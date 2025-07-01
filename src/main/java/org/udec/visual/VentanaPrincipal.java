package org.udec.visual;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame implements EscenarioListener{

    public static final int ANCHO = 640;
    public static final int ALTO = 860;

    // Panel que contiene los escenarios
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private List<PanelEscenario> panelesEscenario;

    // Variables para controlar paneles de escenario.
    private int indiceActual = 0;
    private int contadorPaneles = 0;
    private final int MAX_PANELES = 10; // Cantidad máxima de escenarios que pueden existir.

    // Panel que permite poner botones superpuestos a los paneles de escenario
    private JLayeredPane panelCapas;
    private JButton botonIzquierda;
    private JButton botonDerecha;
    private JLabel labelIndice;
    private JButton botonTienda;

    public VentanaPrincipal(){
        this.setTitle("Simulador de mascota");
        this.setSize(ANCHO, ALTO);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        panelCapas = new JLayeredPane();
        panelCapas.setPreferredSize(new Dimension(ANCHO, ALTO));
        panelCapas.setLayout(null);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.setBounds(0, 0, ANCHO, ALTO);

        panelesEscenario = new ArrayList<>();
        PanelEscenario panelInicial = new PanelEscenario();
        addPanelToCardLayout(panelInicial);

        // Botones de navegación
        botonIzquierda = new JButton("<");
        botonIzquierda.setBounds(20, ALTO / 2 - 40, 60, 40);
        botonIzquierda.setFocusable(false);
        botonIzquierda.addActionListener(e -> cambiarPanel(-1));

        botonDerecha = new JButton(">");
        botonDerecha.setBounds(ANCHO - 80, ALTO / 2 - 40, 60, 40);
        botonDerecha.setFocusable(false);
        botonDerecha.addActionListener(e -> cambiarPanel(1));


        // Label para mostrar el índice actual
        labelIndice = new JLabel();
        labelIndice.setFont(new Font("Arial", Font.BOLD, 16));
        labelIndice.setHorizontalAlignment(SwingConstants.CENTER);
        labelIndice.setBounds(0, 10, ANCHO, 30);
        actualizarLabelIndice();


        // Boton de tienda
        botonTienda = new JButton("Tienda");
        botonTienda.setBounds(20, ALTO - 60, 100, 40);
        botonTienda.setFocusable(false);
        botonTienda.addActionListener(_ -> new TiendaDialog(this));

        // Añadir componentes al layeredPane
        panelCapas.add(cardPanel, JLayeredPane.DEFAULT_LAYER);
        panelCapas.add(botonIzquierda, JLayeredPane.PALETTE_LAYER);
        panelCapas.add(botonDerecha, JLayeredPane.PALETTE_LAYER);
        panelCapas.add(labelIndice, JLayeredPane.PALETTE_LAYER);
        panelCapas.add(botonTienda, JLayeredPane.PALETTE_LAYER);

        this.setContentPane(panelCapas);
        this.pack();
        this.setVisible(true);

    }

    // Métod o auxiliar para añadir paneles y mantener el contador
    private void addPanelToCardLayout(PanelEscenario panel) {
        panel.setEscenarioListener(this);
        panelesEscenario.add(panel);
        cardPanel.add(panel, "Panel" + contadorPaneles);
        contadorPaneles++;
    }

    // Método para actualizar el texto del label
    private void actualizarLabelIndice() {
        labelIndice.setText((indiceActual + 1) + " / " + panelesEscenario.size());
    }

    private void cambiarPanel(int direccion) {
        int totalPaneles = panelesEscenario.size();
        indiceActual = (indiceActual + direccion + totalPaneles) % totalPaneles;
        cardLayout.show(cardPanel, "Panel" + indiceActual);
        actualizarLabelIndice();
    }


    private boolean todosTienenEscenario() {
        for (PanelEscenario p : panelesEscenario) {
            if (!p.tieneEscenario()) return false;
        }
        return true;
    }

    @Override
    public void escenarioInicializado(PanelEscenario panel) {
        if(contadorPaneles < MAX_PANELES && todosTienenEscenario()){
            PanelEscenario nuevoPanel = new PanelEscenario();
            addPanelToCardLayout(nuevoPanel);
            actualizarLabelIndice();
        }
    }

}
