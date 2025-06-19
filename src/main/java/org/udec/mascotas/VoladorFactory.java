package org.udec.mascotas;

import org.udec.TiposEnum;


public class VoladorFactory implements HabitatFactory{
    @Override
    public Escenario crearEscenario() {
        return new EscenarioVolador();
    }

    @Override
    public void crearMascotaParaEscenario(MascotasEnum mascota, Escenario escenario) {
        if(TiposEnum.VOLADOR.mascotasCompatibles().contains(mascota)){
            escenario.alojarMascota(mascota.crearMascota());
        }
    }
}
