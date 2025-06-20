package org.udec.mascotas;

import org.udec.GeneradorNombreAleatorio;

public class Gato extends Mascota{
    Gato(){
        this.nombreAnimal = MascotasEnum.GATO.getNombre();
        this.tipo = MascotasEnum.GATO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(100,100,150);
    }


}
