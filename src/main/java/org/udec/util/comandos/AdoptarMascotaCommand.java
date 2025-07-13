package org.udec.util.comandos;

import org.udec.mascotas.MascotaFactory;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.visual.PanelEscenario;
import org.udec.visual.SelectorMascota;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Comando para adoptar una mascota.
 * Este comando abre un panel de selección de mascota y, si se selecciona una mascota,
 * establece la mascota en el panel de escenario y oculta el botón de adopción.
 * Implementa la interfaz Command.
 */
public class AdoptarMascotaCommand implements Command{

    private final PanelEscenario panelEscenario;

    /**
     * Constructor que inicializa el comando con el panel de escenario.
     *
     * @param panelEscenario El panel de escenario donde se mostrará la mascota adoptada.
     */
    public AdoptarMascotaCommand(PanelEscenario panelEscenario) {
        this.panelEscenario = panelEscenario;
    }

    /**
     * Método que ejecuta el comando de adopción de mascota.
     * Abre un panel de selección de mascota y, si se selecciona una mascota,
     * establece la mascota en el panel de escenario y oculta el botón de adopción.
     */
    @Override
    public void execute() {
        SelectorMascota selectorMascota = new SelectorMascota(panelEscenario);
        if(selectorMascota.getMascotaSeleccionada() != null){
            MascotaFactory mascotaFactory = seleccionDinamicaDeFactory(selectorMascota.getMascotaSeleccionada());
            panelEscenario.establecerMascota(mascotaFactory);
            panelEscenario.ocultarBotonAdoptarMascota();
            panelEscenario.inicializarHiloCompradorInteresado();

        }
    }

    private MascotaFactory seleccionDinamicaDeFactory(MascotasEnum mascotasEnum){
        try {
            Constructor<?>[] factories = Class.forName("org.udec.mascotas." + mascotasEnum.getNombreFactory()).getConstructors();
            return (MascotaFactory) factories[0].newInstance();
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.getMessage();
        }
        return null;
    }
}
