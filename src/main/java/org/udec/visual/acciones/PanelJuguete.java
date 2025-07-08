package org.udec.visual.acciones;


import org.udec.mascotas.Mascota;
import org.udec.util.CargadorDeImagenes;
import org.udec.visual.PanelEscenario;
import org.udec.visual.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PanelJuguete extends JPanel {

    private Mascota mascotaActual;
    private BufferedImage imagenJuguete;

    private int pelotaX = VentanaPrincipal.ANCHO / 2 - 40; // Posición inicial de la pelota
    private int pelotaY = VentanaPrincipal.ALTO - 200;

    private int pelotaSize = 100;
    private int pelotaRadio = pelotaSize / 2;
    private double friction = 0.99; // Fricción para simular el movimiento
    private double umbralDetencion = 0.99; // Umbral de detención para el movimiento

    private double velocityX = 0;
    private double velocityY = 0;

    // Variables para el mouse
    private int mouseX, mouseY;
    private boolean isDragging = false;

    // Auxiliares para felicidad;
    private int contadorFelicidad = 0;
    private int contadorFelicidadMaximo = 15; // Cada cuántos movimientos se aumenta la felicidad

    public PanelJuguete(){

        imagenJuguete = CargadorDeImagenes.cargarImagen("/juguetes/pelota.png");

        this.setLayout(null);
        this.setBounds(0, 0, VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                // Verificar si se clickea en la pelota
                int centerX = pelotaX + pelotaRadio;
                int centerY = pelotaY + pelotaRadio;
                int distanciaCuadrado = (e.getX() - centerX) * (e.getX() - centerX)
                        + (e.getY() - centerY) * (e.getY() - centerY);

                if(distanciaCuadrado <= pelotaRadio * pelotaRadio) {
                    isDragging = true;
                    mouseX = e.getX();
                    mouseY = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isDragging){
                    // Calcular la velocidad de la pelota al soltarla
                    velocityX = -(e.getX() - pelotaX - pelotaRadio) / 10.0;
                    velocityY = -(e.getY() - pelotaY - pelotaRadio) / 10.0;

                    isDragging = false;
                }
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    repaint();
                }
            }
        });

        Thread animacionThread = getAnimacionThread();
        animacionThread.start();

    }

    private Thread getAnimacionThread() {
        Thread animacionThread = new Thread(() -> {
            while (true) {
                if (!isDragging) {
                    actualizarPelota();
                }
                SwingUtilities.invokeLater(this::repaint);
                try {
                    Thread.sleep(16); // Aproximadamente 60 FPS
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        animacionThread.setDaemon(true);
        return animacionThread;
    }

    public void setMascotaActual(PanelEscenario panelEscenario){
        this.mascotaActual = panelEscenario.getEscenario().getMascotaActual();
    }

    private void actualizarPelota() {
        // Actualizar la posición de la pelota usando las velocidades
        pelotaX += velocityX;
        pelotaY += velocityY;

        // Reducir las velocidades usando fricción
        velocityX *= friction;
        velocityY *= friction;

        // Verificar si ambas velocidades son menores que el umbral
        if (Math.abs(velocityX) >= umbralDetencion && Math.abs(velocityY) >= umbralDetencion) {
            contadorFelicidad++;
            // Aumentar la felicidad de la mascota si se mueve lo suficiente
            if (contadorFelicidad >= contadorFelicidadMaximo) {
                mascotaActual.getEstado().addFelicidad(1);
                contadorFelicidad = 0; // Reiniciar el contador de felicidad
            }
        } else {
            // Detener la pelota completamente al alcanzar el umbral
            velocityX = 0;
            velocityY = 0;
        }


        // Verificar colisiones con los bordes
        if (pelotaX <= 0 || pelotaX + pelotaSize >= getWidth()) {
            velocityX = -velocityX;
            pelotaX = Math.max(0, Math.min(pelotaX, getWidth() - pelotaSize));
        }
        if (pelotaY <= 0 || pelotaY + pelotaSize >= getHeight()) {
            velocityY = -velocityY;
            pelotaY = Math.max(0, Math.min(pelotaY, getHeight() - pelotaSize));
        }
    }

    @Override
    public boolean contains(int x, int y) {
        int centerX = pelotaX + pelotaRadio;
        int centerY = pelotaY + pelotaRadio;
        int distanciaCuadrado = (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY);
        return distanciaCuadrado <= pelotaRadio * pelotaRadio;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la pelota cargando imagen
        g.drawImage(imagenJuguete, pelotaX, pelotaY, pelotaSize, pelotaSize, this);

        // Dibujar la línea de dirección mientras se apunta
        if (isDragging) {
            // Calcular la potencia (distancia entre el mouse y la pelota)
            int centerX = pelotaX + pelotaRadio;
            int centerY = pelotaY + pelotaRadio;
            double potencia = calcularPotencia(centerX, centerY);

            // Cambiar el color según la potencia (verde -> amarillo -> rojo)
            Color lineColor = colorSegunPotencia(potencia);

            // Convertir Graphics a Graphics2D para configurar el ancho de la línea
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(lineColor);

            // Establecer el grosor de la línea
            g2.setStroke(new BasicStroke(4)); // Ancho de la línea (puedes cambiar el valor)

            // Dibujar la línea de dirección
            g2.drawLine(centerX, centerY, mouseX, mouseY);

        }
    }

    private double calcularPotencia(int centerX, int centerY) {
        double distance = Math.sqrt((mouseX - centerX) * (mouseX - centerX) + (mouseY - centerY) * (mouseY - centerY));

        // Normalizar la potencia entre 0 y 1 con una transición lenta
        double maximaDistancia = 450; // Máxima distancia para potencia máxima
        double potencia = Math.min(distance / maximaDistancia, 1.0); // Limitar el valor máximo a 1.0

        // Suavizar la transición de potencia utilizando una raíz cuadrada
        return Math.sqrt(potencia);
    }

    private Color colorSegunPotencia(double potencia) {
        // Interpolación de color de verde (baja potencia) a rojo (alta potencia)
        // Verde -> Amarillo -> Rojo
        int red = (int) (255 * potencia);
        int green = (int) (255 * (1 - potencia));
        return new Color(red, green, 0);
    }

    public void reiniciarPelota() {
        pelotaX = VentanaPrincipal.ANCHO / 2 - 50; // Posición inicial de la pelota
        pelotaY = VentanaPrincipal.ALTO - 120;
        velocityX = 0;
        velocityY = 0;
        isDragging = false;
    }
}
