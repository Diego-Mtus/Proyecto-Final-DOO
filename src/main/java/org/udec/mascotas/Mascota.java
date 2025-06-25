package org.udec.mascotas;

import org.udec.util.TiposEnum;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Mascota {

    protected String nombrePropio;
    protected String nombreAnimal;
    protected BufferedImage imagenMascota;
    protected Clip sonidoMascota;
    protected TiposEnum tipo;
    protected Estado estado;

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

    @Override
    public String toString() {
        return "Mascota: " +
                "nombrePropio='" + nombrePropio + '\'' +
                ", nombreAnimal='" + nombreAnimal + '\'' +
                ", tipo=" + tipo;
    }

    public int verSalud(){
        return this.estado.salud;
    }

    public int verHambre(){
        return this.estado.hambre;
    }

    public int verFelicidad(){
        return this.estado.felicidad;
    }

    // Clase solo existe dentro del contexto de la mascota.
    protected static class Estado{
        private int salud;
        private int hambre;
        private int felicidad;

        private final int saludMax = 100;
        private final int hambreMax = 100;
        private final int felicidadMax = 100;
        
        public Estado(){

            // Que los valores que ya tenga sean al azar por debajo de la mitad.
            Random random = new Random();
            this.salud = random.nextInt(50);
            this.hambre = random.nextInt(50);
            this.felicidad = random.nextInt(50);
        }



        public void setSalud(int salud){
            if(salud >= 0 && salud <= saludMax){
                this.salud = salud;
            } else if(salud > saludMax){
                this.salud = saludMax;
            }
        }

        public void setHambre(int hambre){
            if(hambre >= 0 && hambre <= hambreMax){
                this.hambre = hambre;
            } else if(hambre > hambreMax){
                this.hambre = hambreMax;
            }
        }
        public void setFelicidad(int felicidad){
            if(felicidad >= 0 && felicidad <= felicidadMax){
                this.felicidad = felicidad;
            } else if(felicidad > felicidadMax){
                this.felicidad = felicidadMax;
            }
        }

    }
}
