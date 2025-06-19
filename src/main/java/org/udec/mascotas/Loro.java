package org.udec.mascotas;

import org.udec.GeneradorNombreAleatorio;

public class Loro extends Mascota {
    Loro(){
        this.nombreAnimal = MascotasEnum.LORO.getNombre();
        this.tipo = MascotasEnum.LORO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
    }
}
