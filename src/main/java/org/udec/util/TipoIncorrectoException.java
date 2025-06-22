package org.udec.util;

public class TipoIncorrectoException extends Exception {
    public TipoIncorrectoException() {
        super("El tipo de mascota no es compatible con el escenario.");
    }
}
