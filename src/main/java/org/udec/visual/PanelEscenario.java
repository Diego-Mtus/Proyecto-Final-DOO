package org.udec.visual;

import org.udec.escenarios.*;
import org.udec.mascotas.*;
import org.udec.util.*;
import org.udec.util.enumerations.TiposEnum;
import org.udec.util.threads.*;
import org.udec.util.listeners.AdopcionListener;
import org.udec.util.listeners.EscenarioListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Panel que representa el escenario del juego, donde se pueden adoptar y vender mascotas.
 * Contiene botones para crear un escenario, adoptar una mascota y vender una mascota.
 */
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

    /**
     * Constructor del panel de escenario.
     * Configura el layout, tamaño y añade los botones necesarios.
     */
    public PanelEscenario(){
        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(0,0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/base.png");
        botonInicializarEscenario = new BotonInicializarEscenario(this, VentanaPrincipal.ANCHO / 2 - 120, VentanaPrincipal.ALTO / 2 - 60);
        botonAdoptarMascota = new BotonAdoptarMascota(this, VentanaPrincipal.ANCHO / 2 - 120, VentanaPrincipal.ALTO / 2 - 60);
        panelEstado = new PanelEstado(this, VentanaPrincipal.ANCHO / 2 - 130, 6);
        botonVenderMascota = new BotonVenderMascota(this, VentanaPrincipal.ANCHO - 70, VentanaPrincipal.ALTO - 160);
        crearMascotaInteractuable();

    }


    /**
     * Establece el listener para manejar la inicialización del escenario.
     * @param escenarioListener El listener que manejará la inicialización del escenario.
     */
    public void setEscenarioListener(EscenarioListener escenarioListener) {
        this.escenarioListener = escenarioListener;
    }

    /**
     * Establece el listener para manejar adopciones y ventas de mascotas.
     * @param adopcionListener El listener que manejará las adopciones y ventas.
     */
    public void setAdopcionListener(AdopcionListener adopcionListener) {
        this.adopcionListener = adopcionListener;
    }

    /**
     * Inicializa el hilo del comprador interesado en adoptar una mascota.
     * Este hilo se encarga de simular un comprador que está interesado en adoptar una mascota.
     */
    public void inicializarHiloCompradorInteresado(){
        if (mascota != null) {
            hiloComprador = new Thread(new HiloCompradorInteresado(this));
            hiloComprador.start();
        }
    }

    /**
     * Crea una mascota interactuable y la añade al panel.
     * Esta mascota se puede interactuar con ella, mostrando animaciones y sonidos.
     */
    private void crearMascotaInteractuable() {
        this.mascotaInteractuable = new MascotaInteractuable(this);
        this.add(mascotaInteractuable);
    }

    /**
     * Muestra el botón de adoptar mascota.
     * Este método se utiliza cuando hay un escenario inicializado y se puede adoptar una mascota.
     */
    public void mostrarBotonAdoptarMascota() { botonAdoptarMascota.setVisible(true); }

    /**
     * Oculta el botón de adoptar mascota.
     * Este método se utiliza cuando ya se ha adoptado una mascota.
     */
    public void ocultarBotonAdoptarMascota() { botonAdoptarMascota.setVisible(false); }

    /**
     * Muestra el botón de vender mascota.
     * Este método se llama cuando el hilo comprador se interesa en adoptar una mascota.
     */
    public void mostrarBotonVenderMascota() { botonVenderMascota.setVisible(true); }

    /**
     * Oculta el botón de vender mascota.
     * Este método se utiliza cuando ya se vende una mascota.
     */
    public void ocultarBotonVenderMascota() { botonVenderMascota.setVisible(false); }

    /**
     * Oculta el botón de inicializar escenario.
     * Este método se utiliza cuando se ha inicializado un escenario y ya no es necesario mostrar el botón.
     */
    public void ocultarBotonInicializarEscenario() { botonInicializarEscenario.setVisible(false); }

    /**
     * Verifica si el panel tiene un escenario inicializado.
     * @return true si hay un escenario inicializado, false en caso contrario.
     */
    public boolean tieneEscenario() {
        return escenario != null;
    }

    /**
     * Verifica si el panel tiene una mascota adoptada.
     * @return true si hay una mascota adoptada, false en caso contrario.
     */
    public boolean tieneMascota() {
        return mascota != null;
    }

    /**
     * Obtiene el escenario actual del panel.
     * @return El escenario actual, o null si no hay un escenario inicializado.
     */
    public Escenario getEscenario() {
        return escenario;
    }

    /**
     * Obtiene la posicion de la mascota interactuable en el panel.
     * @return Un arreglo de enteros representando la posición de la mascota interactuable.
     */

    public int[] getPosicionMascota() {
        if (mascota != null) {
            return mascotaInteractuable.getPosicionMascota();
        }
        return new int[]{0, 0, 0, 0};
    }

    /**
     * Establece el escenario en el panel.
     * Crea un nuevo escenario basado en el tipo proporcionado y lo inicializa.
     * Notifica al listener que el escenario ha sido inicializado.
     * @param escenarioTipo El tipo de escenario a crear.
     */
    public void establecerEscenario(TiposEnum escenarioTipo) {
        if(this.escenario == null) {
            this.escenario = EscenarioFactory.crearEscenario(escenarioTipo);
            escenarioListener.escenarioInicializado(this);
            this.imagenEscenario = escenario.getImagenEscenario();
            repaint();
        }
    }

    /**
     * Establece una mascota en el escenario.
     * Utiliza un factory para crear la mascota y la asigna al escenario.
     * Si la mascota ya está viviendo en el escenario, muestra un mensaje de error.
     * Si la mascota es del tipo incorrecto para el escenario, muestra un mensaje de error.
     * @param mascotaFactory El factory utilizado para crear la mascota.
     */
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

    /**
     * Vende la mascota actual del escenario.
     * Obtiene el dinero obtenido de la venta, notifica al listener de la venta,
     * y limpia la mascota del escenario.
     * Si hay un hilo comprador activo, lo interrumpe.
     */
    public void venderMascota(){
        int dineroObtenido = mascota.getPrecioVenta();
        escenario.venderMascota();
        mascota = null;
        mascotaInteractuable.removerMascota();
        panelEstado.detenerEstado();
        adopcionListener.ventaMascotaRealizada(dineroObtenido);
        if (hiloComprador != null && hiloComprador.isAlive()) {
            hiloComprador.interrupt();
        }

        ocultarBotonVenderMascota();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(imagenEscenario, 0, 0, imagenEscenario.getWidth(), imagenEscenario.getHeight(), this);
    }
}
