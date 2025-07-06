package org.udec.visual;

import org.udec.util.enumerations.ImagenesUI;
import org.udec.visual.comandos.Command;
import org.udec.visual.comandos.InicializarEscenarioCommand;
import javax.swing.*;


public class BotonInicializarEscenario extends JButton {

    public BotonInicializarEscenario(PanelEscenario panelEscenario, int x, int y) {
        super(new ImageIcon(ImagenesUI.BOTON_COMPRARESCENARIO.getImagen()));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setBounds(x, y, 240, 90);
        setFocusable(false);
        setVisible(true);

        panelEscenario.add(this);

        Command comando = new InicializarEscenarioCommand(panelEscenario);
        this.addActionListener(e -> comando.execute());

    }
}
