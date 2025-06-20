package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;


public class PezDorado extends Mascota{
    PezDorado(){
        this.nombreAnimal = MascotasEnum.PEZDORADO.getNombre();
        this.tipo = MascotasEnum.PEZDORADO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(125,100,125);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.PEZDORADO.getRutaImagen());
    }
}
