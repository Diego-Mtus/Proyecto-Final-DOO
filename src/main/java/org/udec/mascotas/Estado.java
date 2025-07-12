package org.udec.mascotas;

import org.udec.util.enumerations.NivelDecrementoEnum;

import java.util.Random;

/**
 * Clase que representa el estado de una mascota.
 * Contiene atributos para la salud, hambre y felicidad de la mascota,
 * así como métodos para modificar y consultar estos valores.
 * También maneja el estado de heridas y si la mascota quiere jugar.
 */
public class Estado {

    private volatile int salud;
    private volatile int hambre;
    private volatile int felicidad;
    private volatile boolean isHerido = false;
    private volatile boolean quiereJugar = true;

    private final int decrementoHambre;
    private final int decrementoSalud;
    private final int decrementoFelicidad;

    private final int estadoMax = 100;

    /**
     * Constructor de la clase Estado.
     * Inicializa los valores de salud, hambre y felicidad al azar entre 35 y 60,
     * y establece los decrementos según los parámetros proporcionados.
     *
     * @param decrementoHambre Decremento de hambre.
     * @param decrementoSalud Decremento de salud.
     * @param decrementoFelicidad Decremento de felicidad.
     */
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


    /**
     * Establece el valor de salud de la mascota.
     * Si el valor es menor que 0, se establece a 0.
     * Si es mayor que el máximo permitido, se ajusta al máximo.
     *
     * @param salud El nuevo valor de hambre.
     */
    public void setSalud(int salud){
        if(salud < 0){
            this.salud = 0;
        }
        else this.salud = Math.min(salud, estadoMax);
    }

    /**
     * Establece el valor de hambre de la mascota.
     * Si el valor es menor que 0, se establece a 0.
     * Si es mayor que el máximo permitido, se ajusta al máximo.
     *
     * @param hambre El nuevo valor de hambre.
     */
    public void setHambre(int hambre){
        if(hambre < 0){
            this.hambre = 0;
        }
        else this.hambre = Math.min(hambre, estadoMax);
    }

    /**
     * Establece el valor de felicidad de la mascota.
     * Si el valor es menor que 0, se establece a 0.
     * Si es mayor que el máximo permitido, se ajusta al máximo.
     *
     * @param felicidad El nuevo valor de hambre.
     */
    public void setFelicidad(int felicidad){
        if(felicidad < 0){
            this.felicidad = 0;
        }
        else this.felicidad = Math.min(felicidad, estadoMax);
    }

    /**
     * Aumenta la salud de la mascota en una cantidad específica.
     * Si la cantidad es positiva, se suma al valor actual de salud.
     *
     * @param salud Cantidad de salud a añadir.
     */
    public void addSalud(int salud){
        if(salud > 0){
            setSalud(this.salud + salud);
        }
    }

    /**
     * Aumenta el hambre de la mascota en una cantidad específica.
     * Si la cantidad es positiva, se suma al valor actual de hambre.
     *
     * @param hambre Cantidad de hambre a añadir.
     */
    public void addHambre(int hambre){
        if(hambre > 0){
            setHambre(this.hambre + hambre);
        }
    }

    /**
     * Aumenta la felicidad de la mascota en una cantidad específica.
     * Si la cantidad es positiva, se suma al valor actual de felicidad.
     *
     * @param felicidad Cantidad de felicidad a añadir.
     */
    public void addFelicidad(int felicidad){
        if(felicidad > 0){
            setFelicidad(this.felicidad + felicidad);
        }
    }

    /**
     * Verifica el estado de salud de la mascota.
     *
     * @return El valor actual de salud.
     */
    public int verSalud(){
        return salud;
    }

    /**
     * Verifica el estado de hambre de la mascota.
     *
     * @return El valor actual de hambre.
     */
    public int verHambre(){
        return hambre;
    }

    /**
     * Verifica el estado de felicidad de la mascota.
     *
     * @return El valor actual de felicidad.
     */
    public int verFelicidad(){
        return felicidad;
    }

    /**
     * Obtiene el decremento de hambre.
     *
     * @return El valor del decremento de hambre.
     */
    public int getDecrementoHambre() {
        return decrementoHambre;
    }

    /**
     * Obtiene el decremento de salud.
     *
     * @return El valor del decremento de salud.
     */
    public int getDecrementoSalud() {
        return decrementoSalud;
    }

    /**
     * Obtiene el decremento de felicidad.
     *
     * @return El valor del decremento de felicidad.
     */
    public int getDecrementoFelicidad() {
        return decrementoFelicidad;
    }

    /**
     * Verifica si la mascota está herida.
     *
     * @return true si la mascota está herida, false en caso contrario.
     */
    public boolean isHerido() {
        return isHerido;
    }

    /**
     * Establece el estado de herida de la mascota.
     *
     * @param herido true si la mascota está herida, false en caso contrario.
     */
    public void setHerido(boolean herido) {
        isHerido = herido;
    }

    /**
     * Verifica si la mascota quiere jugar.
     *
     * @return true si la mascota quiere jugar, false en caso contrario.
     */
    public boolean quiereJugar() {
        return quiereJugar;
    }

    /**
     * Establece si la mascota quiere jugar.
     *
     * @param quiereJugar true si la mascota quiere jugar, false en caso contrario.
     */
    public void setQuiereJugar(boolean quiereJugar) {
        this.quiereJugar = quiereJugar;
    }
}

