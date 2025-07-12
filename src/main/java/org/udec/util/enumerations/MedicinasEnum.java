package org.udec.util.enumerations;

import org.udec.mascotas.Mascota;
import org.udec.util.GestionDeSonido;

import javax.sound.sampled.Clip;
import java.util.Arrays;

/**
 * Enumeración que representa diferentes tipos de medicinas para mascotas.
 * Implementa la interfaz ProductosEnum para definir los productos que se pueden comprar.
 */
public enum MedicinasEnum implements ProductosEnum{

    MEDICINA_VOLADOR("Medicamento para aves", TiposEnum.VOLADOR.mascotasCompatibles().toArray(new MascotasEnum[0]) , "/medicinas/medicinaVolador.png", 5),
    MEDICINA_COMUN("Medicamento para mascotas comúnes", TiposEnum.COMUN.mascotasCompatibles().toArray(new MascotasEnum[0]), "/medicinas/medicinaComun.png", 5),
    MEDICINA_ROEDOR("Medicamento para roedores", TiposEnum.ROEDOR.mascotasCompatibles().toArray(new MascotasEnum[0]), "/medicinas/medicinaRoedor.png", 5),
    MEDICINA_PECES("Medicamento para peces", TiposEnum.ACUATICO.mascotasCompatibles().toArray(new MascotasEnum[0]), "/medicinas/medicinaAcuatico.png", 5),
    CURITA_HERIDAS("Curita para heridas", null, "/medicinas/curitaHeridas.png", 2);

    private final String nombre;
    private final MascotasEnum[] paraQueMascota;
    private final String rutaImagen;
    private final int precio;
    private int inventario = 0;
    private final Clip clipComer = GestionDeSonido.cargarClip("/sonidos/comer.wav");

    /**
     * Constructor de la enumeración MedicinasEnum.
     *
     * @param nombre Nombre de la medicina.
     * @param paraQueMascota Mascotas para las que es compatible la medicina.
     * @param rutaImagen Ruta de la imagen de la medicina.
     * @param precio Precio de la medicina.
     */
    MedicinasEnum(String nombre, MascotasEnum[] paraQueMascota, String rutaImagen, int precio) {
        this.nombre = nombre;
        this.paraQueMascota = paraQueMascota;
        this.rutaImagen = rutaImagen;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public MascotasEnum[] getParaQueMascota() {
        return paraQueMascota;
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
     * Método para curar una mascota.
     * Si la medicina es CURITA_HERIDAS, se cura la herida de la mascota.
     * Si no, se incrementa la salud de la mascota en 20 si es compatible,
     * o se decrementa en 5 si no lo es.
     *
     * @param mascota La mascota a curar.
     */
    public void curar(Mascota mascota){
        if(this == CURITA_HERIDAS){
            mascota.getEstado().setHerido(false);
            return;
        }
        else{
            if(Arrays.stream(paraQueMascota).anyMatch(m -> m.getNombre().equals(mascota.getNombreAnimal()))){
                mascota.getEstado().addSalud(20);
            } else{
                mascota.getEstado().setSalud(mascota.getEstado().verSalud() - 5);
            }
            GestionDeSonido.reproducirClipEnHilo(clipComer);
        }
    }
}
