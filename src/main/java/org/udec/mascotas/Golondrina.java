package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

public class Golondrina extends Mascota{
    Golondrina(){
        super(MascotasEnum.GOLONDRINA);
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.MEDIO);
    }
}
