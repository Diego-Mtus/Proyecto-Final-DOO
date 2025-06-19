package org.udec;

import org.udec.mascotas.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new VentanaPrincipal();

        System.out.println(TiposEnum.ROEDOR.mascotasCompatibles());

        HabitatFactory factory = new RoedorFactory();

        Escenario escenario = factory.crearEscenario();

        factory.crearMascotaParaEscenario(MascotasEnum.RATON, escenario);
        escenario.venderMascota();

        System.out.println(escenario.getMascotaActual());
    }
}