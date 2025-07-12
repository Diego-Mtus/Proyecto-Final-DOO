package org.udec.util.comandos;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.visual.SelectorMascota;

/**
 * Clase que representa un comando para seleccionar una mascota.
 * Implementa la interfaz Command.
 */
public class SeleccionarMascotaCommand implements Command {
    private final MascotasEnum mascota;
    private final SelectorMascota selector;

    /**
     * Constructor de la clase SeleccionarMascotaCommand.
     *
     * @param mascota  La mascota a seleccionar, representada por un valor del enum MascotasEnum.
     * @param selector El selector de mascotas donde se realizará la selección.
     */
    public SeleccionarMascotaCommand(MascotasEnum mascota, SelectorMascota selector) {
        this.mascota = mascota;
        this.selector = selector;
    }

    /**
     * Método que ejecuta el comando de selección de mascota,
     * estableciendo la mascota seleccionada en el selector y cerrando el selector.
     */
    @Override
    public void execute() {
        selector.setMascotaSeleccionada(mascota);
        selector.dispose();
    }
}