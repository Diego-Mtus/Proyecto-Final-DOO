package org.udec;

import org.udec.mascotas.*;
import org.udec.visual.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.out.println(TiposEnum.ROEDOR.mascotasCompatibles());

        SwingUtilities.invokeLater(VentanaPrincipal::new);


    }
}