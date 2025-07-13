package org.udec.visual;

import org.udec.util.enumerations.BotonesUI;
import org.udec.util.comandos.Command;
import org.udec.util.comandos.InicializarEscenarioCommand;


/**
 * Botón para inicializar el escenario.
 * Al hacer clic en el botón, se ejecuta un comando que maneja la lógica de inicialización del escenario.
 * Extiende de JButtonAnimado para incluir animación al presionar.
 */
public class BotonInicializarEscenario extends JButtonAnimado {

    /**
     * Constructor del botón de inicializar escenario.
     * @param panelEscenario El panel del escenario donde se mostrará el botón.
     * @param x Posición x del botón en el panel.
     * @param y Posición y del botón en el panel.
     */
    public BotonInicializarEscenario(PanelEscenario panelEscenario, int x, int y) {
        super(BotonesUI.BOTON_COMPRARESCENARIO.getImagen(), x, y, 240, 90);
        setVisible(true);

        panelEscenario.add(this);

        Command comando = new InicializarEscenarioCommand(panelEscenario);
        this.addActionListener(e -> comando.execute());

    }
}
