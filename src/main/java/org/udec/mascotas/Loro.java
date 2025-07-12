package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

/**
 * Clase que representa un loro como mascota.
 * Hereda de la clase Mascota y define su estado inicial.
 */
public class Loro extends Mascota {
    Loro(){
        super(MascotasEnum.LORO);
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO);
    }
}
