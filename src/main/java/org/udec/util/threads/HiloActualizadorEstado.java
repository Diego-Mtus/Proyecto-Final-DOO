package org.udec.util.threads;

import org.udec.mascotas.Mascota;
import org.udec.visual.PanelEscenario;
import org.udec.visual.PanelEstado;

/**
 * Clase que representa un hilo para actualizar el estado de una mascota en un panel de estado.
 * Este hilo se encarga de decrementar los valores de hambre, salud y felicidad de la mascota
 * en intervalos regulares, y actualiza el panel de estado correspondiente.
 */
public class HiloActualizadorEstado implements Runnable{

    private final PanelEstado panelEstado;
    private final Mascota mascota;
    private final int decrementoHambre;
    private final int decrementoSalud;
    private final int decrementoFelicidad;

    private boolean corriendo = true;

    /**
     * Constructor de la clase HiloActualizadorEstado.
     * Inicializa el hilo con el panel de estado y la mascota a actualizar.
     *
     * @param panelEstado El panel de estado donde se mostrará la información de la mascota.
     * @param mascota La mascota cuyo estado se actualizará.
     */
    public HiloActualizadorEstado(PanelEstado panelEstado, Mascota mascota) {

        this.panelEstado = panelEstado;
        this.mascota = mascota;
        this.decrementoHambre = mascota.getEstado().getDecrementoHambre();
        this.decrementoSalud = mascota.getEstado().getDecrementoSalud();
        this.decrementoFelicidad = mascota.getEstado().getDecrementoFelicidad();
    }


    /**
     * Método que inicia el hilo de actualización de estado.
     * Este método se ejecuta en un bucle, actualizando el estado de la mascota cada 10 segundos.
     */
    @Override
    public void run() {
        while(corriendo){

            try {
                Thread.sleep(10000);
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

    /**
     * Método para detener el hilo de actualización de estado.
     * Cambia el estado de corriendo a false, lo que detiene el bucle del hilo.
     */
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
