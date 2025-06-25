package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;
import org.udec.util.GestionDeSonido;
import org.udec.util.MascotasEnum;

public class Loro extends Mascota {
    Loro(){
        this.nombreAnimal = MascotasEnum.LORO.getNombre();
        this.tipo = MascotasEnum.LORO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado();
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.LORO.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.LORO.getRutaSonido());
    }
}
