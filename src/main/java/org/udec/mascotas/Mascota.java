package org.udec.mascotas;

import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Mascota {

    protected String nombrePropio;
    protected String nombreAnimal;
    protected BufferedImage imagenMascota;
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

    @Override
    public String toString() {
        return "Mascota: " +
                "nombrePropio='" + nombrePropio + '\'' +
                ", nombreAnimal='" + nombreAnimal + '\'' +
                ", tipo=" + tipo;
    }

    public int verSalud(){
        return this.estado.getSalud();
    }

    public int verHambre(){
        return this.estado.getHambre();
    }

    public int verFelicidad(){
        return this.estado.getFelicidad();
    }

    // Clase solo existe dentro del contexto de la mascota.
    protected static class Estado{
        private int salud;
        private int hambre;
        private int felicidad;
        
        private int saludMax;
        private int hambreMax;
        private int felicidadMax;
        
        public Estado(int saludMax, int hambreMax, int felicidadMax){
            this.saludMax = saludMax;
            this.hambreMax = hambreMax;
            this.felicidadMax = felicidadMax;

            // Que los valores que ya tenga sean al azar por debajo de la mitad.
            Random random = new Random();
            this.salud = random.nextInt(saludMax/2 + 1);
            this.hambre = random.nextInt(hambreMax/2 + 1);
            this.felicidad = random.nextInt(felicidadMax/2 + 1);
        }

        public int getSalud(){
            return this.salud;
        }

        public int getHambre(){
            return this.hambre;
        }
        public int getFelicidad(){
            return this.felicidad;
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
