package org.udec.mascotas;

import org.udec.util.enumerations.TiposEnum;

public class RatonTest extends MascotasTestBase{
    @Override
    MascotaFactory getMascotaFactory() {
        return new RatonFactory();
    }

    @Override
    TiposEnum getTipoMascota() {
        return TiposEnum.ROEDOR;
    }
}
