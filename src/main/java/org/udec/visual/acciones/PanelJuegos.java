package org.udec.visual.acciones;

import org.udec.mascotas.Mascota;
import org.udec.util.enumerations.BotonesUI;
import org.udec.visual.JButtonAnimado;
import org.udec.visual.PanelEscenario;
import org.udec.util.listeners.DineroObtenidoListener;
import org.udec.visual.VentanaPrincipal;

import javax.swing.*;

/**
 * PanelJuegos es un panel que permite al usuario jugar con su mascota.
 * Contiene un botón para iniciar el juego y maneja la lógica de victoria y derrota.
 */
public class PanelJuegos extends JPanel {

    private Mascota mascotaActual;
    private JButton botonJugar;

    private DineroObtenidoListener dineroObtenidoListener;

    /**
     * Constructor del panel de juegos.
     * Configura el layout, tamaño y añade el botón de jugar.
     */
    public PanelJuegos(){
        this.setLayout(null);
        this.setBounds(0, 0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);

        this.setOpaque(false); // Hacer el panel transparente

        botonJugar = new JButtonAnimado(BotonesUI.BOTON_JUGAR.getImagen(), VentanaPrincipal.ANCHO / 2 - 50, VentanaPrincipal.ALTO - 104, 100, 50);
        botonJugar.addActionListener(e -> this.jugar());
        this.add(botonJugar);
    }

    /**
     * Método para establecer la mascota actual del panel de escenario.
     * Esto se utiliza para modificar el estado de la mascota después de jugar.
     *
     * @param panelEscenario El panel de escenario que contiene la mascota actual.
     */
    public void setMascotaActual(PanelEscenario panelEscenario) {
        this.mascotaActual = panelEscenario.getEscenario().getMascotaActual();
    }

    /**
     * Metodo para establecer el listener que se llamará cuando se obtenga dinero del juego.
     *
     * @param dineroObtenidoListener El listener que se llamará con la cantidad de dinero obtenida.
     */
    public void setDineroObtenidoListener(DineroObtenidoListener dineroObtenidoListener) {
        this.dineroObtenidoListener = dineroObtenidoListener;
    }

    private void jugar() {
        if (mascotaActual != null) {

            switch (mascotaActual.getTipo()) {
                case COMUN ->
                        SwingUtilities.invokeLater(() -> new JuegoComun(mascotaActual.getImagenMascotaJuego(), this));
                case ROEDOR ->
                        SwingUtilities.invokeLater(() -> new JuegoRoedor(mascotaActual.getImagenMascotaJuego(), this));
                case VOLADOR ->
                        SwingUtilities.invokeLater(() -> new JuegoVolador(mascotaActual.getImagenMascotaJuego(), this));
                case ACUATICO ->
                        SwingUtilities.invokeLater(() -> new JuegoAcuatico(mascotaActual.getImagenMascotaJuego(), this));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay mascota seleccionada.");
        }
    }

    /**
     * Método que se llama cuando se gana un juego.
     * Aumenta la felicidad de la mascota y notifica al listener de dinero obtenido.
     *
     * @param dineroObtenido Cantidad de dinero obtenida al ganar el juego.
     */
    void victoriaJuego(int dineroObtenido){
        if (mascotaActual != null) {
            mascotaActual.getEstado().addFelicidad(40);
            mascotaActual.getEstado().setQuiereJugar(false);
            dineroObtenidoListener.dineroObtenido(dineroObtenido);
            repaint();
        }
    }

    /**
     * Método que se llama cuando se pierde un juego.
     * Reduce la salud de la mascota y la marca como herida.
     */
    void derrotaJuego(){
        if (mascotaActual != null) {
            mascotaActual.getEstado().setSalud(mascotaActual.getEstado().verSalud() - 10);
            mascotaActual.getEstado().setQuiereJugar(false);
            mascotaActual.getEstado().setHerido(true);
            repaint();
        }
    }

}
