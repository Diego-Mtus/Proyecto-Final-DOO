package org.udec;

import org.udec.escenarios.Escenario;
import org.udec.mascotas.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new VentanaPrincipal();
        Mascota test = new Golondrina();
        System.out.println(test.getNombrePropio());

        System.out.println(TiposEnum.ROEDOR.mascotasCompatibles());

        HabitatFactory factory = new RoedorFactory();
        Escenario escenario = factory.crearEscenario();
        Mascota mascota = factory.crearMascota(MascotasEnum.GATO);

        escenario.alojarMascota(mascota);

        System.out.println(escenario.getMascotaActual());
    }
}