package org.udec;

import org.udec.escenarios.*;
import org.udec.mascotas.*;


public class ComunFactory implements HabitatFactory{
    @Override
    public Escenario crearEscenario() {
        return new EscenarioComun();
    }

    @Override
    public Mascota crearMascota(MascotasEnum mascota) {
        if(TiposEnum.COMUN.mascotasCompatibles().contains(mascota)){
            return mascota.crearMascota();
        }
        return null;
    }
}
