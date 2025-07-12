package org.udec.mascotas;

import org.udec.util.enumerations.TiposEnum;

public class PezDoradoTest extends MascotasTestBase{
    @Override
    MascotaFactory getMascotaFactory() {
        return new PezDoradoFactory();
    }

    @Override
    TiposEnum getTipoMascota() {
        return TiposEnum.ACUATICO;
    }
}
