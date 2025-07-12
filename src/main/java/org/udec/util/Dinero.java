package org.udec.util;

/**
 * Clase que representa una cantidad de dinero.
 * Permite agregar y restar dinero, y lanza una excepción si no hay suficiente dinero para restar.
 */

public class Dinero {
    private int cantidad;

    /**
     * Constructor que inicializa la cantidad de dinero.
     *
     * @param cantidad La cantidad inicial de dinero.
     */
    public Dinero(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Método para obtener la cantidad de dinero actual.
     *
     * @return La cantidad de dinero actual.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Método para agregar una cantidad de dinero.
     *
     * @param cantidad La cantidad de dinero a agregar.
     */
    public void agregar(int cantidad) {
        if(cantidad > 0) {
            this.cantidad += cantidad;
        }
    }

    /**
     * Método para restar una cantidad de dinero.
     *
     * @param cantidad La cantidad de dinero a restar.
     * @throws DineroNoSuficienteException Si la cantidad a restar es mayor que la cantidad actual.
     */
    public void restar(int cantidad) throws DineroNoSuficienteException{
        if (this.cantidad >= cantidad) {
            this.cantidad -= cantidad;
        } else {
            throw new DineroNoSuficienteException();
        }
    }
}
