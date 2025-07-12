package org.udec.escenarios;

import org.udec.mascotas.Mascota;
import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.enumerations.TiposEnum;

import java.awt.image.BufferedImage;

/**
 * Clase abstracta que representa un escenario donde se pueden alojar mascotas.
 * Define las operaciones básicas para manejar mascotas en el escenario.
 */
public abstract class Escenario {
    /**
     * Nombre del escenario.
     */
    protected String nombre;
    /**
     * Mascota actual alojada en el escenario.
     * Puede ser null si no hay ninguna mascota alojada.
     */
    protected Mascota mascotaActual;
    /**
     * Tipo de escenario, representado por un valor de la enumeración TiposEnum.
     */
    protected TiposEnum tipoEscenario;
    /**
     * Imagen que representa el escenario.
     * Se utiliza para mostrar el escenario en la interfaz gráfica.
     */
    protected BufferedImage imagenEscenario;

    /**
     * Getter para obtener el tipo de escenario.
     *
     * @return El tipo de escenario como un valor de la enumeración TiposEnum.
     */
    public TiposEnum getTipoEscenario(){
        return this.tipoEscenario;
    }

    /**
     * Getter para obtener la mascota actual alojada en el escenario.
     *
     * @return La mascota actual, o null si no hay ninguna mascota alojada.
     */
    public Mascota getMascotaActual() {
        return mascotaActual;
    }

    /**
     * Getter para obtener el nombre del escenario.
     *
     * @return El nombre del escenario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter para obtener la imagen del escenario.
     *
     * @return La imagen del escenario como un objeto BufferedImage.
     */
    public BufferedImage getImagenEscenario() {
        return imagenEscenario;
    }

    /**
     * Método para verificar si el escenario puede alojar una mascota de un tipo específico.
     *
     * @param mascota La mascota a verificar como un valor de la enumeración MascotasEnum.
     * @return true si el escenario puede alojar la mascota, false en caso contrario.
     */
    public boolean puedeAlojar(MascotasEnum mascota){
        return mascota.getTipo() == this.tipoEscenario;
    }

    /**
     * Método para verificar si el escenario ya tiene una mascota alojada.
     *
     * @return true si hay una mascota alojada, false en caso contrario.
     */
    public boolean tieneMascota() {
        return mascotaActual != null;
    }

    /**
     * Método para alojar una mascota en el escenario.
     *
     * @param mascota La mascota a alojar.
     */
    public void alojarMascota(Mascota mascota) {
        this.mascotaActual = mascota;
        System.out.println("Se ha alojado a " + mascotaActual.getNombreAnimal());
    }

    /**
     * Método para vender la mascota actual del escenario.
     * Si no hay una mascota alojada, se informa al usuario.
     */
    public void venderMascota() {
        if (mascotaActual != null) {
            System.out.println("Has vendido al " + mascotaActual.getNombreAnimal());
            mascotaActual = null;
        } else {
            System.out.println("No hay ninguna mascota en el escenario.");
        }
    }

}
