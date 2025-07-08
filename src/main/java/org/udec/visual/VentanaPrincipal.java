package org.udec.visual;

import org.udec.util.Dinero;
import org.udec.util.DineroNoSuficienteException;
import org.udec.util.enumerations.BotonesUI;
import org.udec.visual.acciones.PanelAcciones;
import org.udec.visual.listeners.AdopcionListener;
import org.udec.visual.listeners.CompraListener;
import org.udec.visual.listeners.DineroObtenidoListener;
import org.udec.visual.listeners.EscenarioListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame implements EscenarioListener, CompraListener, AdopcionListener, DineroObtenidoListener {

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
    private JButtonAnimado botonIzquierda;
    private JButtonAnimado botonDerecha;

    // Distintos componentes de la ventana
    private JLabel labelIndice;
    private JButtonAnimado botonTienda;
    private Dinero dinero;
    private JLabel labelDinero;
    private PanelAcciones panelAcciones;

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
        botonIzquierda = new JButtonAnimado(BotonesUI.BOTON_IZQUIERDAGRANDE.getImagen(), 20, ALTO / 2 - 40, 60, 40);
        botonIzquierda.addActionListener(_ -> cambiarPanel(-1));

        botonDerecha = new JButtonAnimado(BotonesUI.BOTON_DERECHAGRANDE.getImagen(), ANCHO - 80, ALTO / 2 - 40, 60, 40);
        botonDerecha.addActionListener(e -> cambiarPanel(1));


        // Label para mostrar el índice actual
        labelIndice = new JLabel();
        labelIndice.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        labelIndice.setHorizontalAlignment(SwingConstants.LEFT);
        labelIndice.setBounds(10, 10, ANCHO, 30);
        actualizarLabelIndice();

        // Label pare mostrar el dinero actualmente disponible
        dinero = new Dinero(200); // Inicializar con $200
        labelDinero = new JLabel();
        labelDinero.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        labelDinero.setHorizontalAlignment(SwingConstants.RIGHT);
        labelDinero.setBounds(ANCHO - 190, 10, 180, 30);
        labelDinero.setText("$" + dinero.getCantidad());


        // Boton de tienda
        botonTienda = new JButtonAnimado(BotonesUI.BOTON_TIENDA.getImagen(), 20, ALTO - 80, 60, 60);
        botonTienda.addActionListener(_ -> TiendaDialog.getInstance(this));

        // Panel de acciones
        panelAcciones = PanelAcciones.getInstance(panelInicial, ANCHO / 2 - 100, ALTO - 140);
        panelAcciones.setDineroObtenidoListenerJuegos(this);

        // Añadir componentes al layeredPane
        panelCapas.add(cardPanel, JLayeredPane.DEFAULT_LAYER);
        panelCapas.add(botonIzquierda, JLayeredPane.PALETTE_LAYER);
        panelCapas.add(botonDerecha, JLayeredPane.PALETTE_LAYER);
        panelCapas.add(labelIndice, JLayeredPane.PALETTE_LAYER);
        panelCapas.add(labelDinero, JLayeredPane.PALETTE_LAYER);
        panelCapas.add(botonTienda, JLayeredPane.PALETTE_LAYER);
        panelCapas.add(panelAcciones, JLayeredPane.DRAG_LAYER);

        this.setContentPane(panelCapas);
        this.pack();
        this.setVisible(true);

        TiendaDialog.getInstance(this); // Inicializar la tienda para que esté lista al abrir la ventana
    }

    // Métod o auxiliar para añadir paneles y mantener el contador
    private void addPanelToCardLayout(PanelEscenario panel) {
        panel.setEscenarioListener(this);
        panel.setAdopcionListener(this);
        panelesEscenario.add(panel);
        cardPanel.add(panel, "Panel" + contadorPaneles);
        contadorPaneles++;
    }

    // Método para actualizar el texto del label
    private void actualizarLabelIndice() {
        labelIndice.setText((indiceActual + 1) + "/" + panelesEscenario.size());
    }

    private void cambiarPanel(int direccion) {
        int totalPaneles = panelesEscenario.size();
        indiceActual = (indiceActual + direccion + totalPaneles) % totalPaneles;
        cardLayout.show(cardPanel, "Panel" + indiceActual);
        actualizarLabelIndice();
        if(panelesEscenario.get(indiceActual).tieneMascota()) {
            panelAcciones.setPanelEscenario(panelesEscenario.get(indiceActual));
            panelAcciones.setVisible(true);
            panelAcciones.reiniciarPelota();
        } else {
            panelAcciones.setVisible(false);
        }
    }

    private void actualizarLabelDinero(){
        labelDinero.setText("$" + dinero.getCantidad());
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


    @Override
    public void comprar(int precio) throws DineroNoSuficienteException {
        dinero.restar(precio);
        actualizarLabelDinero();

        new Thread(() -> {
            try {
                Thread.sleep(100);
                SwingUtilities.invokeLater(() -> panelAcciones.actualizarListas());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }).start();

    }

    @Override
    public void adopcionRealizada(PanelEscenario panelEscenario) {
        panelAcciones = PanelAcciones.getInstance(panelEscenario, ANCHO / 2 - 100, ALTO - 140);
        panelAcciones.setVisible(true);
    }

    @Override
    public void ventaMascotaRealizada(int dineroObtenido) {
        dinero.agregar(dineroObtenido);
        actualizarLabelDinero();
        panelAcciones.setVisible(false);
    }

    @Override
    public void dineroObtenido(int dineroObtenido) {
        dinero.agregar(dineroObtenido);
        actualizarLabelDinero();
    }
}
