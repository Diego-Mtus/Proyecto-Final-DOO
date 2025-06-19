package org.udec.mascotas;

public interface HabitatFactory {
    Escenario crearEscenario();
    Mascota crearMascota(MascotasEnum mascota);
}
