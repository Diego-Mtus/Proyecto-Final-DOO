package org.udec.util.threads;

import org.udec.mascotas.Mascota;
import org.udec.visual.PanelEscenario;

import java.util.Random;

public class HiloCompradorInteresado implements Runnable{

    private volatile boolean corriendo = true;
    private final int ESTADO_MASCOTA_ESPERADO = 85;


    private PanelEscenario panelEscenario;
    private Mascota mascota;
    private int intervalosActuales = 0;
    private final int intervalosEsperados;

    public HiloCompradorInteresado(PanelEscenario panelEscenario){
        this.panelEscenario = panelEscenario;
        this.mascota = panelEscenario.getEscenario().getMascotaActual();
        Random intervalos = new Random();

        // Los intervalos esperados para que un comprador se interese es un número al azar entre 5 y 9,
        // significando que alguien se interesará luego de un tiempo entre 25 y 45 segundos.
        this.intervalosEsperados = intervalos.nextInt(5,10);
    }

    @Override
    public void run() {

            while(corriendo){

                // Cada 5 segundos revisará.
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("El hilo del comprador ha sido interrumpido.");
                }

                synchronized (mascota.getEstado()){
                    // Si la mascota tiene todos los estados por sobre 85, se cumple un intervalo
                    if(mascota.getEstado().verHambre() > ESTADO_MASCOTA_ESPERADO && mascota.getEstado().verSalud() > ESTADO_MASCOTA_ESPERADO && mascota.getEstado().verFelicidad() > ESTADO_MASCOTA_ESPERADO){
                        intervalosActuales++;
                        System.out.println("Invervalo cumplido satisfactoriamente");
                    }

                    // Una vez que hayan ocurrido INTERVALOS_ESPERADOS intervalos, se avisará a PanelEscenario para vender, y se terminará el hilo.
                    if(intervalosActuales >= intervalosEsperados){
                        System.out.println("Apto para venta");
                        Thread.currentThread().interrupt();
                        corriendo = false;
                    }

                    System.out.println("Ciclo de comprador completado.");
                }



        }

    }
}
