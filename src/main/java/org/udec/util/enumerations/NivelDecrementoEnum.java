package org.udec.util.enumerations;

/**
 * Enumeración que define los niveles de decremento de estados para las mascotas.
 * Cada nivel tiene un valor asociado que indica la velocidad de decremento.
 */
public enum NivelDecrementoEnum {
    LENTO(2),
    MEDIO(3),
    RAPIDO(5);

    private final int valor;

    /**
     * Constructor para inicializar el nivel de decremento con un valor específico.
     *
     * @param valor El valor asociado al nivel de decremento.
     */
    NivelDecrementoEnum(int valor){
        this.valor = valor;
    }

    /**
     * Método para obtener el valor asociado al nivel de decremento.
     *
     * @return El valor del nivel de decremento.
     */
    public int getValor() {
        return valor;
    }
}
