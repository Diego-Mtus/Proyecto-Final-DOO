package org.udec.mascotas;

import org.udec.escenarios.Escenario;
import org.udec.util.MascotaViviendoException;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.TipoIncorrectoException;

/**
 * Clase que representa una fábrica para crear instancias de Loro.
 * Extiende de MascotaFactory y proporciona la implementación específica
 * para crear un Loro en un escenario dado.
 */
public class LoroFactory extends MascotaFactory {

    /**
     * Método que crea una mascota de tipo Loro en el escenario proporcionado.
     *
     * @param escenario El escenario donde se alojará la mascota.
     * @throws MascotaViviendoException Si ya hay una mascota viviendo en el escenario.
     * @throws TipoIncorrectoException Si el escenario no puede alojar un Loro.
     */
    @Override
    public void crearMascota(Escenario escenario) throws MascotaViviendoException, TipoIncorrectoException {

        if(!escenario.puedeAlojar(MascotasEnum.LORO)) {
            throw new TipoIncorrectoException();
        }

        if(escenario.tieneMascota()){
            throw new MascotaViviendoException();
        }

        escenario.alojarMascota(new Loro());

    }
}
