package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;
import org.udec.util.GestionDeSonido;
import org.udec.util.MascotasEnum;

public class Gato extends Mascota{
    Gato(){
        this.nombreAnimal = MascotasEnum.GATO.getNombre();
        this.tipo = MascotasEnum.GATO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(100,100,150);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.GATO.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.GATO.getRutaSonido());
    }


}
