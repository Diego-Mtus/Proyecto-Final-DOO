package org.udec;

import org.udec.mascotas.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new VentanaPrincipal();

        System.out.println(TiposEnum.ROEDOR.mascotasCompatibles());

        HabitatFactory factory = new RoedorFactory();

        Escenario escenario = factory.crearEscenario();
        Mascota mascota = factory.crearMascota(MascotasEnum.RATON);

        escenario.alojarMascota(mascota);

        System.out.println(escenario.getMascotaActual());
    }
}