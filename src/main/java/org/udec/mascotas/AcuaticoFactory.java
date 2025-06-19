package org.udec.mascotas;

import org.udec.TiposEnum;

public class AcuaticoFactory implements HabitatFactory{
    @Override
    public Escenario crearEscenario() {
        return new EscenarioAcuatico();
    }

    @Override
    public void crearMascotaParaEscenario(MascotasEnum mascota, Escenario escenario) {
        if(TiposEnum.ACUATICO.mascotasCompatibles().contains(mascota)){
            escenario.alojarMascota(mascota.crearMascota());
        }
    }
}
