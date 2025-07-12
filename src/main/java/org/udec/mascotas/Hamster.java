package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

/**
 * Clase que representa un hamster como mascota.
 * Hereda de la clase Mascota y define su estado inicial.
 */
public class Hamster extends Mascota{
    Hamster(){
        super(MascotasEnum.HAMSTER);
        this.estado = new Estado(NivelDecrementoEnum.LENTO, NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.RAPIDO);
    }
}
