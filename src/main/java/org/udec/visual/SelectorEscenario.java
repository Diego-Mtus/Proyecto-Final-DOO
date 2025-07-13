package org.udec.visual;

import org.udec.util.enumerations.TiposEnum;
import org.udec.util.comandos.Command;
import org.udec.util.comandos.SelecionarEscenarioCommand;
import org.udec.util.listeners.CompraListener;

import javax.swing.*;
import java.awt.*;

/**
 * Diálogo para seleccionar un escenario en el juego.
 * Permite al usuario elegir entre diferentes escenarios disponibles.
 * Al seleccionar un escenario, se notifica al listener de compra.
 */
public class SelectorEscenario extends JDialog {

    private TiposEnum escenarioSeleccionado = null;
    private final CompraListener compraListener;

    private final int ANCHO = 300;
    private final int ALTO = 200;
    private final int FILAS = 2;
    private final int COLUMNAS = 2;

    /**
     * Constructor del Selector de Escenario.
     * Inicializa el diálogo y configura su apariencia y comportamiento.
     *
     * @param panelEscenario El panel del escenario desde el cual se invoca el selector.
     */
    public SelectorEscenario(PanelEscenario panelEscenario) {
        setTitle("Seleccionar Escenario");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(panelEscenario);
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        compraListener = (CompraListener) SwingUtilities.getWindowAncestor(panelEscenario);

        setLayout(new BorderLayout());
        JLabel label = new JLabel("Seleccione un escenario:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel  = crearPanelBotones();
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel crearPanelBotones() {
        JPanel buttonPanel = new JPanel(new GridLayout(FILAS, COLUMNAS));
        for (TiposEnum tipo : TiposEnum.values()){
            buttonPanel.add(crearBotonesDeEscenario(tipo));
        }
        return buttonPanel;
    }


    private JButton crearBotonesDeEscenario(TiposEnum tipo) {
        JButton button = new JButton(tipo.getNombreEscenario() + " - $" + tipo.getPrecioEscenario());
        button.setFocusable(false);
        Command comando = new SelecionarEscenarioCommand(tipo, this, compraListener);
        button.addActionListener(e -> comando.execute());
        return button;
    }

    /**
     * Establece el escenario seleccionado por el usuario.
     * Este método se invoca cuando el usuario selecciona un escenario.
     *
     * @param escenarioSeleccionado El escenario seleccionado por el usuario.
     */
    public void setEscenarioSeleccionado(TiposEnum escenarioSeleccionado) {
        this.escenarioSeleccionado = escenarioSeleccionado;
    }

    /**
     * Obtiene el escenario seleccionado por el usuario.
     * Este método se utiliza para recuperar el escenario elegido después de que el diálogo se cierra.
     *
     * @return El escenario seleccionado, o null si no se ha seleccionado ninguno.
     */
    public TiposEnum getEscenarioSeleccionado() {
        return escenarioSeleccionado;
    }

}
