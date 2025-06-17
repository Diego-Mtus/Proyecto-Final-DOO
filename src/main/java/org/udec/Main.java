package org.udec;

import org.udec.mascotas.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new VentanaPrincipal();
        Mascota test = new Golondrina();
        System.out.println(test.getNombrePropio());
    }
}