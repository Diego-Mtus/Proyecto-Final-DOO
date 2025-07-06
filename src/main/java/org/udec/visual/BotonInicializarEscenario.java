package org.udec.visual;

import org.udec.util.enumerations.BotonesUI;
import org.udec.visual.comandos.Command;
import org.udec.visual.comandos.InicializarEscenarioCommand;


public class BotonInicializarEscenario extends JButtonAnimado {

    public BotonInicializarEscenario(PanelEscenario panelEscenario, int x, int y) {
        super(BotonesUI.BOTON_COMPRARESCENARIO.getImagen(), x, y, 240, 90);
        setVisible(true);

        panelEscenario.add(this);

        Command comando = new InicializarEscenarioCommand(panelEscenario);
        this.addActionListener(e -> comando.execute());

    }
}
