package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;
import org.udec.util.GestionDeSonido;
import org.udec.util.MascotasEnum;

public class Golondrina extends Mascota{
    Golondrina(){
        this.nombreAnimal = MascotasEnum.GOLONDRINA.getNombre();
        this.tipo = MascotasEnum.GOLONDRINA.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(125,100,100);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.GOLONDRINA.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.GOLONDRINA.getRutaSonido());
    }
}
