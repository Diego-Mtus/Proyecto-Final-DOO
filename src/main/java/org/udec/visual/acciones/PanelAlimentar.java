package org.udec.visual.acciones;

import org.udec.mascotas.Mascota;
import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.AlimentosEnum;
import org.udec.visual.PanelEscenario;
import org.udec.visual.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    // Posicion de la mascota
    private final int mascotaPosX = VentanaPrincipal.ANCHO / 2 - 150;
    private final int mascotaPosY = VentanaPrincipal.ALTO / 2 - 150;
    private final int mascotaSize = 300;

    private Font fuente = new Font("Arial", Font.BOLD, 14);
    private int mouseX, mouseY;
    private boolean isDragging = false;

    public PanelAlimentar() {
        this.setLayout(null);
        this.setBounds(0, 0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);

        botonIzquierda = new JButton("<");
        botonIzquierda.setBounds(VentanaPrincipal.ANCHO / 2 - 64, VentanaPrincipal.ALTO - 40, 40, 30);
        botonIzquierda.addActionListener(e -> cambiarMedicamento(-1));
        botonIzquierda.setVisible(alimentosDisponibles.size() > 1);
        this.add(botonIzquierda);

        botonDerecha = new JButton(">");
        botonDerecha.setBounds(VentanaPrincipal.ANCHO / 2 + 24, VentanaPrincipal.ALTO - 40, 40, 30);
        botonDerecha.addActionListener(e -> cambiarMedicamento(1));
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
                    if(e.getX() >= mascotaPosX && e.getX() <= mascotaPosX + mascotaSize &&
                            e.getY() >= mascotaPosY && e.getY() <= mascotaPosY + mascotaSize) {
                        System.out.println("Alimentando "+ alimentosDisponibles.get(indiceComida).getNombre() + " a la mascota en: " + mouseX + ", " + mouseY);
                        if (mascotaActual != null) {
                            alimentosDisponibles.get(indiceComida).alimentar(mascotaActual);
                        // Regresa la comida a la posición inicial
                            alimentosDisponibles.get(indiceComida).setInventario(
                                alimentosDisponibles.get(indiceComida).getInventario() - 1);

                            comidaX = posicionInicialX;
                            comidaY = posicionInicialY;
                        }
                        actualizarListaAlimentos();
                        cambiarMedicamento(0);

                    } else {
                        System.out.println("La comida no llegó a la mascota.");
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

    public void setMascotaActual(PanelEscenario panelEscenario){
        this.mascotaActual = panelEscenario.getEscenario().getMascotaActual();
    }

    public void actualizarListaAlimentos() {
        alimentosDisponibles.clear();
        imagenesComida.clear();
        for(AlimentosEnum alimento : AlimentosEnum.values()) {
            if(alimento.getInventario() > 0){
                alimentosDisponibles.add(alimento);
                imagenesComida.add(CargadorDeImagenes.cargarImagen(alimento.getRutaImagen()));
            }

        }
        System.out.println(alimentosDisponibles);
        botonIzquierda.setVisible(alimentosDisponibles.size() > 1);
        botonDerecha.setVisible(alimentosDisponibles.size() > 1);
        indiceComida = 0; // Reinicia el índice al actualizar la lista
        repaint();

    }

    private void cambiarMedicamento(int direccion) {
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
