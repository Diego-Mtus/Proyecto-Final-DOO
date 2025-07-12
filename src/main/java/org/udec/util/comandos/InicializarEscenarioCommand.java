package org.udec.util.comandos;

import org.udec.visual.PanelEscenario;
import org.udec.visual.SelectorEscenario;

/**
 * Comando para inicializar el escenario en la aplicación.
 * Este comando abre un panel de selección de escenario y establece el escenario seleccionado en el panel.
 * Si se selecciona un escenario con dinero suficiente, se oculta el botón de inicialización y se muestra el botón para adoptar una mascota.
 * Implementa la interfaz Command.
 */
public class InicializarEscenarioCommand implements Command{

    private final PanelEscenario panelEscenario;


    /**
     * Constructor que recibe el panel de escenario donde se realizará la inicialización.
     *
     * @param panelEscenario El panel de escenario donde se establecerá el escenario seleccionado.
     */
    public InicializarEscenarioCommand(PanelEscenario panelEscenario) {
        this.panelEscenario = panelEscenario;
    }

    /**
     * Método que ejecuta el comando para inicializar el escenario.
     * Abre un selector de escenario y establece el escenario seleccionado en el panel.
     * Si se selecciona un escenario con dinero suficiente, oculta el botón de inicialización y muestra el botón para adoptar una mascota.
     */
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
