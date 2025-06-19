package org.udec.mascotas;

public interface HabitatFactory {
    Escenario crearEscenario();
    void crearMascotaParaEscenario(MascotasEnum mascota, Escenario escenario);
}
