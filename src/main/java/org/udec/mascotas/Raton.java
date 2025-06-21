package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;

public class Raton extends Mascota{
    Raton(){
        this.nombreAnimal = MascotasEnum.RATON.getNombre();
        this.tipo = MascotasEnum.RATON.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(125,100,125);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.RATON.getRutaImagen());
    }
}
