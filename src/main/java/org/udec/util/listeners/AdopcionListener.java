package org.udec.util.listeners;


import org.udec.visual.PanelEscenario;

/**
 * Interfaz que define los métodos para manejar eventos de adopción y venta de mascotas.
 * Implementada por clases que desean reaccionar a estos eventos.
 */
public interface AdopcionListener {
    /**
     * Método que se llama cuando se realiza una adopción de mascota.
     * @param panelEscenario El panel del escenario donde se realiza la adopción.
     */
    void adopcionRealizada(PanelEscenario panelEscenario);
    /**
     * Método que se llama cuando se realiza una venta de mascota.
     * @param dineroObtenido La cantidad de dinero obtenida por la venta de la mascota.
     */
    void ventaMascotaRealizada(int dineroObtenido);
}
