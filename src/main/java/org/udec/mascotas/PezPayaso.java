package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;

public class PezPayaso extends Mascota{
    PezPayaso(){
        this.nombreAnimal = MascotasEnum.PEZPAYASO.getNombre();
        this.tipo = MascotasEnum.PEZPAYASO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(150,100,100);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.PEZPAYASO.getRutaImagen());
    }
}
