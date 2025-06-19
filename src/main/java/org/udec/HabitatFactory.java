package org.udec;

import org.udec.escenarios.Escenario;
import org.udec.mascotas.Mascota;
import org.udec.mascotas.MascotasEnum;

public interface HabitatFactory {
    Escenario crearEscenario();
    Mascota crearMascota(MascotasEnum mascota);
}
