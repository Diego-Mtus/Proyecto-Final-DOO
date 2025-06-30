package org.udec.visual;

import org.udec.escenarios.Escenario;
import org.udec.escenarios.EscenarioFactory;
import org.udec.mascotas.*;
import org.udec.util.*;
import org.udec.visual.comandos.AdoptarMascotaCommand;
import org.udec.visual.comandos.Command;
import org.udec.visual.comandos.InicializarEscenarioCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelEscenario extends JPanel {

    private Escenario escenario;
    private BufferedImage imagenEscenario;

    private Mascota mascota;
    private MascotaInteractuable mascotaInteractuable;

    private Thread hiloActualizador;
    private Thread hiloComprador;

    private JButton botonAdoptarMascota; // Esto será su propia clase más adelante
    private JButton botonInicializarEscenario;

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


    public Escenario getEscenario() {
        return escenario;
    }

    public void establecerEscenario(TiposEnum escenarioTipo) {
        if(this.escenario == null) {
            this.escenario = EscenarioFactory.crearEscenario(escenarioTipo);
            escenarioListener.escenarioInicializado(this);
            this.imagenEscenario = escenario.getImagenEscenario();
            repaint();
        }
    }

    public void establecerMascota(MascotaFactory mascotaFactory){
        if (escenario != null) {
            try {
                mascotaFactory.crearMascota(escenario);
                mascota = escenario.getMascotaActual();
                mascotaInteractuable.setMascota(mascota);
                repaint();

                } catch (MascotaViviendoException ex) {
                    JOptionPane.showMessageDialog(this, "Ya hay una mascota viviendo en ese escenario.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (TipoIncorrectoException ex) {
                    JOptionPane.showMessageDialog(this, "La mascota no es del mismo tipo que el escenario.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
    }


    public void inicializarHiloActualizadorDeEstado(){
        System.out.println("Inicializando hilo actualizador de estado para la mascota: " + mascota.getNombrePropio());
        hiloActualizador = new Thread(new HiloActualizadorEstado(this));
        hiloActualizador.start();

    }

    public void inicializarHiloCompradorInteresado(){
        if (mascota != null) {
            hiloComprador = new Thread(new HiloCompradorInteresado(this));
            hiloComprador.start();
        }
    }

    private void crearBotonDeInicializarEscenario(){
        botonInicializarEscenario = new JButton("Inicializar Escenario");
        botonInicializarEscenario.setFocusable(false);
        botonInicializarEscenario.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 50, 200, 50);

        this.add(botonInicializarEscenario);

        Command comando = new InicializarEscenarioCommand(this);
        botonInicializarEscenario.addActionListener(e -> comando.execute());
    }


    private void crearBotonAdoptarMascota(){
        botonAdoptarMascota = new JButton("Adoptar Mascota");
        botonAdoptarMascota.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 50, 200, 50);
        botonAdoptarMascota.setVisible(false);
        botonAdoptarMascota.setFocusable(false);

        this.add(botonAdoptarMascota);

        Command comando = new AdoptarMascotaCommand(this);
        botonAdoptarMascota.addActionListener( e -> comando.execute());
    }

    public void ocultarBotonInicializarEscenario() {
        botonInicializarEscenario.setVisible(false);
    }

    public void mostrarBotonAdoptarMascota() {
        botonAdoptarMascota.setVisible(true);
    }

    public void ocultarBotonAdoptarMascota() {
        botonAdoptarMascota.setVisible(false);
    }

    private void crearMascotaInteractuable() {
        this.mascotaInteractuable = new MascotaInteractuable(this, VentanaPrincipal.ANCHO / 2 - 150, VentanaPrincipal.ALTO / 2 - 150, 300, 300);
        this.add(mascotaInteractuable);
    }


    public boolean tieneEscenario() {
        return escenario != null;
    }

    public void actualizarVisualEstado(){
        repaint(0,0,120,120);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(imagenEscenario, 0, 0, imagenEscenario.getWidth(), imagenEscenario.getHeight(), this);

        if (mascota != null) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(mascota.getNombrePropio() + " - " + mascota.getNombreAnimal(), 10, 20);
            g.drawString("Salud: " + mascota.getEstado().verSalud(), 10, 60);
            g.drawString("Hambre: " + mascota.getEstado().verHambre(), 10, 80);
            g.drawString("Felicidad: " + mascota.getEstado().verFelicidad(), 10, 100);
        }
    }
}
