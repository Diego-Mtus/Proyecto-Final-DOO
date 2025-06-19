package org.udec;

import org.udec.escenarios.*;
import org.udec.mascotas.*;


public class RoedorFactory implements HabitatFactory{
    @Override
    public Escenario crearEscenario() {
        return new EscenarioRoedor();
    }

    @Override
    public Mascota crearMascota(MascotasEnum mascota) {
        if(TiposEnum.ROEDOR.mascotasCompatibles().contains(mascota)){
            return mascota.crearMascota();
        }
        return null;
    }
}
