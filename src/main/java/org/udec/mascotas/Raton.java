package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;
import org.udec.util.GestionDeSonido;
import org.udec.util.MascotasEnum;

public class Raton extends Mascota{
    Raton(){
        this.nombreAnimal = MascotasEnum.RATON.getNombre();
        this.tipo = MascotasEnum.RATON.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado();
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.RATON.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.RATON.getRutaSonido());
    }
}
