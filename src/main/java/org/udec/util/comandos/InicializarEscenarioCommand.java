package org.udec.util.comandos;

import org.udec.visual.PanelEscenario;
import org.udec.visual.SelectorEscenario;

public class InicializarEscenarioCommand implements Command{

    private final PanelEscenario panelEscenario;

    public InicializarEscenarioCommand(PanelEscenario panelEscenario) {
        this.panelEscenario = panelEscenario;
    }

    @Override
    public void execute() {
        System.out.println("Se abre panel de seleccion de escenario");
        SelectorEscenario selectorEscenario = new SelectorEscenario(panelEscenario);
        if(selectorEscenario.getEscenarioSeleccionado() != null) {
            panelEscenario.establecerEscenario(selectorEscenario.getEscenarioSeleccionado());
            panelEscenario.ocultarBotonInicializarEscenario();
            panelEscenario.mostrarBotonAdoptarMascota();
        }
    }
}
