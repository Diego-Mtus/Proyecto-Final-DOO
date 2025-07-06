package org.udec.visual.acciones;

import org.udec.mascotas.Mascota;
import org.udec.visual.PanelEscenario;
import org.udec.visual.DineroObtenidoListener;
import org.udec.visual.VentanaPrincipal;

import javax.swing.*;

public class PanelJuegos extends JPanel {

    private Mascota mascotaActual;
    private JButton botonJugar;

    private DineroObtenidoListener dineroObtenidoListener;

    public PanelJuegos(){
        this.setLayout(null);
        this.setBounds(0, 0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);

        this.setOpaque(false); // Hacer el panel transparente

        botonJugar = new JButton("Jugar");
        botonJugar.setBounds(VentanaPrincipal.ANCHO / 2 - 50, VentanaPrincipal.ALTO - 104, 100, 50);
        botonJugar.addActionListener(e -> this.jugar());
        botonJugar.setFocusable(false);
        botonJugar.setVisible(true);
        this.add(botonJugar);
    }

    public void setMascotaActual(PanelEscenario panelEscenario) {
        this.mascotaActual = panelEscenario.getEscenario().getMascotaActual();
    }

    public void setDineroObtenidoListener(DineroObtenidoListener dineroObtenidoListener) {
        this.dineroObtenidoListener = dineroObtenidoListener;
    }

    private void jugar() {
        if (mascotaActual != null) {
            System.out.println("Jugando con la mascota: " + mascotaActual.getNombreAnimal());
            switch (mascotaActual.getTipo()) {
                case COMUN -> new JuegoComun(mascotaActual.getImagenMascota(), this);
                case ROEDOR -> System.out.println("Jugando con una mascota roedor.");
                case VOLADOR -> System.out.println("Jugando con una mascota voladora.");
                case ACUATICO -> System.out.println("Jugando con una mascota acuática.");
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay mascota seleccionada.");
        }
    }

    void victoriaJuego(){
        if (mascotaActual != null) {
            mascotaActual.getEstado().addFelicidad(40);

            dineroObtenidoListener.dineroObtenido(30);
            repaint();
        }
    }

    void derrotaJuego(){
        if (mascotaActual != null) {
            mascotaActual.getEstado().setSalud(mascotaActual.getEstado().verSalud() - 10);
            repaint();
        }
    }

}
