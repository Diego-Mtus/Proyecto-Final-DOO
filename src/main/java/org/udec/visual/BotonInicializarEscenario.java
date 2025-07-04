package org.udec.visual;

import org.udec.visual.comandos.Command;
import org.udec.visual.comandos.InicializarEscenarioCommand;
import javax.swing.*;


public class BotonInicializarEscenario extends JButton {

    public BotonInicializarEscenario(PanelEscenario panelEscenario, int x, int y) {
        super("Comprar Escenario");
        setBounds(x, y, 200, 50);
        setFocusable(false);
        setVisible(true);

        panelEscenario.add(this);

        Command comando = new InicializarEscenarioCommand(panelEscenario);
        this.addActionListener(e -> comando.execute());

    }
}
