package org.udec.mascotas;

import org.udec.GeneradorNombreAleatorio;
import org.udec.TiposEnum;

public class Golondrina extends Mascota{

    public Golondrina(){
        this.nombreAnimal = "Golondrina";
        this.tipo = TiposEnum.VOLADOR;
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
    }
}
