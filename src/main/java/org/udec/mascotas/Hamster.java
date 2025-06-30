package org.udec.mascotas;

import org.udec.util.*;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;


public class Hamster extends Mascota{
    Hamster(){
        this.nombreAnimal = MascotasEnum.HAMSTER.getNombre();
        this.tipo = MascotasEnum.HAMSTER.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(NivelDecrementoEnum.LENTO, NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.RAPIDO);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.HAMSTER.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.HAMSTER.getRutaSonido());
    }
}
