package org.udec.mascotas;

import org.udec.util.*;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

public class Golondrina extends Mascota{
    Golondrina(){
        this.nombreAnimal = MascotasEnum.GOLONDRINA.getNombre();
        this.tipo = MascotasEnum.GOLONDRINA.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.MEDIO);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.GOLONDRINA.getRutaImagen());
        this.imagenMascotaJuego = CargadorDeImagenes.cargarImagen(MascotasEnum.GOLONDRINA.getRutaImagenJuego());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.GOLONDRINA.getRutaSonido());
        this.precioVenta = MascotasEnum.GOLONDRINA.getPrecioVenta();
    }
}
