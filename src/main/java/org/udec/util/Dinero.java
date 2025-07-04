package org.udec.util;

public class Dinero {
    private int cantidad;

    public Dinero(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void agregar(int cantidad) {
        this.cantidad += cantidad;
    }

    public void restar(int cantidad) throws DineroNoSuficienteException{
        if (this.cantidad >= cantidad) {
            this.cantidad -= cantidad;
        } else {
            throw new DineroNoSuficienteException();
        }
    }
}
