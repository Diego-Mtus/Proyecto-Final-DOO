package org.udec;

import org.udec.mascotas.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new VentanaPrincipal();

        System.out.println(TiposEnum.ROEDOR.mascotasCompatibles());

        Escenario comun = EscenarioFactory.crearEscenario(TiposEnum.COMUN);
        comun.fabricarMascota(MascotasEnum.GATO);

        Mascota mascota = comun.getMascotaActual();
        System.out.println(mascota.toString());
        System.out.println("salud: " + mascota.verSalud());
        System.out.println("hambre: " + mascota.verHambre());
        System.out.println("felicidad: " + mascota.verFelicidad());

    }
}