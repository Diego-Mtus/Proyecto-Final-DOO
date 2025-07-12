package org.udec.mascotas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.udec.escenarios.Escenario;
import org.udec.escenarios.EscenarioFactory;
import org.udec.util.MascotaViviendoException;
import org.udec.util.TipoIncorrectoException;

import org.udec.util.enumerations.AlimentosEnum;
import org.udec.util.enumerations.MedicinasEnum;
import org.udec.util.enumerations.TiposEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EstadoTest {

    // Se probará el comportamiento de la clase Estado, AlimentosEnum y MedicinasEnum
    private Mascota mascota;

    @BeforeEach
    public void setUp() throws MascotaViviendoException, TipoIncorrectoException {

        // Creamos una mascota arbitraria para probar el estado
        Escenario escenario = EscenarioFactory.crearEscenario(TiposEnum.COMUN);
        MascotaFactory mascotaFactory = new GatoFactory();
        mascotaFactory.crearMascota(escenario);
        mascota = escenario.getMascotaActual();

        // Asignamos el estado inicial de la mascota
        mascota.getEstado().setHambre(50);
        mascota.getEstado().setSalud(50);
        mascota.getEstado().setFelicidad(50);

    }

    // Alimentar con su propia comida añade 20 a hambre
    @Test
    public void AlimentarMascotaConComidaSuyaTest() {
        AlimentosEnum.ALIMENTO_GATO.alimentar(mascota);
        assertEquals(70, mascota.getEstado().verHambre(), "La mascota no se alimentó correctamente con su propia comida.");
    }

    // Alimentar con comida de otro animal de mismo tipo añade 10 a hambre
    @Test
    public void AlimentarMascotaConComidaDeMismoTipoTest() {
        AlimentosEnum.ALIMENTO_PERRO.alimentar(mascota);
        assertEquals(60, mascota.getEstado().verHambre(), "La mascota no se alimentó correctamente con comida de mismo tipo.");
    }

    // Alimentar con comida de otro animal de diferente tipo quita vida y alimenta 5 a hambre
    @Test
    public void AlimentarMascotaConComidaDeOtroTipoTest() {
        AlimentosEnum.ALIMENTO_RATON.alimentar(mascota);
        assertEquals(55, mascota.getEstado().verHambre(), "La mascota no se alimentó correctamente con comida de otro tipo.");
        assertEquals(45, mascota.getEstado().verSalud(), "La salud de la mascota no disminuyó al alimentarla con comida de otro tipo.");
    }

    @Test
    public void MedicarMascotaConMedicinaDeMismoTipoTest() {
        // Medicamos con medicina de su tipo
        MedicinasEnum.MEDICINA_COMUN.curar(mascota);
        assertEquals(70, mascota.getEstado().verSalud(), "La salud de la mascota no aumentó al medicarla con medicina de su tipo.");
    }

    @Test
    public void MedicarMascotaConMedicinaDeOtroTipoTest() {
        // Medicamos con medicina de otro tipo
        MedicinasEnum.MEDICINA_PECES.curar(mascota);
        assertEquals(45, mascota.getEstado().verSalud(), "La salud de la mascota no disminuyó al medicarla con medicina de otro tipo.");
    }

    //
    @Test
    void MedicarMascotaConCuritaTest() {
        // Medicamos con curita
        mascota.getEstado().setHerido(true);
        MedicinasEnum.CURITA_HERIDAS.curar(mascota);
        assertFalse(mascota.getEstado().isHerido(), "La mascota no se curó correctamente con la curita.");
    }

    // Intentar asignar valores negativos a hambre, salud o felicidad no debería cambiar el estado
    @Test
    void NoDeberiaPermitirValoresNegativosEnEstadoTest() {
        mascota.getEstado().setHambre(-10);
        mascota.getEstado().setSalud(-20);
        mascota.getEstado().setFelicidad(-30);

        assertEquals(0, mascota.getEstado().verHambre(), "El hambre no debería ser negativo.");
        assertEquals(0, mascota.getEstado().verSalud(), "La salud no debería ser negativa.");
        assertEquals(0, mascota.getEstado().verFelicidad(), "La felicidad no debería ser negativa.");
    }

    @Test
    void NoDeberiaPermitirValoresSobre100EnEstadoTest() {
        mascota.getEstado().setHambre(120);
        mascota.getEstado().setSalud(150);
        mascota.getEstado().setFelicidad(130);

        assertEquals(100, mascota.getEstado().verHambre(), "El hambre no debería ser mayor a 100.");
        assertEquals(100, mascota.getEstado().verSalud(), "La salud no debería ser mayor a 100.");
        assertEquals(100, mascota.getEstado().verFelicidad(), "La felicidad no debería ser mayor a 100.");
    }

}
