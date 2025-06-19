package org.udec.mascotas;

import org.udec.GeneradorNombreAleatorio;

public class Golondrina extends Mascota{
    Golondrina(){
        this.nombreAnimal = MascotasEnum.GOLONDRINA.getNombre();
        this.tipo = MascotasEnum.GOLONDRINA.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
    }
}
