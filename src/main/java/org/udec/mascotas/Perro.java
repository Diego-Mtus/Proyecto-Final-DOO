package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;

public class Perro extends Mascota{
    Perro(){
        this.nombreAnimal = MascotasEnum.PERRO.getNombre();
        this.tipo = MascotasEnum.PERRO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(100,150,100);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.PERRO.getRutaImagen());
    }
}
