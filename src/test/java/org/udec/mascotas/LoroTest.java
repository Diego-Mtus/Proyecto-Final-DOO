package org.udec.mascotas;

import org.udec.util.enumerations.TiposEnum;

public class LoroTest extends MascotasTestBase{
    @Override
    MascotaFactory getMascotaFactory() {
        return new LoroFactory();
    }

    @Override
    TiposEnum getTipoMascota() {
        return TiposEnum.VOLADOR;
    }
}
