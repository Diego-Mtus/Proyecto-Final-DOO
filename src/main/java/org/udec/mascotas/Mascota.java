package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;
import org.udec.util.GestionDeSonido;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.NivelDecrementoEnum;
import org.udec.util.enumerations.TiposEnum;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;

public abstract class Mascota {

    protected String nombrePropio;
    protected String nombreAnimal;
    protected BufferedImage imagenMascota;
    protected BufferedImage imagenMascotaJuego;
    protected Clip sonidoMascota;
    protected TiposEnum tipo;
    protected int precioVenta;
    protected Estado estado;

    protected Mascota(MascotasEnum mascotaEnum){
        this.nombreAnimal = mascotaEnum.getNombre();
        this.tipo = mascotaEnum.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.imagenMascota = CargadorDeImagenes.cargarImagen(mascotaEnum.getRutaImagen());
        this.imagenMascotaJuego = CargadorDeImagenes.cargarImagen(mascotaEnum.getRutaImagenJuego());
        this.sonidoMascota = GestionDeSonido.cargarClip(mascotaEnum.getRutaSonido());
        this.precioVenta = mascotaEnum.getPrecioVenta();
    }

    public TiposEnum getTipo(){
        return this.tipo;
    }

    public String getNombreAnimal(){
        return this.nombreAnimal;
    }

    public String getNombrePropio(){
        return this.nombrePropio;
    }

    public BufferedImage getImagenMascota() {
        return imagenMascota;
    }

    public Clip getSonidoMascota() {
        return sonidoMascota;
    }

    public BufferedImage getImagenMascotaJuego() {
        return imagenMascotaJuego;
    }

    @Override
    public String toString() {
        return "Mascota: " +
                "nombrePropio='" + nombrePropio + '\'' +
                ", nombreAnimal='" + nombreAnimal + '\'' +
                ", tipo=" + tipo;
    }

    public Estado getEstado(){
        return estado;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }


}
