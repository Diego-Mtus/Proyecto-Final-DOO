package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;

public class Loro extends Mascota {
    Loro(){
        this.nombreAnimal = MascotasEnum.LORO.getNombre();
        this.tipo = MascotasEnum.LORO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(125,100,125);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.LORO.getRutaImagen());
    }
}
