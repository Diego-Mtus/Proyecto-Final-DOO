package org.udec.mascotas;

import org.udec.GeneradorNombreAleatorio;

public class Perro extends Mascota{
    public Perro(){
        this.nombreAnimal = MascotasEnum.PERRO.getNombre();
        this.tipo = MascotasEnum.PERRO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
    }
}
