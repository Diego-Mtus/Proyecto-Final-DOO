package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

public class Gato extends Mascota{
    Gato(){
        super(MascotasEnum.GATO);
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO);
    }


}
