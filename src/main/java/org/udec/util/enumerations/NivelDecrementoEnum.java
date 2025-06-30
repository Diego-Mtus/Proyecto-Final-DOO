package org.udec.util.enumerations;

public enum NivelDecrementoEnum {
    LENTO(2),
    MEDIO(3),
    RAPIDO(5);

    private final int valor;

    NivelDecrementoEnum(int valor){
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
