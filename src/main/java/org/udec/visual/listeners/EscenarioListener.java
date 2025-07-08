package org.udec.visual.listeners;

import org.udec.visual.PanelEscenario;

// Para que el PanelEscenario pueda notificar a la VentanaPrincipal cuando exista un escenario ahí.
public interface EscenarioListener {
    void escenarioInicializado(PanelEscenario panel);
}