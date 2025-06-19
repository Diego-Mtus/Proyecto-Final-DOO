package org.udec.mascotas;

import org.udec.TiposEnum;


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
