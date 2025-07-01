package org.udec.visual;

import org.udec.visual.comandos.AdoptarMascotaCommand;
import org.udec.visual.comandos.Command;

import javax.swing.*;

public class BotonAdoptarMascota extends JButton {

    public BotonAdoptarMascota(PanelEscenario panelEscenario, int x, int y) {
        super("Adoptar Mascota");
        setBounds(x, y, 200, 50);
        setFocusable(false);
        setVisible(false);

        panelEscenario.add(this);

        Command comando = new AdoptarMascotaCommand(panelEscenario);
        this.addActionListener(e -> comando.execute());

    }
}
