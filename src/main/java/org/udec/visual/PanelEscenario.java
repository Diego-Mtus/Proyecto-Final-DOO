package org.udec.visual;

import org.udec.escenarios.*;
import org.udec.mascotas.*;
import org.udec.util.*;
import org.udec.util.enumerations.TiposEnum;
import org.udec.util.threads.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelEscenario extends JPanel {

    private Escenario escenario;
    private BufferedImage imagenEscenario;

    private Mascota mascota;
    private MascotaInteractuable mascotaInteractuable;

    private Thread hiloComprador;

    private final JButton botonAdoptarMascota;
    private final JButton botonInicializarEscenario;
    private final JButton botonVenderMascota;

    private final PanelEstado panelEstado;

    private EscenarioListener escenarioListener;
    private AdopcionListener adopcionListener;

    public PanelEscenario(){
        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(0,0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/base.png");
        botonInicializarEscenario = new BotonInicializarEscenario(this, VentanaPrincipal.ANCHO / 2 - 100, VentanaPrincipal.ALTO / 2 - 50);
        botonAdoptarMascota = new BotonAdoptarMascota(this, VentanaPrincipal.ANCHO / 2 - 100, VentanaPrincipal.ALTO / 2 - 50);
        panelEstado = new PanelEstado(this, VentanaPrincipal.ANCHO / 2 - 130, 6);
        botonVenderMascota = new BotonVenderMascota(this, VentanaPrincipal.ANCHO - 70, VentanaPrincipal.ALTO - 140);
        crearMascotaInteractuable();

    }

    public void setEscenarioListener(EscenarioListener escenarioListener) {
        this.escenarioListener = escenarioListener;
    }

    public void setAdopcionListener(AdopcionListener adopcionListener) {
        this.adopcionListener = adopcionListener;
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
                panelEstado.inicializarEstado(mascota);
                adopcionListener.adopcionRealizada(this);
                repaint();

                } catch (MascotaViviendoException ex) {
                    JOptionPane.showMessageDialog(this, "Ya hay una mascota viviendo en ese escenario.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (TipoIncorrectoException ex) {
                    JOptionPane.showMessageDialog(this, "La mascota no es del mismo tipo que el escenario.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
    }

    public void inicializarHiloCompradorInteresado(){
        if (mascota != null) {
            hiloComprador = new Thread(new HiloCompradorInteresado(this));
            hiloComprador.start();
        }
    }

    public void ocultarBotonInicializarEscenario() {
        botonInicializarEscenario.setVisible(false);
    }

    public void mostrarBotonAdoptarMascota() {
        botonAdoptarMascota.setVisible(true);
    }

    public void mostrarBotonVenderMascota() {
        botonVenderMascota.setVisible(true);
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

    public boolean tieneMascota() {
        return mascota != null;
    }

    public void venderMascota(){
        escenario.venderMascota();
        mascota = null;
        mascotaInteractuable.removerMascota();
        panelEstado.detenerEstado();
        adopcionListener.ventaMascotaRealizada();
        if (hiloComprador != null && hiloComprador.isAlive()) {
            hiloComprador.interrupt();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenEscenario, 0, 0, imagenEscenario.getWidth(), imagenEscenario.getHeight(), this);
    }
}
