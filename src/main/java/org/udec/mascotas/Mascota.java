package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.GeneradorNombreAleatorio;
import org.udec.util.GestionDeSonido;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.TiposEnum;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;

/**
 * Clase abstracta que representa una mascota en el juego.
 * Contiene atributos comunes a todas las mascotas, como nombre, tipo, imagen y sonido.
 * Proporciona métodos para obtener información sobre la mascota.
 */
public abstract class Mascota {

    protected String nombrePropio;
    protected String nombreAnimal;
    protected BufferedImage imagenMascota;
    protected BufferedImage imagenMascotaJuego;
    protected Clip sonidoMascota;
    protected TiposEnum tipo;
    protected int precioVenta;
    protected Estado estado;

    /**
     * Constructor de la clase Mascota.
     * Inicializa los atributos comunes de la mascota utilizando un enumerador de mascotas.
     *
     * @param mascotaEnum Enumeración que define las características de la mascota.
     */
    protected Mascota(MascotasEnum mascotaEnum){
        this.nombreAnimal = mascotaEnum.getNombre();
        this.tipo = mascotaEnum.getTipo();
        this.nombrePropio = GeneradorNombreAleatorio.obtenerNombreAleatorio();
        this.imagenMascota = CargadorDeImagenes.cargarImagen(mascotaEnum.getRutaImagen());
        this.imagenMascotaJuego = CargadorDeImagenes.cargarImagen(mascotaEnum.getRutaImagenJuego());
        this.sonidoMascota = GestionDeSonido.cargarClip(mascotaEnum.getRutaSonido());
        this.precioVenta = mascotaEnum.getPrecioVenta();
    }


    /**
     * Método para obtener el tipo de mascota.
     *
     * @return El tipo de mascota como un valor de la enumeración TiposEnum.
     */
    public TiposEnum getTipo(){
        return this.tipo;
    }

    /**
     * Método para obtener el nombre del animal de la mascota.
     *
     * @return El nombre del animal asignado a la mascota.
     */
    public String getNombreAnimal(){
        return this.nombreAnimal;
    }

    /**
     * Método para obtener el nombre propio de la mascota.
     *
     * @return El nombre propio asignado a la mascota.
     */
    public String getNombrePropio(){
        return this.nombrePropio;
    }

    /**
     * Método para obtener la imagen de la mascota.
     *
     * @return La imagen que representa a la mascota.
     */
    public BufferedImage getImagenMascota() {
        return imagenMascota;
    }

    /**
     * Método para obtener el sonido asociado a la mascota.
     *
     * @return El clip de sonido que representa el sonido de la mascota.
     */
    public Clip getSonidoMascota() {
        return sonidoMascota;
    }

    /**
     * Método para obtener la imagen de la mascota que se muestra en el juego.
     *
     * @return La imagen de la mascota utilizada en el juego.
     */
    public BufferedImage getImagenMascotaJuego() {
        return imagenMascotaJuego;
    }

    /**
     * Método para obtener el estado actual de la mascota.
     *
     * @return El estado de la mascota, que incluye niveles de hambre, sed y energía.
     */
    public Estado getEstado(){
        return estado;
    }

    /**
     * Método para obtener el precio de venta de la mascota.
     *
     * @return El precio de venta de la mascota.
     */
    public int getPrecioVenta() {
        return precioVenta;
    }


}
