package org.udec.mascotas;

import org.udec.util.enumerations.TiposEnum;

public class GolondrinaTest extends MascotasTestBase{
    @Override
    MascotaFactory getMascotaFactory() {
        return new GolondrinaFactory();
    }

    @Override
    TiposEnum getTipoMascota() {
        return TiposEnum.VOLADOR;
    }
}
