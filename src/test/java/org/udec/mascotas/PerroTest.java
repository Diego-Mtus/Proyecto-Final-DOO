package org.udec.mascotas;

import org.udec.util.enumerations.TiposEnum;

class PerroTest extends MascotasTestBase{

    @Override
    MascotaFactory getMascotaFactory() {
        return new PerroFactory();
    }

    @Override
    TiposEnum getTipoMascota() {
        return TiposEnum.COMUN;
    }
}