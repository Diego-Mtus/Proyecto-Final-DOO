package org.udec.visual;

import org.udec.util.comandos.AdoptarMascotaCommand;
import org.udec.util.comandos.Command;
import org.udec.util.enumerations.BotonesUI;


public class BotonAdoptarMascota extends JButtonAnimado {

    public BotonAdoptarMascota(PanelEscenario panelEscenario, int x, int y) {
        super(BotonesUI.BOTON_ADOPTARMASCOTA.getImagen(), x, y, 240, 90);
        setVisible(true);

        panelEscenario.add(this);

        Command comando = new AdoptarMascotaCommand(panelEscenario);
        this.addActionListener(e -> comando.execute());

    }
}
