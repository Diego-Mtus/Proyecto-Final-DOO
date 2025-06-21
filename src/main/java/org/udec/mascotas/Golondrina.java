package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;

public class Golondrina extends Mascota{
    Golondrina(){
        this.nombreAnimal = MascotasEnum.GOLONDRINA.getNombre();
        this.tipo = MascotasEnum.GOLONDRINA.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(125,100,100);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.GOLONDRINA.getRutaImagen());
    }
}
