package org.udec.mascotas;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

/**
 * Clase que representa un pez payaso como mascota.
 * Hereda de la clase Mascota y define su estado inicial.
 */
public class PezPayaso extends Mascota{
    PezPayaso(){
        super(MascotasEnum.PEZPAYASO);
        this.estado = new Estado(NivelDecrementoEnum.LENTO, NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.RAPIDO);

    }
}
