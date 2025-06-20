package org.udec;

import org.udec.mascotas.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new VentanaPrincipal();

        System.out.println(TiposEnum.ROEDOR.mascotasCompatibles());

        Escenario acuatico = EscenarioFactory.crearEscenario(TiposEnum.ACUATICO);
        acuatico.fabricarMascota(MascotasEnum.GATO);
        acuatico.fabricarMascota(MascotasEnum.PEZDORADO);

        Mascota mascota = acuatico.getMascotaActual();
        System.out.println(mascota.toString());

    }
}