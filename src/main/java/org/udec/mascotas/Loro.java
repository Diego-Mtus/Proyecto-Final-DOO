package org.udec.mascotas;

import org.udec.GeneradorNombreAleatorio;
import org.udec.TiposEnum;

public class Loro extends Mascota {
    public Loro(){
        this.nombreAnimal = "Loro";
        this.tipo = TiposEnum.VOLADOR;
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
    }
}
