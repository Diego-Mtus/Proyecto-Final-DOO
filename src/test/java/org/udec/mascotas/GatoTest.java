package org.udec.mascotas;

import org.udec.util.enumerations.TiposEnum;

public class GatoTest extends MascotasTestBase {

    @Override
    MascotaFactory getMascotaFactory() {
        return new GatoFactory();
    }

    @Override
    TiposEnum getTipoMascota() {
        return TiposEnum.COMUN;
    }
}
