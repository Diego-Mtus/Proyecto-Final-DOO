package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

/**
 * Clase que representa un pez dorado como mascota.
 * Hereda de la clase Mascota y define su estado inicial.
 */
public class PezDorado extends Mascota{
    PezDorado(){
        super(MascotasEnum.PEZDORADO);
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.MEDIO);
    }
}
