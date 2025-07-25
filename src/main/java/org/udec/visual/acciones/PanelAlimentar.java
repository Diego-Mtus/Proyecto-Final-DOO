package org.udec.visual.acciones;

import org.udec.mascotas.Mascota;
import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.AlimentosEnum;
import org.udec.util.enumerations.BotonesUI;
import org.udec.visual.JButtonAnimado;
import org.udec.visual.PanelEscenario;
import org.udec.visual.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Panel para alimentar a la mascota actual.
 * Permite seleccionar entre diferentes tipos de comida y arrastrarla hacia la mascota.
 */
public class PanelAlimentar extends JPanel {

    private final int posicionInicialX = VentanaPrincipal.ANCHO / 2 - 40;
    private final int posicionInicialY = VentanaPrincipal.ALTO - 124;
    private int comidaX = posicionInicialX;
    private int comidaY = posicionInicialY;
    private final int comidaSize = 80;
    private final int comidaRadio = comidaSize / 2;

    private ArrayList<AlimentosEnum> alimentosDisponibles = new ArrayList<>();
    private ArrayList<BufferedImage> imagenesComida = new ArrayList<>();
    private Mascota mascotaActual;

    private int indiceComida = 0;
    private JButton botonIzquierda;
    private JButton botonDerecha;

    private int[] posicionMascota; // x, y, x + ancho, y + alto

    private Font fuente = new Font("Arial", Font.BOLD, 14);
    private int mouseX, mouseY;
    private boolean isDragging = false;

