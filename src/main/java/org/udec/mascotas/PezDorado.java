package org.udec.mascotas;

import org.udec.GeneradorNombreAleatorio;


public class PezDorado extends Mascota{
    public PezDorado(){
        this.nombreAnimal = MascotasEnum.PEZDORADO.getNombre();
        this.tipo = MascotasEnum.PEZDORADO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
    }
}
