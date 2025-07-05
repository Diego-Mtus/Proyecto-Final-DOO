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

    private final int posicionInicialX = VentanaPrincipal.ANCHO / 2 - 50;
    private final int posicionInicialY = VentanaPrincipal.ALTO - 120;
    private int comidaX = posicionInicialX;
    private int comidaY = posicionInicialY;
    private final int comidaSize = 100;
    private final int comidaRadio = comidaSize / 2;

    private ArrayList<AlimentosEnum> alimentosDisponibles = new ArrayList<>();
    private ArrayList<BufferedImage> imagenesComida = new ArrayList<>();
    private Mascota mascotaActual;

    private final PanelAcciones panelAcciones;
    private int indiceComida = 0;
    private JButton botonIzquierda;
    private JButton botonDerecha;

    // Posicion de la mascota
    private final int mascotaPosX = VentanaPrincipal.ANCHO / 2 - 150;
    private final int mascotaPosY = VentanaPrincipal.ALTO / 2 - 150;
    private final int mascotaSize = 300;

    private int mouseX, mouseY;
    private boolean isDragging = false;

    public PanelAlimentar(PanelAcciones panelAcciones) {
        this.setLayout(null);
        this.setBounds(0, 0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);
        this.panelAcciones = panelAcciones;

        botonIzquierda = new JButton("<");
        botonIzquierda.setBounds(VentanaPrincipal.ANCHO / 2 - 52, VentanaPrincipal.ALTO - 40, 50, 30);
        botonIzquierda.addActionListener(e -> cambiarComida(-1));
        botonIzquierda.setVisible(alimentosDisponibles.size() > 1);
        this.add(botonIzquierda);

        botonDerecha = new JButton(">");
        botonDerecha.setBounds(VentanaPrincipal.ANCHO / 2 + 2, VentanaPrincipal.ALTO - 40, 50, 30);
        botonDerecha.addActionListener(e -> cambiarComida(1));
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
                        // Aquí lógica de alimentar

                        if (mascotaActual != null) {
                            alimentosDisponibles.get(indiceComida).alimentar(mascotaActual);
                        // Regresa la comida a la posición inicial
                            alimentosDisponibles.get(indiceComida).setInventario(
                                alimentosDisponibles.get(indiceComida).getInventario() - 1);

                            comidaX = posicionInicialX;
                            comidaY = posicionInicialY;
                        }
                        actualizarListaAlimentos();
                        cambiarComida(0);

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

    private void cambiarComida(int direccion) {
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

        if (alimentosDisponibles.isEmpty()) {
            g.drawString("No hay alimentos disponibles", 10, 20);
            return;
        }

        // Dibuja la comida
        g.drawImage(imagenesComida.get(indiceComida), comidaX, comidaY, comidaSize, comidaSize, this);

    }

}
