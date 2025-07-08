package org.udec;

import org.udec.visual.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main() {

        SwingUtilities.invokeLater(VentanaPrincipal::new);

    }
}