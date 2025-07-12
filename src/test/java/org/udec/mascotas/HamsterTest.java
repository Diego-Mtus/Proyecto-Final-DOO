package org.udec.mascotas;

import org.udec.util.enumerations.TiposEnum;

public class HamsterTest extends MascotasTestBase{
    @Override
    MascotaFactory getMascotaFactory() {
        return new HamsterFactory();
    }

    @Override
    TiposEnum getTipoMascota() {
        return TiposEnum.ROEDOR;
    }
}
