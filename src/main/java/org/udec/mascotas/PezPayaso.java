package org.udec.mascotas;

import org.udec.GeneradorNombreAleatorio;

public class PezPayaso extends Mascota{
    PezPayaso(){
        this.nombreAnimal = MascotasEnum.PEZPAYASO.getNombre();
        this.tipo = MascotasEnum.PEZPAYASO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
    }
}
