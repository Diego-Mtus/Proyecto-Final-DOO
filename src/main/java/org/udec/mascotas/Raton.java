package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

/**
 * Clase que representa un ratón como mascota.
 * Hereda de la clase Mascota y define su estado inicial.
 */
public class Raton extends Mascota{
    Raton(){
        super(MascotasEnum.RATON);
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO);
    }
}
