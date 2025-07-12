package org.udec.mascotas;

import org.udec.util.enumerations.TiposEnum;

public class PezPayasoTest extends MascotasTestBase{

    @Override
    MascotaFactory getMascotaFactory() {
        return new PezPayasoFactory();
    }

    @Override
    TiposEnum getTipoMascota() {
        return TiposEnum.ACUATICO;
    }
}
