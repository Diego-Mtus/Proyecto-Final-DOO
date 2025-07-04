package org.udec.util;

public class DineroNoSuficienteException extends Exception {
    public DineroNoSuficienteException() {
      super("El dinero no es suficiente.");
    }
}
