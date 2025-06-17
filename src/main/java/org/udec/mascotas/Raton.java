package org.udec.mascotas;

import org.udec.GeneradorNombreAleatorio;

public class Raton extends Mascota{
    public Raton(){
        this.nombreAnimal = MascotasEnum.RATON.getNombre();
        this.tipo = MascotasEnum.RATON.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
    }
}
