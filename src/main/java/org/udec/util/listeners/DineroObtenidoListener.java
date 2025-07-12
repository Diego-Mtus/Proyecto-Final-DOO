package org.udec.util.listeners;

/**
 * Interfaz que define un listener para recibir eventos de dinero obtenido.
 */
public interface DineroObtenidoListener {
    /**
     * Método que se llama cuando se obtiene una cantidad de dinero.
     *
     * @param dineroObtenido La cantidad de dinero obtenida.
     */
    void dineroObtenido(int dineroObtenido);
}
