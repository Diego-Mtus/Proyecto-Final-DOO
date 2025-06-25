package org.udec.mascotas;

import org.udec.util.*;

public class Golondrina extends Mascota{
    Golondrina(){
        this.nombreAnimal = MascotasEnum.GOLONDRINA.getNombre();
        this.tipo = MascotasEnum.GOLONDRINA.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.MEDIO);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.GOLONDRINA.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.GOLONDRINA.getRutaSonido());
    }
}
