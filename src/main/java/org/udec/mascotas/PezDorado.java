package org.udec.mascotas;

import org.udec.util.*;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;


public class PezDorado extends Mascota{
    PezDorado(){
        this.nombreAnimal = MascotasEnum.PEZDORADO.getNombre();
        this.tipo = MascotasEnum.PEZDORADO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(NivelDecrementoEnum.MEDIO, NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.MEDIO);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.PEZDORADO.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.PEZDORADO.getRutaSonido());
        this.precioVenta = MascotasEnum.PEZDORADO.getPrecioVenta();
    }
}
