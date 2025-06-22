package org.udec.escenarios;

import org.udec.mascotas.Mascota;
import org.udec.util.MascotasEnum;
import org.udec.util.TiposEnum;

import java.awt.image.BufferedImage;

public abstract class Escenario {
    protected String nombre;
    protected Mascota mascotaActual;
    protected TiposEnum tipoEscenario;
    protected BufferedImage imagenEscenario;

    public TiposEnum getTipoEscenario(){
        return this.tipoEscenario;
    }

    public Mascota getMascotaActual() {
        return mascotaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public BufferedImage getImagenEscenario() {
        return imagenEscenario;
    }

    public boolean puedeAlojar(MascotasEnum mascota){
        return mascota.getTipo() == this.tipoEscenario;
    }

    public boolean tieneMascota() {
        return mascotaActual != null;
    }

    public void alojarMascota(Mascota mascota) {
        this.mascotaActual = mascota;
        System.out.println("Se ha alojado a " + mascotaActual.getNombreAnimal());
    }

    public void venderMascota() {
        if (mascotaActual != null) {
            System.out.println("Has vendido al " + mascotaActual.getNombreAnimal());
            mascotaActual = null;
        } else {
            System.out.println("No hay ninguna mascota en el escenario.");
        }
    }

}
