package org.udec.visual;

import org.udec.visual.comandos.Command;
import org.udec.visual.comandos.VenderMascotaCommand;

import javax.swing.*;

public class BotonVenderMascota extends JButton{

    BotonVenderMascota(PanelEscenario panelEscenario, int x, int y) {
        super("Vender Mascota");
        setBounds(x, y, 50, 140);
        setFocusable(false);
        setVisible(false);

        panelEscenario.add(this);

        Command comando = new VenderMascotaCommand(panelEscenario);
        this.addActionListener(_ -> comando.execute());

    }
}
