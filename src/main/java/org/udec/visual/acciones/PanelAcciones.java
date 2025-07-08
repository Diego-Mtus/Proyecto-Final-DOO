package org.udec.visual.acciones;

import org.udec.util.enumerations.BotonesUI;
import org.udec.visual.JButtonAnimado;
import org.udec.visual.PanelEscenario;
import org.udec.visual.listeners.DineroObtenidoListener;
import org.udec.visual.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;


public class PanelAcciones extends JPanel {

    private static PanelAcciones instance; // Instancia del panel de acciones
    private PanelEscenario panelEscenario; // Referencia al panel del escenario

    private JLayeredPane layeredPane; // Panel para contener los componentes
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private int indicePanelActual = 0; // Indice del panel actual, comienza en 0
    private PanelAlimentar panelAlimento;
    private PanelMedicar panelMedicamento;
    private PanelJuguete panelJuguete;
    private PanelJuegos panelJuegos;

    private PanelAcciones(PanelEscenario panel, int x, int y) {

        setBounds(0, 0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);
        setLayout(null);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);
        layeredPane.setLayout(null);

        cardPanel = new JPanel();
        cardPanel.setBounds(0, 0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        setVisible(false);
        setOpaque(false);
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(Color.black, 1)); // Para verlo mientras se desarrolla
        cardPanel.setOpaque(false); // Hacer el panel de tarjetas transparente

        // Inicializar los paneles de acciones
        panelAlimento = new PanelAlimentar();
        panelAlimento.setOpaque(false);
        panelAlimento.add(new JLabel("Alimentos"));
        cardPanel.add(panelAlimento, "0");

        panelMedicamento = new PanelMedicar();
        panelMedicamento.setOpaque(false);
        panelMedicamento.add(new JLabel("Medicamentos"));
        cardPanel.add(panelMedicamento, "1");

        panelJuguete = new PanelJuguete();
        panelJuguete.setOpaque(false);
        cardPanel.add(panelJuguete, "2");

        panelJuegos = new PanelJuegos();
        panelJuegos.setOpaque(false);
        panelJuegos.add(new JLabel("Juegos"));
        cardPanel.add(panelJuegos, "3");

        // Flechas para cambiar de panel
        JButtonAnimado flechaIzquierda = new JButtonAnimado(BotonesUI.BOTON_IZQUIERDASMALL.getImagen(), x, y + 40, 40, 40);
        flechaIzquierda.addActionListener(e -> cambiarPanel(-1));
        this.add(flechaIzquierda);

        JButtonAnimado flechaDerecha = new JButtonAnimado(BotonesUI.BOTON_DERECHASMALL.getImagen(), x + 160, y + 40, 40, 40);
        flechaDerecha.addActionListener(e -> cambiarPanel(1));
        this.add(flechaDerecha);

        // Agregar el panel de tarjetas al panel principal
        layeredPane.add(cardPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(flechaIzquierda, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(flechaDerecha, JLayeredPane.PALETTE_LAYER);
        this.add(layeredPane);

    }

    private void cambiarPanel(int direccion){
        indicePanelActual = (indicePanelActual + 4 + direccion) % 4;
        cardLayout.show(cardPanel, String.valueOf(indicePanelActual));
        panelJuguete.reiniciarPelota();
    }

    public void setPanelEscenario(PanelEscenario panelEscenario){
        this.panelEscenario = panelEscenario;
        this.panelJuguete.setMascotaActual(panelEscenario);
        this.panelAlimento.setMascotaActual(panelEscenario);
        this.panelMedicamento.setMascotaActual(panelEscenario);
        this.panelJuegos.setMascotaActual(panelEscenario);
    }

    public void setDineroObtenidoListenerJuegos(DineroObtenidoListener dineroObtenidoListener){
        if(panelJuegos != null) {
            panelJuegos.setDineroObtenidoListener(dineroObtenidoListener);
        }
    }

    public void reiniciarPelota() {
        if (panelJuguete != null) {
            panelJuguete.reiniciarPelota();
        }
    }

    public void actualizarListas() {
        if (panelAlimento != null) {
            panelAlimento.actualizarListaAlimentos();
        }
        if (panelMedicamento != null) {
            panelMedicamento.actualizarListaMedicamentos();
        }
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
