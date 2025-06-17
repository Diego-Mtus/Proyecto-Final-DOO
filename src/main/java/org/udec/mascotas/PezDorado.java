package org.udec.mascotas;

import org.udec.GeneradorNombreAleatorio;
import org.udec.TiposEnum;

public class PezDorado extends Mascota{
    public PezDorado(){
        this.nombreAnimal = "Pez Dorado";
        this.tipo = TiposEnum.ACUATICO;
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
    }
}
