package org.udec.mascotas;

import org.udec.escenarios.Escenario;
import org.udec.util.MascotaViviendoException;
import org.udec.util.TipoIncorrectoException;

/**
 * Clase abstracta que define el método para crear una mascota en un escenario.
 * Las subclases deben implementar este método para crear diferentes tipos de mascotas.
 * Esta clase es parte del patrón de diseño Factory.
 */
public abstract class MascotaFactory {

    /**
     * Método que crea una mascota en el escenario proporcionado.
     *
     * @param escenario El escenario donde se alojará la mascota.
     * @throws MascotaViviendoException Si ya hay una mascota viviendo en el escenario.
     * @throws TipoIncorrectoException Si el escenario no puede alojar un Gato.
     */
    public abstract void crearMascota (Escenario escenario) throws MascotaViviendoException, TipoIncorrectoException;

}