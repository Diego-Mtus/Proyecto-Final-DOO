// src/main/java/org/udec/visual/comandos/SeleccionarMascotaCommand.java
package org.udec.util.comandos;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.visual.SelectorMascota;

public class SeleccionarMascotaCommand implements Command {
    private final MascotasEnum mascota;
    private final SelectorMascota selector;

    public SeleccionarMascotaCommand(MascotasEnum mascota, SelectorMascota selector) {
        this.mascota = mascota;
        this.selector = selector;
    }

    @Override
    public void execute() {
        selector.setMascotaSeleccionada(mascota);
        selector.dispose();
    }
}