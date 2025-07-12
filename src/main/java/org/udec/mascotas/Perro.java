package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

/**
 * Clase que representa un perro como mascota.
 * Hereda de la clase Mascota y define su estado inicial.
 */
public class Perro extends Mascota{
    Perro(){
        super(MascotasEnum.PERRO);
        this.estado = new Estado(NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.MEDIO);
    }
}
