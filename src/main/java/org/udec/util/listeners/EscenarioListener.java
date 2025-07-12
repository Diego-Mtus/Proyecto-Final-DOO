package org.udec.util.listeners;

import org.udec.visual.PanelEscenario;

/**
 * Interfaz que define un listener para eventos relacionados con la inicialización de un escenario.
 * Permite a los objetos interesados recibir notificaciones cuando un escenario es inicializado.
 */
public interface EscenarioListener {
    /**
     * Método que se llama cuando un escenario ha sido inicializado.
     *
     * @param panel El panel del escenario que ha sido inicializado.
     */
    void escenarioInicializado(PanelEscenario panel);
}