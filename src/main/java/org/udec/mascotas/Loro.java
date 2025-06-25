package org.udec.mascotas;

import org.udec.util.*;

public class Loro extends Mascota {
    Loro(){
        this.nombreAnimal = MascotasEnum.LORO.getNombre();
        this.tipo = MascotasEnum.LORO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.LORO.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.LORO.getRutaSonido());
    }
}
