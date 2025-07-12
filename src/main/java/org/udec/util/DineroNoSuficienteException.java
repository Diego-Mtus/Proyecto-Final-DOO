package org.udec.util;

/**
 * Excepción personalizada que se lanza cuando el dinero disponible no es suficiente
 * para realizar una operación o compra en el juego.
 * Esta excepción extiende de la clase Exception.
 */
public class DineroNoSuficienteException extends Exception {
    public DineroNoSuficienteException() {
      super("El dinero no es suficiente.");
    }
}
