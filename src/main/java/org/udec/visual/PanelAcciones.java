package org.udec.visual;

import javax.swing.*;
import java.awt.*;


public class PanelAcciones extends JPanel {

    private static PanelAcciones instance; // Instancia del panel de acciones
    private PanelEscenario panelEscenario; // Referencia al panel del escenario

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private int indicePanelActual = 0; // Indice del panel actual, comienza en 0
    private JPanel panelAlimento;
    private JPanel panelMedicamento;
    private JPanel panelJuguete;
    private JPanel panelJuegos;

    private PanelAcciones(PanelEscenario panel, int x, int y) {


        setBounds(x, y, 200, 120);
        this.setLayout(null);

        cardPanel = new JPanel();
        cardPanel.setBounds(50, 0, 100, 120);
        this.add(cardPanel);

        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        setVisible(false);
        setOpaque(false);
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(Color.black, 1)); // Para verlo mientras se desarrolla


        // Inicializar los paneles de acciones
        panelAlimento = new JPanel();
        panelAlimento.add(new JLabel("Alimentos"));
        cardPanel.add(panelAlimento, "0");

        panelMedicamento = new JPanel();
        panelMedicamento.add(new JLabel("Medicamentos"));
        cardPanel.add(panelMedicamento, "1");

        panelJuguete = new JPanel();
        panelJuguete.add(new JLabel("Juguetes"));
        cardPanel.add(panelJuguete, "2");

        panelJuegos = new JPanel();
        panelJuegos.add(new JLabel("Juegos"));
        cardPanel.add(panelJuegos, "3");

        // Flechas para cambiar de panel
        JButton flechaIzquierda = new JButton("<");
        flechaIzquierda.setBounds(0, 40, 40, 40);
        flechaIzquierda.addActionListener(e -> cambiarPanel(-1));
        this.add(flechaIzquierda);

        JButton flechaDerecha = new JButton(">");
        flechaDerecha.setBounds(160, 40, 40, 40);
        flechaDerecha.addActionListener(e -> cambiarPanel(1));
        this.add(flechaDerecha);

    }

    private void cambiarPanel(int direccion){
        indicePanelActual = (indicePanelActual + 4 + direccion) % 4;
        cardLayout.show(cardPanel, String.valueOf(indicePanelActual));
    }

    public void setPanelEscenario(PanelEscenario panelEscenario){
        this.panelEscenario = panelEscenario;
    }

    public static PanelAcciones getInstance(PanelEscenario panelEscenario, int x, int y) {
        if (instance == null) {
            instance = new PanelAcciones(panelEscenario, x, y);
        } else {
            instance.setPanelEscenario(panelEscenario);
        }
        return instance;
    }


}
