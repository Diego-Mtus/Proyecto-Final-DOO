package org.udec.mascotas;

import org.udec.ExtractorNombreAleatorio;
import org.udec.TiposEnum;

public class Hamster extends Mascota{
    public Hamster(){
        this.nombreAnimal = "Hamster";
        this.tipo = TiposEnum.ROEDOR;
        this.nombrePropio = ExtractorNombreAleatorio.obtenerNombreAleatorio();
    }
}
