package org.udec.mascotas;

import org.udec.TiposEnum;


public class ComunFactory implements HabitatFactory{
    @Override
    public Escenario crearEscenario() {
        return new EscenarioComun();
    }

    @Override
    public void crearMascotaParaEscenario(MascotasEnum mascota, Escenario escenario) {
        if(TiposEnum.COMUN.mascotasCompatibles().contains(mascota)){
            escenario.alojarMascota(mascota.crearMascota());
        }

    }
}
