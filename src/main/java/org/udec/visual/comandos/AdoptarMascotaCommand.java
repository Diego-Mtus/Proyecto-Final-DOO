package org.udec.visual.comandos;

import org.udec.mascotas.MascotaFactory;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.visual.PanelEscenario;
import org.udec.visual.SelectorMascota;
import org.udec.visual.VentanaPrincipal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class AdoptarMascotaCommand implements Command{

    private final PanelEscenario panelEscenario;

    public AdoptarMascotaCommand(PanelEscenario panelEscenario) {
        this.panelEscenario = panelEscenario;
    }

    @Override
    public void execute() {
        System.out.println("Se abre panel de adopción de mascota");
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
            System.out.println(Arrays.toString(factories));
            return (MascotaFactory) factories[0].newInstance();
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.getMessage();
        }
        return null;
    }
}
