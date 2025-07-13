package org.udec.visual.acciones;

import org.udec.util.enumerations.BotonesUI;
import org.udec.visual.JButtonAnimado;
import org.udec.visual.PanelEscenario;
import org.udec.util.listeners.DineroObtenidoListener;
import org.udec.visual.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;


/**
 * PanelAcciones es un panel que contiene diferentes acciones que se pueden realizar con la mascota.
 * Permite cambiar entre diferentes paneles de acciones como alimentar, medicar, jugar, etc.
 */
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

    /**
     * Constructor privado para implementar el patrón Singleton.
     * @param panel PanelEscenario al que se asociará este PanelAcciones.
     * @param x Posición x del panel en la ventana.
     * @param y Posición y del panel en la ventana.
     */
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

    /**
     * Establece el panel de escenario al que este PanelAcciones está asociado.
     * Actualiza los paneles de acciones con la mascota actual del escenario.
     * @param panelEscenario PanelEscenario al que se asociará este PanelAcciones.
     */
    public void setPanelEscenario(PanelEscenario panelEscenario){
        this.panelEscenario = panelEscenario;
        this.panelJuguete.setMascotaActual(panelEscenario);
        this.panelAlimento.setMascotaActual(panelEscenario);
        this.panelMedicamento.setMascotaActual(panelEscenario);
        this.panelJuegos.setMascotaActual(panelEscenario);
    }

    /**
     * Establece un listener para recibir eventos de dinero obtenido en los juegos.
     * @param dineroObtenidoListener Listener que manejará los eventos de dinero obtenido.
     */
    public void setDineroObtenidoListenerJuegos(DineroObtenidoListener dineroObtenidoListener){
        if(panelJuegos != null) {
            panelJuegos.setDineroObtenidoListener(dineroObtenidoListener);
        }
    }

    /**
     * Reinicia la pelota en el panel de juguetes.
     * Esto es para volver a colocar la pelota en su posición inicial
     */
    public void reiniciarPelota() {
        if (panelJuguete != null) {
            panelJuguete.reiniciarPelota();
        }
    }

    /**
     * Actualiza las listas de alimentos y medicamentos en los paneles correspondientes.
     * Se llama cuando se realiza una acción que puede cambiar la lista de alimentos o medicamentos.
     */
    public void actualizarListas() {
        if (panelAlimento != null) {
            panelAlimento.actualizarListaAlimentos();
        }
        if (panelMedicamento != null) {
            panelMedicamento.actualizarListaMedicamentos();
        }
    }

    /**
     * Método para obtener la instancia única de PanelAcciones (Singleton).
     * Si no existe, crea una nueva instancia; si ya existe, actualiza el panelEscenario.
     * @param panelEscenario PanelEscenario al que se asociará este PanelAcciones.
     * @param x Posición x del panel en la ventana.
     * @param y Posición y del panel en la ventana.
     * @return La instancia única de PanelAcciones.
     */
    public static PanelAcciones getInstance(PanelEscenario panelEscenario, int x, int y) {
        if (instance == null) {
            instance = new PanelAcciones(panelEscenario, x, y);
        } else {
            instance.setPanelEscenario(panelEscenario);
        }
        return instance;
    }
}
