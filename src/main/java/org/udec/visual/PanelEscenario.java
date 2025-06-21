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
    private MascotaInteractuable mascotaInteractuable;

    private JButton botonAdoptarMascota; // Esto será su propia clase más adelante

    private EscenarioListener escenarioListener;

    public PanelEscenario(){
        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(0,0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/base.png");
        crearBotonDeInicializarEscenario();
        crearBotonAdoptarMascota();
        crearMascotaInteractuable();
    }

    public void setEscenarioListener(EscenarioListener escenarioListener) {
        this.escenarioListener = escenarioListener;
    }

    public BufferedImage getImagenMascota() {
        return imagenMascota;
    }

    public Escenario getEscenario() {
        return escenario;
    }

    private void crearBotonDeInicializarEscenario(){
        JButton boton = new JButton("Inicializar Escenario");
        boton.setFocusable(false);
        boton.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 50, 200, 50);
        this.add(boton);
        boton.addActionListener(e -> {
            System.out.println("Inicializar Escenario prueba");
            escenario = EscenarioFactory.crearEscenario(TiposEnum.COMUN);

            escenarioListener.escenarioInicializado(this);
            imagenEscenario = escenario.getImagenEscenario();
            boton.setVisible(false);
            repaint();
        });
    }

    private void crearMascotaInteractuable() {
         this.mascotaInteractuable = new MascotaInteractuable(this, VentanaPrincipal.ANCHO / 2 - 150, VentanaPrincipal.ALTO / 2 - 150, 300, 300);
         this.add(mascotaInteractuable);
    }

    private void crearBotonAdoptarMascota(){
        botonAdoptarMascota = new JButton("Adoptar Mascota");
        botonAdoptarMascota.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 50, 200, 50);
        botonAdoptarMascota.setVisible(false);
        botonAdoptarMascota.setFocusable(false);
        this.add(botonAdoptarMascota);
        botonAdoptarMascota.addActionListener(e -> {
            if (escenario != null) {
                escenario.crearMascota(MascotasEnum.GATO); // Por ejemplo, adoptar un gato
                mascota = escenario.getMascotaActual();
                if (mascota != null) {

                    mascotaInteractuable.setMascota(mascota);
                    botonAdoptarMascota.setVisible(false);
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo adoptar la mascota.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

    public boolean tieneEscenario() {
        return escenario != null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(imagenEscenario, 0, 0, imagenEscenario.getWidth(), imagenEscenario.getHeight(), this);

        if(mascota == null && escenario != null) {
            botonAdoptarMascota.setVisible(true);

        } else if (mascota != null) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(mascota.getNombrePropio() + " - " + mascota.getNombreAnimal(), 10, 20);
            g.drawString("Salud: " + mascota.verSalud(), 10, 60);
            g.drawString("Hambre: " + mascota.verHambre(), 10, 80);
            g.drawString("Felicidad: " + mascota.verFelicidad(), 10, 100);
        }
    }
}
