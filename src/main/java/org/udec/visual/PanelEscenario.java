package org.udec.visual;

import org.udec.mascotas.*;
import org.udec.util.CargadorDeImagenes;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelEscenario extends JPanel {

    private Escenario escenario;
    private BufferedImage imagenEscenario;
    private Mascota mascota;
    private BufferedImage imagenMascota;

    private JButton botonAdoptarMascota; // Esto será su propia clase más adelante

    public PanelEscenario(){
        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(0,0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/base.png");
        crearBotonDeInicializarEscenario();
        crearBotonAdoptarMascota();
    }

    private void crearBotonDeInicializarEscenario(){
        JButton boton = new JButton("Inicializar Escenario");
        boton.setFocusable(false);
        boton.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 50, 200, 50);
        this.add(boton);
        boton.addActionListener(e -> {
            System.out.println("Inicializar Escenario prueba");
            escenario = EscenarioFactory.crearEscenario(TiposEnum.COMUN);
            imagenEscenario = escenario.getImagenEscenario();
            boton.setVisible(false);
            repaint();
        });
    }

    private void crearBotonAdoptarMascota(){
        botonAdoptarMascota = new JButton("Adoptar Mascota");
        botonAdoptarMascota.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 50, 200, 50);
        botonAdoptarMascota.setVisible(false);
        botonAdoptarMascota.setFocusable(false);
        this.add(botonAdoptarMascota);
        botonAdoptarMascota.addActionListener(e -> {
            if (escenario != null) {
                escenario.fabricarMascota(MascotasEnum.GATO); // Por ejemplo, adoptar un gato
                mascota = escenario.getMascotaActual();
                if (mascota != null) {
                    imagenMascota = mascota.getImagenMascota();
                    botonAdoptarMascota.setVisible(false);
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo adoptar la mascota.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(imagenEscenario, 0, 0, imagenEscenario.getWidth(), imagenEscenario.getHeight(), this);

        if(mascota == null && escenario != null) {
            botonAdoptarMascota.setVisible(true);

        } else if (mascota != null) {
            g.setColor(Color.BLACK);
            g.drawString("Mascota: " + mascota.getNombreAnimal(), 10, 20);
            g.drawString("Nombre: " + mascota.getNombrePropio(), 10, 40);
            g.drawString("Salud: " + mascota.verSalud(), 10, 60);
            g.drawString("Hambre: " + mascota.verHambre(), 10, 80);
            g.drawString("Felicidad: " + mascota.verFelicidad(), 10, 100);
            g.drawImage(imagenMascota, getWidth() / 2 - 150, getHeight() / 2 - 150, 300, 300, this);
        }
    }
}
