package org.udec.mascotas;

import org.udec.util.*;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;

public class PezPayaso extends Mascota{
    PezPayaso(){
        this.nombreAnimal = MascotasEnum.PEZPAYASO.getNombre();
        this.tipo = MascotasEnum.PEZPAYASO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(NivelDecrementoEnum.LENTO, NivelDecrementoEnum.RAPIDO, NivelDecrementoEnum.RAPIDO);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.PEZPAYASO.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.PEZPAYASO.getRutaSonido());
    }
}