    /**
     * Constructor del panel de alimentar.
     * Configura el panel, los botones y los eventos de arrastre para medicar a la mascota.
     */
    public PanelAlimentar() {
        this.setLayout(null);
        this.setBounds(0, 0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);

        botonIzquierda = new JButtonAnimado(BotonesUI.BOTON_IZQUERDASMALLER.getImagen(),VentanaPrincipal.ANCHO / 2 - 64, VentanaPrincipal.ALTO - 40, 30, 30);
        botonIzquierda.addActionListener(e -> cambiarAlimento(-1));
        botonIzquierda.setVisible(alimentosDisponibles.size() > 1);
        this.add(botonIzquierda);

        botonDerecha = new JButtonAnimado(BotonesUI.BOTON_DERECHASMALLER.getImagen(), VentanaPrincipal.ANCHO / 2 + 34, VentanaPrincipal.ALTO - 40, 30, 30);
        botonDerecha.addActionListener(e -> cambiarAlimento(1));
        botonDerecha.setVisible(alimentosDisponibles.size() > 1);
        this.add(botonDerecha);

        actualizarListaAlimentos();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (alimentosDisponibles.isEmpty()) return;
                int centerX = comidaX + comidaRadio;
                int centerY = comidaY + comidaRadio;

                int distanciaCuadrado = (e.getX() - centerX) * (e.getX() - centerX)
                        + (e.getY() - centerY) * (e.getY() - centerY);


                if(distanciaCuadrado <= comidaRadio * comidaRadio) {
                    isDragging = true;
                    mouseX = e.getX();
                    mouseY = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (alimentosDisponibles.isEmpty()) return;
                if(isDragging){
                    if(e.getX() >= posicionMascota[0] && e.getX() <= posicionMascota[2] &&
                            e.getY() >= posicionMascota[1] && e.getY() <= posicionMascota[3]) {
                        if (mascotaActual != null) {
                            alimentosDisponibles.get(indiceComida).alimentar(mascotaActual);
                        // Regresa la comida a la posición inicial
                            alimentosDisponibles.get(indiceComida).setInventario(
                                alimentosDisponibles.get(indiceComida).getInventario() - 1);

                            comidaX = posicionInicialX;
                            comidaY = posicionInicialY;
                        }
                        actualizarListaAlimentos();
                        cambiarAlimento(0);

                    } else {
                        // Regresa la comida a la posición inicial con animación
                        animarRegresoComida();
                    }
                    isDragging = false;
                    repaint();
                }
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (alimentosDisponibles.isEmpty()) return;
                if (isDragging) {
                    // Mueve la comida con el mouse
                    comidaX = e.getX() - comidaRadio;
                    comidaY = e.getY() - comidaRadio;
                    repaint();
                }
            }
        });


        repaint();
    }

    /**
     * Establece la mascota actual del panel de escenario.
     * Actualiza la posición de la mascota para el arrastre de comida.
     *
     * @param panelEscenario El panel de escenario que contiene la mascota actual.
     */
    public void setMascotaActual(PanelEscenario panelEscenario){
        this.mascotaActual = panelEscenario.getEscenario().getMascotaActual();
        this.posicionMascota = panelEscenario.getPosicionMascota();
    }

    /**
     * Actualiza la lista de alimentos disponibles.
     * Limpia la lista actual y agrega los alimentos que tienen inventario mayor a 0.
     * También actualiza las imágenes de los alimentos.
     */
    public void actualizarListaAlimentos() {
        alimentosDisponibles.clear();
        imagenesComida.clear();
        for(AlimentosEnum alimento : AlimentosEnum.values()) {
            if(alimento.getInventario() > 0){
                alimentosDisponibles.add(alimento);
                imagenesComida.add(CargadorDeImagenes.cargarImagen(alimento.getRutaImagen()));
            }

        }
        botonIzquierda.setVisible(alimentosDisponibles.size() > 1);
        botonDerecha.setVisible(alimentosDisponibles.size() > 1);
        indiceComida = 0; // Reinicia el índice al actualizar la lista
        repaint();

    }

    private void cambiarAlimento(int direccion) {
        if (alimentosDisponibles.isEmpty()) return;
        indiceComida = (indiceComida + direccion + alimentosDisponibles.size()) % alimentosDisponibles.size();
        comidaX = posicionInicialX;
        comidaY = posicionInicialY;
        repaint();
    }

    private void animarRegresoComida() {
        final int pasos = 10;
        final int inicioX = comidaX;
        final int inicioY = comidaY;
        final int deltaX = posicionInicialX - inicioX;
        final int deltaY = posicionInicialY - inicioY;

        new Thread(() -> {
            for (int i = 1; i <= pasos; i++) {
                comidaX = inicioX + deltaX * i / pasos;
                comidaY = inicioY + deltaY * i / pasos;
                repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException ignored) {}
            }
            comidaX = posicionInicialX;
            comidaY = posicionInicialY;
            repaint();
        }).start();
    }

    @Override
    public boolean contains(int x, int y) {
        int centerX = comidaX + comidaRadio;
        int centerY = comidaY + comidaRadio;
        int distanciaCuadrado = (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY);

        // Verifica si el mouse está dentro del área de la comida o sobre los botones
        return distanciaCuadrado <= comidaRadio * comidaRadio || x >= botonIzquierda.getX() && x <= botonDerecha.getX() + botonDerecha.getWidth()
                && y >= botonIzquierda.getY() && y <= botonIzquierda.getY() + botonIzquierda.getHeight();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        // Dibujar texto de cantidad y nombre de la comida
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.setFont(fuente);
        FontMetrics fontMetrics = g2d.getFontMetrics();

        if (alimentosDisponibles.isEmpty()) {
            g2d.drawString("COMIDA", posicionInicialX + comidaRadio - fontMetrics.stringWidth("COMIDA") / 2, posicionInicialY + comidaRadio + 6);
            return;
        }

        // Dibuja la comida
        g.drawImage(imagenesComida.get(indiceComida), comidaX, comidaY, comidaSize, comidaSize, this);



        String nombreComida = alimentosDisponibles.get(indiceComida).getNombre();
        int textoX = posicionInicialX + comidaRadio - fontMetrics.stringWidth(nombreComida) / 2;
        int textoY = posicionInicialY - 12; // Ajusta la posición del texto arriba de la comida
        g2d.drawString(nombreComida, textoX, textoY);

        String cantidad = "x " + alimentosDisponibles.get(indiceComida).getInventario();
        textoX = posicionInicialX + comidaRadio - fontMetrics.stringWidth(cantidad) / 2;
        textoY = posicionInicialY + comidaSize + 24; // Ajusta la posición del texto debajo de la comida
        g2d.drawString(cantidad, textoX, textoY);


    }

}
