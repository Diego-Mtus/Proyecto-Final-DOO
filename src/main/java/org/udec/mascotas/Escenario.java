package org.udec.mascotas;

import org.udec.TiposEnum;

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

    public void fabricarMascota(MascotasEnum mascota){

        if(mascotaActual != null) {
            System.out.println("Ya hay una mascota habitando ahí.");
            return;
        }

        if(puedeAlojar(mascota)){
            this.mascotaActual = mascota.crearMascota();
            System.out.println("Se ha alojado a " + mascotaActual.getNombreAnimal());
        } else {
            System.out.println("Esa mascota no puede vivir ahí.");
        }

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
