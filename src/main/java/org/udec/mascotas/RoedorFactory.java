package org.udec.mascotas;

import org.udec.TiposEnum;


public class RoedorFactory implements HabitatFactory{
    @Override
    public Escenario crearEscenario() {
        return new EscenarioRoedor();
    }

    @Override
    public void crearMascotaParaEscenario(MascotasEnum mascota, Escenario escenario) {
        if(TiposEnum.ROEDOR.mascotasCompatibles().contains(mascota)){
            escenario.alojarMascota(mascota.crearMascota());
        }
    }
}
