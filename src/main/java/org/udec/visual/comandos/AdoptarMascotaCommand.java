package org.udec.visual.comandos;

import org.udec.visual.PanelEscenario;
import org.udec.visual.SelectorMascota;

public class AdoptarMascotaCommand implements Command{

    private final PanelEscenario panelEscenario;

    public AdoptarMascotaCommand(PanelEscenario panelEscenario) {
        this.panelEscenario = panelEscenario;
    }

    @Override
    public void execute() {
        System.out.println("Se abre panel de adopción de mascota");
        SelectorMascota selectorMascota = new SelectorMascota(panelEscenario);
        if(selectorMascota.isMascotaSeleccionada()) {
            panelEscenario.ocultarBotonAdoptarMascota();
            panelEscenario.inicializarHiloActualizadorDeEstado();
            panelEscenario.inicializarHiloCompradorInteresado();
        }
    }
}
