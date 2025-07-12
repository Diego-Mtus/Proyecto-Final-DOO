package org.udec.visual;

import org.udec.util.comandos.Command;
import org.udec.util.comandos.VenderMascotaCommand;
import org.udec.util.enumerations.BotonesUI;

import javax.swing.*;

public class BotonVenderMascota extends JButtonAnimado{

    BotonVenderMascota(PanelEscenario panelEscenario, int x, int y) {
        super(BotonesUI.BOTON_VENDERMASCOTA.getImagen(), x, y, 50, 140);
        setVisible(false);

        panelEscenario.add(this);

        Command comando = new VenderMascotaCommand(panelEscenario);
        this.addActionListener(_ -> comando.execute());

    }
}
