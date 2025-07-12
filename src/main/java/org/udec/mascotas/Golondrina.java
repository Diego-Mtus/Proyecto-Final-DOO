package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

/**
 * Clase que representa una golondrina como mascota.
 * Hereda de la clase Mascota y define su estado inicial.
 */
public class Golondrina extends Mascota{
    Golondrina(){
        super(MascotasEnum.GOLONDRINA);
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.MEDIO);
    }
}
