package org.udec.mascotas;

import org.udec.ExtractorNombreAleatorio;
import org.udec.TiposEnum;

public class Perro extends Mascota{
    public Perro(){
        this.nombreAnimal = "Perro";
        this.tipo = TiposEnum.COMUN;
        this.nombrePropio = ExtractorNombreAleatorio.obtenerNombreAleatorio();
    }
}
