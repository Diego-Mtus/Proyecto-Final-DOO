package org.udec;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public static final int ANCHO = 640;
    public static final int ALTO = 860;

    VentanaPrincipal(){
        this.setTitle("Simulador de mascota");
        this.setSize(new Dimension(ANCHO, ALTO));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
