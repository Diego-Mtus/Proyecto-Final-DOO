package org.udec;

import org.udec.mascotas.*;
import org.udec.visual.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.out.println(TiposEnum.ROEDOR.mascotasCompatibles());

        Escenario comun = EscenarioFactory.crearEscenario(TiposEnum.COMUN);
        comun.fabricarMascota(MascotasEnum.GATO);

        Mascota mascota = comun.getMascotaActual();
        System.out.println(mascota.toString());
        System.out.println("salud: " + mascota.verSalud());
        System.out.println("hambre: " + mascota.verHambre());
        System.out.println("felicidad: " + mascota.verFelicidad());

        SwingUtilities.invokeLater(VentanaPrincipal::new);


    }
}