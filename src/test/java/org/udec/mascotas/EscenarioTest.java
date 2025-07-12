package org.udec.mascotas;

import org.junit.jupiter.api.Test;
import org.udec.escenarios.Escenario;
import org.udec.escenarios.EscenarioFactory;
import org.udec.util.enumerations.TiposEnum;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EscenarioTest {

    @Test
    void imagenesEscenarioExistenTest() {
        // Prueba para verificar que las imágenes del escenario se cargan correctamente
        Escenario escenario = EscenarioFactory.crearEscenario(TiposEnum.COMUN);
        assertNotNull(escenario.getImagenEscenario(), "El escenario común no tiene imagen asignada.");
        escenario = EscenarioFactory.crearEscenario(TiposEnum.ROEDOR);
        assertNotNull(escenario.getImagenEscenario(), "El escenario de roedor no tiene imagen asignada.");
        escenario = EscenarioFactory.crearEscenario(TiposEnum.VOLADOR);
        assertNotNull(escenario.getImagenEscenario(), "El escenario volador no tiene imagen asignada.");
        escenario = EscenarioFactory.crearEscenario(TiposEnum.ACUATICO);
        assertNotNull(escenario.getImagenEscenario(), "El escenario acuático no tiene imagen asignada.");

    }

    @Test
    void venderMascotaQuitaMascotaTest() {
        // Prueba para verificar que al vender una mascota, esta se elimina del escenario
        Escenario escenario = EscenarioFactory.crearEscenario(TiposEnum.COMUN);
        MascotaFactory mascotaFactory = new GatoFactory();
        try {
            mascotaFactory.crearMascota(escenario);
        } catch (Exception e) {
            // No se espera que se lance una excepción aquí
        }

        assertNotNull(escenario.getMascotaActual(), "El escenario no tiene una mascota actual.");

        // Vender la mascota
        escenario.venderMascota();

        assertNull(escenario.getMascotaActual(), "La mascota no fue eliminada correctamente del escenario.");
    }
}
