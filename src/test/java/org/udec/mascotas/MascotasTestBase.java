package org.udec.mascotas;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.udec.escenarios.Escenario;
import org.udec.escenarios.EscenarioFactory;
import org.udec.util.MascotaViviendoException;
import org.udec.util.TipoIncorrectoException;
import org.udec.util.enumerations.TiposEnum;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// Las pruebas unitarias de mascotas específicas es para ver si
// sonido e imagenes cargas correctamente.

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class MascotasTestBase {

    Mascota mascota;
    abstract MascotaFactory getMascotaFactory();
    abstract TiposEnum getTipoMascota();


    @BeforeAll
    void setUp() throws MascotaViviendoException, TipoIncorrectoException {
        Escenario escenario = EscenarioFactory.crearEscenario(getTipoMascota());
        getMascotaFactory().crearMascota(escenario);
        mascota = escenario.getMascotaActual();
    }

    @Test
    void testReconoceImagen() {
        assertNotNull(mascota.getImagenMascota(), "La mascota no tiene imagen asignada.");
    }

    @Test
    void testReconoceImagenJuego() {
        assertNotNull(mascota.getImagenMascotaJuego(), "La mascota no tiene imagen de juego asignada.");
    }

    @Test
    void testReconoceSonido() {
        assertNotNull(mascota.getSonidoMascota(), "La mascota no tiene sonido asignado.");
    }

}
