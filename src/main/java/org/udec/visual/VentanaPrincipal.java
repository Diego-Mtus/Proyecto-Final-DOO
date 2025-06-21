package org.udec.visual;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    public static final int ANCHO = 640;
    public static final int ALTO = 860;

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private List<PanelEscenario> panelesEscenario;

    private int indiceActual = 0;
    private int contadorPaneles = 0;
    private final int MAX_PANELES = 10; // Cantidad máxima de escenarios que pueden existir.

    public VentanaPrincipal(){
        this.setTitle("Simulador de mascota");
        this.setSize(ANCHO, ALTO);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.add(new PanelEscenario());
        this.setVisible(true);
    }
}
