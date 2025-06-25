package org.udec.mascotas;

import org.udec.util.*;

public class Gato extends Mascota{
    Gato(){
        this.nombreAnimal = MascotasEnum.GATO.getNombre();
        this.tipo = MascotasEnum.GATO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.GATO.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.GATO.getRutaSonido());
    }


}
