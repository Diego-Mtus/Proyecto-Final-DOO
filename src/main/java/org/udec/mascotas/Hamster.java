package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;
import org.udec.util.GestionDeSonido;
import org.udec.util.MascotasEnum;


public class Hamster extends Mascota{
    Hamster(){
        this.nombreAnimal = MascotasEnum.HAMSTER.getNombre();
        this.tipo = MascotasEnum.HAMSTER.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(150,100,100);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.HAMSTER.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.HAMSTER.getRutaSonido());
    }
}
