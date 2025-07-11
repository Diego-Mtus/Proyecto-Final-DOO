package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

public class Perro extends Mascota{
    Perro(){
        super(MascotasEnum.PERRO);
        this.estado = new Estado(NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.MEDIO);
    }
}
