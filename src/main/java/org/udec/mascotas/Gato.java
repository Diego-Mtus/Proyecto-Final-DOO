package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

/**
 * Clase que representa un gato como mascota.
 * Hereda de la clase Mascota y define su estado inicial.
 */
public class Gato extends Mascota{
    Gato(){
        super(MascotasEnum.GATO);
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO);
    }


}
