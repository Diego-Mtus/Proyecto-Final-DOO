package org.udec.util.enumerations;

import org.udec.mascotas.Mascota;
import org.udec.util.GestionDeSonido;

import javax.sound.sampled.Clip;
import java.util.Objects;

/**
 * Enumeración que representa diferentes tipos de alimentos para mascotas.
 * Implementa la interfaz ProductosEnum para definir los productos que se pueden comprar.
 */
public enum AlimentosEnum implements ProductosEnum{

    ALIMENTO_PERRO("Croquetas para perro", MascotasEnum.PERRO, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_GATO("Croquetas para gato", MascotasEnum.GATO, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_GOLONDRINA("Semillas para golondrina", MascotasEnum.GOLONDRINA, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_LORO("Semillas para loro", MascotasEnum.LORO, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_RATON("Queso para ratón", MascotasEnum.RATON, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_HAMSTER("Alimento para hámster", MascotasEnum.HAMSTER, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_PEZDORADO("Alimento para pez dorado", MascotasEnum.PEZDORADO, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_PEZPAYASO("Alimento para pez payaso", MascotasEnum.PEZPAYASO, "/mascotas/pezpayaso.png", 1);

    private final String nombre;
    private final MascotasEnum paraQueMascota;
    private final String rutaImagen;
    private final int precio;
    private int inventario = 0;
    private final Clip clipComer = GestionDeSonido.cargarClip("/sonidos/comer.wav");

    /**
     * Constructor de la enumeración AlimentosEnum.
     *
     * @param nombre Nombre del alimento.
     * @param paraQueMascota Tipo de mascota para la que es adecuado el alimento.
     * @param rutaImagen Ruta de la imagen del alimento.
     * @param precio Precio del alimento.
     */
    AlimentosEnum(String nombre, MascotasEnum paraQueMascota, String rutaImagen, int precio) {
        this.nombre = nombre;
        this.paraQueMascota = paraQueMascota;
        this.rutaImagen = rutaImagen;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public MascotasEnum[] getParaQueMascota() {
        return new MascotasEnum[]{paraQueMascota};
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public int getPrecio() {
        return precio;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    /**
     * Método para alimentar a una mascota con el alimento correspondiente.
     * Aumenta el hambre de la mascota según su tipo.
     *
     * @param mascota La mascota que se va a alimentar.
     */
    public void alimentar(Mascota mascota){
        if (Objects.equals(mascota.getNombreAnimal(), paraQueMascota.getNombre())){
            mascota.getEstado().addHambre(20);
        } else if(mascota.getTipo() == paraQueMascota.getTipo()){
            mascota.getEstado().addHambre(10);
        } else {
            mascota.getEstado().addHambre(5);
            mascota.getEstado().setSalud(mascota.getEstado().verSalud() - 5);
        }
        GestionDeSonido.reproducirClipEnHilo(clipComer);

    }

}
