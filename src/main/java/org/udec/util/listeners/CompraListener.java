package org.udec.util.listeners;

import org.udec.util.DineroNoSuficienteException;

/**
 * Interfaz que define un listener para la acción de compra.
 * Permite realizar una compra si se tiene suficiente dinero.
 */
public interface CompraListener {
    /**
     * Método para realizar una compra.
     *
     * @param precio El precio del producto o servicio a comprar.
     * @throws DineroNoSuficienteException Si el usuario no tiene suficiente dinero para realizar la compra.
     */
    void comprar(int precio) throws DineroNoSuficienteException;
}
