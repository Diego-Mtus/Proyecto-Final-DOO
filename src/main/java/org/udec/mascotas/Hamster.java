package org.udec.mascotas;

import org.udec.GeneradorNombreAleatorio;


public class Hamster extends Mascota{
    public Hamster(){
        this.nombreAnimal = MascotasEnum.HAMSTER.getNombre();
        this.tipo = MascotasEnum.HAMSTER.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
    }
}
