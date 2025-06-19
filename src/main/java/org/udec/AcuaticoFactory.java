package org.udec;

import org.udec.escenarios.*;
import org.udec.mascotas.*;

public class AcuaticoFactory implements HabitatFactory{
    @Override
    public Escenario crearEscenario() {
        return new EscenarioAcuatico();
    }

    @Override
    public Mascota crearMascota(MascotasEnum mascota) {
        if(TiposEnum.ACUATICO.mascotasCompatibles().contains(mascota)){
            return mascota.crearMascota();
        }
        return null;
    }
}
