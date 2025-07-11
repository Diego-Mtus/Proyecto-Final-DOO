package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;


public class PezDorado extends Mascota{
    PezDorado(){
        super(MascotasEnum.PEZDORADO);
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.MEDIO);
    }
}
