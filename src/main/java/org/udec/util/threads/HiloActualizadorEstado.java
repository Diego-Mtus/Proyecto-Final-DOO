package org.udec.util.threads;

import org.udec.mascotas.Mascota;
import org.udec.visual.PanelEscenario;
import org.udec.visual.PanelEstado;

public class HiloActualizadorEstado implements Runnable{

    private final PanelEstado panelEstado;
    private final Mascota mascota;
    private final int decrementoHambre;
    private final int decrementoSalud;
    private final int decrementoFelicidad;

    private boolean corriendo = true;

    public HiloActualizadorEstado(PanelEstado panelEstado, Mascota mascota) {

        this.panelEstado = panelEstado;
        this.mascota = mascota;
        this.decrementoHambre = mascota.getEstado().getDecrementoHambre();
        this.decrementoSalud = mascota.getEstado().getDecrementoSalud();
        this.decrementoFelicidad = mascota.getEstado().getDecrementoFelicidad();
    }

    @Override
    public void run() {
        while(corriendo){

            try {
                Thread.sleep(10000); // Esperar 1 segundo entre ciclos
            } catch (InterruptedException e) {
                System.out.println("El hilo de actualización de estado fue interrumpido.");
            }

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

                System.out.println("Ciclo de estado completado.");
                panelEstado.repaint(); // Actualizar el panel de estado
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
