package org.udec.mascotas;

import org.udec.util.NivelDecrementoEnum;
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

        private int decrementoHambre;
        private int decrementoSalud;
        private int decrementoFelicidad;

        private final int estadoMax = 100;

        
        protected Estado(NivelDecrementoEnum decrementoHambre, NivelDecrementoEnum decrementoSalud, NivelDecrementoEnum decrementoFelicidad){

            this.decrementoHambre = decrementoHambre.getValor();
            this.decrementoSalud = decrementoSalud.getValor();
            this.decrementoFelicidad = decrementoFelicidad.getValor();

            // Que los valores que ya tenga sean al azar por debajo de la mitad.
            Random random = new Random();
            this.salud = random.nextInt(50);
            this.hambre = random.nextInt(50);
            this.felicidad = random.nextInt(50);
        }

        public void setSalud(int salud){
            if(salud >= 0 && salud <= estadoMax){
                this.salud = salud;
            } else if(salud > estadoMax){
                this.salud = estadoMax;
            }
        }

        public void setHambre(int hambre){
            if(hambre >= 0 && hambre <= estadoMax){
                this.hambre = hambre;
            } else if(hambre > estadoMax){
                this.hambre = estadoMax;
            }
        }

        public void setFelicidad(int felicidad){
            if(felicidad >= 0 && felicidad <= estadoMax){
                this.felicidad = felicidad;
            } else if(felicidad > estadoMax){
                this.felicidad = estadoMax;
            }
        }

    }
}
