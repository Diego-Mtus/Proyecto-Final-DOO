package org.udec.mascotas;

import org.udec.util.*;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

public class Perro extends Mascota{
    Perro(){
        this.nombreAnimal = MascotasEnum.PERRO.getNombre();
        this.tipo = MascotasEnum.PERRO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.MEDIO);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.PERRO.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.PERRO.getRutaSonido());
    }
}
