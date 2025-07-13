package org.udec.visual.acciones;

import org.udec.mascotas.Mascota;
import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.BotonesUI;
import org.udec.util.enumerations.MedicinasEnum;
import org.udec.visual.JButtonAnimado;
import org.udec.visual.PanelEscenario;
import org.udec.visual.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PanelMedicar extends JPanel {

    private final int posicionInicialX = VentanaPrincipal.ANCHO / 2 - 40;
    private final int posicionInicialY = VentanaPrincipal.ALTO - 124;
    private int medicinaX = posicionInicialX;
    private int medicinaY = posicionInicialY;
    private final int medicinaSize = 80;
    private final int medicinaRadio = medicinaSize / 2;

    private ArrayList<MedicinasEnum> medicinasDisponibles = new ArrayList<>();
    private ArrayList<BufferedImage> imagenesMedicina = new ArrayList<>();
    private Mascota mascotaActual;

    private int indiceMedicina = 0;
    private JButton botonIzquierda;
    private JButton botonDerecha;

    // Posicion de la mascota
    private int[] posicionMascota; // x, y, x + ancho, y + alto

    private Font fuente = new Font("Arial", Font.BOLD, 14);
    private int mouseX, mouseY;
    private boolean isDragging = false;

    public PanelMedicar() {
        this.setLayout(null);
        this.setBounds(0, 0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);

        botonIzquierda = new JButtonAnimado(BotonesUI.BOTON_IZQUERDASMALLER.getImagen(), VentanaPrincipal.ANCHO / 2 - 64, VentanaPrincipal.ALTO - 40, 30, 30);
        botonIzquierda.addActionListener(e -> cambiarMedicina(-1));
        botonIzquierda.setVisible(medicinasDisponibles.size() > 1);
        this.add(botonIzquierda);

        botonDerecha = new JButtonAnimado(BotonesUI.BOTON_DERECHASMALLER.getImagen(),VentanaPrincipal.ANCHO / 2 + 34, VentanaPrincipal.ALTO - 40, 30, 30);
        botonDerecha.addActionListener(e -> cambiarMedicina(1));
        botonDerecha.setVisible(medicinasDisponibles.size() > 1);
        this.add(botonDerecha);

        actualizarListaMedicamentos();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (medicinasDisponibles.isEmpty()) return;
                int centerX = medicinaX + medicinaRadio;
                int centerY = medicinaY + medicinaRadio;
                int distanciaCuadrado = (e.getX() - centerX) * (e.getX() - centerX)
                        + (e.getY() - centerY) * (e.getY() - centerY);

                if(distanciaCuadrado <= medicinaRadio * medicinaRadio) {
                    isDragging = true;
                    mouseX = e.getX();
                    mouseY = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (medicinasDisponibles.isEmpty()) return;
                if(isDragging){
                    if(e.getX() >= posicionMascota[0] && e.getX() <= posicionMascota[2] &&
                            e.getY() >= posicionMascota[1] && e.getY() <= posicionMascota[3]) {
                        if (mascotaActual != null) {
                            medicinasDisponibles.get(indiceMedicina).curar(mascotaActual);

                            medicinasDisponibles.get(indiceMedicina).setInventario(
                                    medicinasDisponibles.get(indiceMedicina).getInventario() - 1);
                            // Regresa la medicina a la posición inicial
                            medicinaX = posicionInicialX;
                            medicinaY = posicionInicialY;
                        }
                        actualizarListaMedicamentos();
                        cambiarMedicina(0);

                    } else {
                        // Regresa la comida a la posición inicial con animación
                        animarRegresoMedicina();
                    }
                    isDragging = false;
                    repaint();
                }
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (medicinasDisponibles.isEmpty()) return;
                if (isDragging) {
                    // Mueve la comida con el mouse
                    medicinaX = e.getX() - medicinaRadio;
                    medicinaY = e.getY() - medicinaRadio;
                    repaint();
                }
            }
        });


        repaint();
    }

    public void setMascotaActual(PanelEscenario panelEscenario){
        this.mascotaActual = panelEscenario.getEscenario().getMascotaActual();
        this.posicionMascota = panelEscenario.getPosicionMascota();
    }

    public void actualizarListaMedicamentos() {
        medicinasDisponibles.clear();
        imagenesMedicina.clear();
        for(MedicinasEnum medicina : MedicinasEnum.values()) {
            if(medicina.getInventario() > 0){
                medicinasDisponibles.add(medicina);
                imagenesMedicina.add(CargadorDeImagenes.cargarImagen(medicina.getRutaImagen()));
            }

        }
        botonIzquierda.setVisible(medicinasDisponibles.size() > 1);
        botonDerecha.setVisible(medicinasDisponibles.size() > 1);
        indiceMedicina = 0; // Reinicia el índice al actualizar la lista
        repaint();

    }

    private void cambiarMedicina(int direccion) {
        if (medicinasDisponibles.isEmpty()) return;
        indiceMedicina = (indiceMedicina + direccion + medicinasDisponibles.size()) % medicinasDisponibles.size();
        medicinaX = posicionInicialX;
        medicinaY = posicionInicialY;
        repaint();
    }

    private void animarRegresoMedicina() {
        final int pasos = 10;
        final int inicioX = medicinaX;
        final int inicioY = medicinaY;
        final int deltaX = posicionInicialX - inicioX;
        final int deltaY = posicionInicialY - inicioY;

        new Thread(() -> {
            for (int i = 1; i <= pasos; i++) {
                medicinaX = inicioX + deltaX * i / pasos;
                medicinaY = inicioY + deltaY * i / pasos;
                repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException ignored) {}
            }
            medicinaX = posicionInicialX;
            medicinaY = posicionInicialY;
            repaint();
        }).start();
    }

    @Override
    public boolean contains(int x, int y) {
        int centerX = medicinaX + medicinaRadio;
        int centerY = medicinaY + medicinaRadio;
        int distanciaCuadrado = (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY);

        // Verifica si el mouse está dentro del área de la comida o sobre los botones
        return distanciaCuadrado <= medicinaRadio * medicinaRadio || x >= botonIzquierda.getX() && x <= botonDerecha.getX() + botonDerecha.getWidth()
                && y >= botonIzquierda.getY() && y <= botonIzquierda.getY() + botonIzquierda.getHeight();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.setFont(fuente);
        FontMetrics fontMetrics = g2d.getFontMetrics();

        if (medicinasDisponibles.isEmpty()) {
            g2d.drawString("MEDICINA", posicionInicialX + medicinaRadio - fontMetrics.stringWidth("MEDICINA") / 2, posicionInicialY + medicinaRadio + 6);
            return;
        }

        // Dibuja la comida
        g.drawImage(imagenesMedicina.get(indiceMedicina), medicinaX, medicinaY, medicinaSize, medicinaSize, this);


        // Dibujar texto de cantidad y nombre de la comida
        String nombreComida = medicinasDisponibles.get(indiceMedicina).getNombre();
        int textoX = posicionInicialX + medicinaRadio - fontMetrics.stringWidth(nombreComida) / 2;
        int textoY = posicionInicialY - 12; // Ajusta la posición del texto arriba de la comida
        g2d.drawString(nombreComida, textoX, textoY);

        String cantidad = "x " + medicinasDisponibles.get(indiceMedicina).getInventario();
        textoX = posicionInicialX + medicinaRadio - fontMetrics.stringWidth(cantidad) / 2;
        textoY = posicionInicialY + medicinaSize + 24; // Ajusta la posición del texto debajo de la comida
        g2d.drawString(cantidad, textoX, textoY);


    }

}
