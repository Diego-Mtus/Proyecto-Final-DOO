package org.udec.mascotas;

import org.udec.TiposEnum;

public abstract class Escenario {
    protected String nombre;
    protected Mascota mascotaActual;
    protected TiposEnum tipoEscenario;

    public TiposEnum getTipoEscenario(){
        return this.tipoEscenario;
    }

    public Mascota getMascotaActual() {
        return mascotaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean puedeAlojar(Mascota mascota){
        return mascota.getTipo() == this.tipoEscenario;
    }

    public boolean alojarMascota(Mascota mascota){
        if(mascotaActual != null) {
            System.out.println("Ya hay una mascota habitando ahí.");
            return false;
        }

        if(puedeAlojar(mascota)){
            this.mascotaActual = mascota;
            System.out.println("Se ha alojado a " + mascota.getNombreAnimal());
            return true;
        } else {
            System.out.println("Esa mascota no puede vivir ahí.");
            return false;
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
