package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;
import org.udec.util.GestionDeSonido;

public class Perro extends Mascota{
    Perro(){
        this.nombreAnimal = MascotasEnum.PERRO.getNombre();
        this.tipo = MascotasEnum.PERRO.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.estado = new Estado(100,150,100);
        this.imagenMascota = CargadorDeImagenes.cargarImagen(MascotasEnum.PERRO.getRutaImagen());
        this.sonidoMascota = GestionDeSonido.cargarClip(MascotasEnum.PERRO.getRutaSonido());
    }
}
