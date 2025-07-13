package org.udec.util;

/**
 * Excepción personalizada que se lanza cuando se intenta agregar una mascota
 * a un escenario que ya tiene una mascota viviendo en él.
 * Esta excepción extiende la clase Exception.
 */
public class MascotaViviendoException extends Exception {
    /**
     * Excepción personalizada que se lanza cuando se intenta agregar una mascota
     * a un escenario que ya tiene una mascota viviendo en él.
     * Esta excepción extiende la clase Exception.
     */
    public MascotaViviendoException() {
        super("Ya existe una mascota viviendo en el escenario.");
    }
}
