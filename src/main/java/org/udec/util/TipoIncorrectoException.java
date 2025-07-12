package org.udec.util;

/**
 * Excepción personalizada que se lanza cuando el tipo de mascota no es compatible con el escenario.
 * Esta excepción extiende la clase Exception.
 */
public class TipoIncorrectoException extends Exception {
    public TipoIncorrectoException() {
        super("El tipo de mascota no es compatible con el escenario.");
    }
}
