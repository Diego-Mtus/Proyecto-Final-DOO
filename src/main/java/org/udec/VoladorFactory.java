package org.udec;

import org.udec.escenarios.*;
import org.udec.mascotas.*;


public class VoladorFactory implements HabitatFactory{
    @Override
    public Escenario crearEscenario() {
        return new EscenarioVolador();
    }

    @Override
    public Mascota crearMascota(MascotasEnum mascota) {
        if(TiposEnum.VOLADOR.mascotasCompatibles().contains(mascota)){
            return mascota.crearMascota();
        }
        return null;
    }
}
