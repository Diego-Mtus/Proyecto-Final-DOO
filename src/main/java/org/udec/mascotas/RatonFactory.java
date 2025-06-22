package org.udec.mascotas;

import org.jetbrains.annotations.NotNull;
import org.udec.escenarios.Escenario;
import org.udec.util.MascotaViviendoException;
import org.udec.util.MascotasEnum;
import org.udec.util.TipoIncorrectoException;

public class RatonFactory extends MascotaFactory {

    @Override
    public void crearMascota(@NotNull Escenario escenario) throws MascotaViviendoException, TipoIncorrectoException {

        if(!escenario.puedeAlojar(MascotasEnum.RATON)) {
            throw new TipoIncorrectoException();
        }

        if(escenario.tieneMascota()){
            throw new MascotaViviendoException();
        }

        escenario.alojarMascota(new Golondrina());

    }
}
