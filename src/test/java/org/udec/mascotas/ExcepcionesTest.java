package org.udec.mascotas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.udec.escenarios.Escenario;
import org.udec.escenarios.EscenarioFactory;
import org.udec.util.MascotaViviendoException;
import org.udec.util.TipoIncorrectoException;
import org.udec.util.enumerations.TiposEnum;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExcepcionesTest {

    // Se probará que las excepciones se lancen correctamente al crear mascotas
    // que no son del tipo esperado o cuando ya hay una mascota en el escenario.

    private Escenario escenario;

    @BeforeEach
    void setUp() {
        escenario = EscenarioFactory.crearEscenario(TiposEnum.COMUN);
    }

    @Test
    void crearMascotaConTipoIncorrectoTest() {
        // Intentar crear una mascota de tipo incorrecto
        LoroFactory loroFactory = new LoroFactory();
        assertThrows(TipoIncorrectoException.class, () -> {
            loroFactory.crearMascota(escenario);
        });
    }

    @Test
    void crearMascotaConMascotaViviendoTest() {
        MascotaFactory mascotaFactory = new GatoFactory();
        try {
            mascotaFactory.crearMascota(escenario);
        } catch (Exception e) {
            // No se espera que se lance una excepción aquí
        }

        // Intentar crear otra mascota cuando ya hay una
        assertThrows(MascotaViviendoException.class, () -> {
            mascotaFactory.crearMascota(escenario);
        });

    }

}
