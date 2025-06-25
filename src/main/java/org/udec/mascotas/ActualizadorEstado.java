package org.udec.mascotas;

import org.udec.visual.PanelEscenario;

public class ActualizadorEstado implements Runnable{

    private PanelEscenario panelEscenario;
    private Mascota mascota;
    private int decrementoHambre;
    private int decrementoSalud;
    private int decrementoFelicidad;

    private volatile boolean corriendo = true;


    public ActualizadorEstado(PanelEscenario panelEscenario){
        this.panelEscenario = panelEscenario;
        this.mascota = panelEscenario.getEscenario().getMascotaActual();
        this.decrementoHambre = mascota.getEstado().getDecrementoHambre();
        this.decrementoSalud = mascota.getEstado().getDecrementoSalud();
        this.decrementoFelicidad = mascota.getEstado().getDecrementoFelicidad();
    }

    @Override
    public void run() {
        while(corriendo){
            try{
                Thread.sleep(10000);

                synchronized (mascota.getEstado()){

                    // Decrementar hambre
                    if(mascota.getEstado().verHambre() > 0){
                        decrementarHambre();
                    }

                    // Si el hambre es menor o igual a 20, empieza a decrementar salud
                    if(mascota.getEstado().verHambre() <= 20 && mascota.getEstado().verSalud() > 0){
                        decrementarSalud();
                    }

                    if(mascota.getEstado().verFelicidad() > 0) {
                        decrementarFelicidad();
                    }

                    System.out.println("a");
                    panelEscenario.actualizarVisualEstado();
                }

            } catch (InterruptedException e) {
                System.out.println("Interrupción de hilo");
                Thread.currentThread().interrupt();
                corriendo = false;
            }
        }

    }

    public void detener(){
        this.corriendo = false;
    }

    private void decrementarHambre(){
        mascota.getEstado().setHambre(mascota.getEstado().verHambre() - decrementoHambre);
    }

    private void decrementarSalud(){
        mascota.getEstado().setSalud(mascota.getEstado().verSalud() - decrementoSalud);
    }

    private void decrementarFelicidad(){
        mascota.getEstado().setFelicidad(mascota.getEstado().verFelicidad() - decrementoFelicidad);
    }
}
