package org.udec.mascotas;

import org.udec.util.enumerations.NivelDecrementoEnum;

import java.util.Random;

public class Estado {
    private volatile int salud;
    private volatile int hambre;
    private volatile int felicidad;

    private int decrementoHambre;
    private int decrementoSalud;
    private int decrementoFelicidad;

    private final int estadoMax = 100;


    Estado(NivelDecrementoEnum decrementoHambre, NivelDecrementoEnum decrementoSalud, NivelDecrementoEnum decrementoFelicidad){

        this.decrementoHambre = decrementoHambre.getValor();
        this.decrementoSalud = decrementoSalud.getValor();
        this.decrementoFelicidad = decrementoFelicidad.getValor();

        // Que los valores que ya tenga sean al azar por debajo de la mitad.
        Random random = new Random();
        this.salud = random.nextInt(35,60);
        this.hambre = random.nextInt(35,60);
        this.felicidad = random.nextInt(35,60);
    }

    public void setSalud(int salud){
        if(salud < 0){
            this.salud = 0;
        }
        else this.salud = Math.min(salud, estadoMax);
    }

    public void setHambre(int hambre){
        if(hambre < 0){
            this.hambre = 0;
        }
        else this.hambre = Math.min(hambre, estadoMax);
    }

    public void setFelicidad(int felicidad){
        if(felicidad < 0){
            this.felicidad = 0;
        }
        else this.felicidad = Math.min(felicidad, estadoMax);
    }

    public void addSalud(int salud){
        if(salud > 0){
            setSalud(this.salud + salud);
        }
    }

    public void addHambre(int hambre){
        if(hambre > 0){
            setHambre(this.hambre + hambre);
        }
    }

    public void addFelicidad(int felicidad){
        if(felicidad > 0){
            setFelicidad(this.felicidad + felicidad);
        }
    }

    public int verSalud(){
        return salud;
    }

    public int verHambre(){
        return hambre;
    }

    public int verFelicidad(){
        return felicidad;
    }

    public int getDecrementoHambre() {
        return decrementoHambre;
    }

    public int getDecrementoSalud() {
        return decrementoSalud;
    }

    public int getDecrementoFelicidad() {
        return decrementoFelicidad;
    }
}

