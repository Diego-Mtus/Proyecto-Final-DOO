package org.udec.mascotas;

import org.udec.util.*;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

public class Raton extends Mascota{
    Raton(){
        this.nombreAnimal = MascotasEnum.RATON.getNombre();
        this.tipo = MascotasEnum.RATON.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.RATON.getRutaImagen());
        this.imagenMascotaJuego = CargadorDeImagenes.cargarImagen(MascotasEnum.RATON.getRutaImagenJuego());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.RATON.getRutaSonido());
        this.precioVenta = MascotasEnum.RATON.getPrecioVenta();
    }
}
