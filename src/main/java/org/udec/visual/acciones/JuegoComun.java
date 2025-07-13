package org.udec.visual.acciones;


import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.EstadoJuego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;


/**
 * Clase que representa un juego común donde la mascota debe saltar obstáculos.
 * El objetivo es ganar puntos y evitar chocar con los obstáculos.
 */
public class JuegoComun extends JPanel implements ActionListener, KeyListener {

    private final JDialog ventanaJuego;
    private final PanelJuegos panelJuegos;
    private final int RECOMPENSA = 50; // Recompensa por ganar el juego

    private final int ANCHO = 800;
    private final int ALTO = 400;

    private final BufferedImage imagenMascota;
    private final Font fuente = new Font("Arial", Font.BOLD, 20);

    // Variables de juego
    private Timer timer;
    private int mascotaY = 250;
    private int mascotaVelY = 0;
    private boolean jumping = false;
    private final int MASCOTA_SIZE = 40; // Tamaño de la mascota

    // Obstáculo
    private final BufferedImage IMAGEN_OBSTACULO = CargadorDeImagenes.cargarImagen("/juegos/obstaculoComun.png");
    private int obstaculoX = 800; // Posición inicial del obstáculo
    private final int ANCHO_OBSTACULO = 20;
    private int altoObstaculo = 40;

    // Imagen del piso
    private final BufferedImage IMAGEN_PISO = CargadorDeImagenes.cargarImagen("/juegos/pisoComun.png");
    private int pisoOffset = 0;

    // Variables de estado
    private EstadoJuego estadoJuego = EstadoJuego.MENU;
    private int puntos = 0;

    private int velObstaculo = 8; // Velocidad del obstaculo, el valor asignado es el inicial
    private final int MAX_VEL = 20; // Velocidad máxima del obstáculo
    private final int PUNTOS_WIN = 30; // Puntos necesarios para ganar el juego


    /**
     * Constructor del juego común.
     *
     * @param imagenMascota Imagen de la mascota que se utilizará en el juego.
     * @param panelJuegos   Panel donde se muestra el juego y se manejan las acciones de victoria/derrota.
     */
    public JuegoComun(BufferedImage imagenMascota, PanelJuegos panelJuegos) {
        ventanaJuego = new JDialog();
        ventanaJuego.setContentPane(this);
        ventanaJuego.setLayout(null);
        ventanaJuego.setResizable(false);
        ventanaJuego.setTitle("Juego común: salta los obstáculos");
        ventanaJuego.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ventanaJuego.setSize(ANCHO, ALTO);
        ventanaJuego.setLocationRelativeTo(null);

        this.panelJuegos = panelJuegos;

        this.setFocusable(true);
        this.addKeyListener(this);

        // Timer para actualizar en 60 FPS
        timer = new Timer(16, this);
        timer.start();

        setVisible(true);

        // Para ahorrar recursos, se escala la imagen de la mascota desde un inicio
        this.imagenMascota = redimensionarImagenMascota(imagenMascota);

        ventanaJuego.setVisible(true);
        this.requestFocusInWindow();
    }

    private BufferedImage redimensionarImagenMascota(BufferedImage imagenMascota) {
        BufferedImage imagenMascotaEscalada = new BufferedImage(MASCOTA_SIZE, MASCOTA_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gMascota = imagenMascotaEscalada.createGraphics();
        gMascota.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        gMascota.drawImage(imagenMascota, 0, 0, MASCOTA_SIZE, MASCOTA_SIZE, null);
        gMascota.dispose();
        return imagenMascotaEscalada;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Configuración inicial de gráficos
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(fuente);
        FontMetrics fontMetrics = g2d.getFontMetrics();

        dibujarEntorno(g2d);

        switch(estadoJuego){
            case MENU -> dibujarMenu(g2d, fontMetrics);
            case JUGANDO -> {
                dibujarMascota(g2d);
                dibujarObstaculo(g2d);
            }
            case VICTORIA -> dibujarVictoria(g2d, fontMetrics);
            case DERROTA -> dibujarDerrota(g2d, fontMetrics);
        }

        // Dibujar puntos
        g2d.setColor(Color.BLACK);
        g2d.drawString("Puntos: " + puntos, 10, 20);

    }

    private void dibujarEntorno(Graphics2D g2d) {

        // Dibujar cielo
        g2d.setColor(Color.cyan);
        g2d.fillRect(0, 0, ANCHO, ALTO);

        // Dibujar piso con desplazamiento
        for (int x = -pisoOffset; x < ANCHO; x += IMAGEN_PISO.getWidth()) {
            g2d.drawImage(IMAGEN_PISO, x, 300, this);
        }
    }

    private void dibujarObstaculo(Graphics2D g2d) {
        g2d.drawImage(IMAGEN_OBSTACULO, obstaculoX, 316 - altoObstaculo, // Esquina superior izquierda destino
                obstaculoX + ANCHO_OBSTACULO, 316, // Esquina inferior derecha destino
                0, 0 // Esquina superior izquierda de la imagen original
                , ANCHO_OBSTACULO, altoObstaculo, this); // Parte cortada de acuerdo al tamaño del obstáculo
    }

    private void dibujarMenu(Graphics2D g2d, FontMetrics fontMetrics) {
        g2d.setColor(Color.BLACK);
        String msg1 = "Si juntas " + PUNTOS_WIN + " puntos, ganas el juego";
        String msg2 = "Presiona 'Espacio' para iniciar";
        String msg3 = "Presiona 'Esc' para salir";
        g2d.drawString(msg1, (ANCHO - fontMetrics.stringWidth(msg1)) / 2, 100);
        g2d.drawString(msg2, (ANCHO - fontMetrics.stringWidth(msg2)) / 2, 150);
        g2d.drawString(msg3, (ANCHO - fontMetrics.stringWidth(msg3)) / 2, 200);
    }

    private void dibujarVictoria(Graphics2D g2d, FontMetrics fontMetrics) {
        g2d.setColor(Color.BLACK);
        String msg1 = "¡Felicidades! Has ganado el juego.";
        String msg2 = "Has ganado $" + RECOMPENSA;
        String msg3 = "Presiona 'Esc' para salir";
        g2d.drawString(msg1, (ANCHO - fontMetrics.stringWidth(msg1)) / 2, 100);
        g2d.drawString(msg2, (ANCHO - fontMetrics.stringWidth(msg2)) / 2, 150);
        g2d.drawString(msg3, (ANCHO - fontMetrics.stringWidth(msg3)) / 2, 200);
    }

    private void dibujarDerrota(Graphics2D g2d, FontMetrics fontMetrics) {
        g2d.setColor(Color.RED);
        String msg1 = "¡Juego terminado! Has perdido.";
        String msg2 = "Tu mascota ha perdido un poco de salud";
        String msg3 = "Presiona 'Esc' para salir";
        g2d.drawString(msg1, (ANCHO - fontMetrics.stringWidth(msg1)) / 2, 100);
        g2d.drawString(msg2, (ANCHO - fontMetrics.stringWidth(msg2)) / 2, 150);
        g2d.drawString(msg3, (ANCHO - fontMetrics.stringWidth(msg3)) / 2, 200);
    }

    private void dibujarMascota(Graphics2D g2d){

        // Calcular el ángulo basado en la velocidad de la mascota
        // Se inclinará hacia arriba si está saltando, y hacia abajo si está cayendo
        // con un límite de -30 a 30 grados
        double angulo = 0;
        if (mascotaY < 260) {
            angulo = Math.max(-30, Math.min(30, Math.toDegrees(Math.atan2(mascotaVelY, 14))));
        }

        int x = 100; // Posición X de la mascota
        AffineTransform originalTransform = g2d.getTransform();
        g2d.rotate(Math.toRadians(angulo), x + MASCOTA_SIZE / 2, mascotaY + MASCOTA_SIZE / 2);
        g2d.drawImage(imagenMascota, x, mascotaY, MASCOTA_SIZE, MASCOTA_SIZE, this);
        g2d.setTransform(originalTransform); // Restaurar la transformación original

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (estadoJuego != EstadoJuego.JUGANDO) return; // No actualizar el juego si no estamos jugando
        movimientoObstaculo();
        movimientoMascota();
        verificarColision();
        repaint();

    }

    private void movimientoMascota() {
        mascotaY += mascotaVelY;

        if(mascotaY >= 270){
            mascotaY = 270; // Asegurarse de que la mascota no caiga por debajo del suelo
            mascotaVelY = 0; // Detener la caída
            jumping = false;
        } else {
            mascotaVelY += 1; // Gravedad
        }

        // Movimiento del piso para simular mascota moviendose horizontalmente
        pisoOffset += velObstaculo;
        if(pisoOffset >= IMAGEN_PISO.getWidth()) {
            pisoOffset = 0; // Reiniciar el offset del piso
        }

    }

    private void movimientoObstaculo() {
        obstaculoX -= velObstaculo;
        if(obstaculoX + ANCHO_OBSTACULO < 0){
            // Reiniciar obstáculo
            obstaculoX = ANCHO;
            altoObstaculo = 40 + (int)(Math.random() * 50); // Altura aleatoria entre 40 y 90

            puntos++;

            if(puntos == PUNTOS_WIN){
                estadoJuego = EstadoJuego.VICTORIA;
                timer.stop();
                panelJuegos.victoriaJuego(RECOMPENSA);
            }

            // Aumentar velocidad gradualmente
            if(puntos % 5 == 0 && velObstaculo < MAX_VEL) {
                velObstaculo++;
            }
        }
    }

    private void verificarColision() {
        if(new Rectangle(100, mascotaY, 30, 30).intersects(new Rectangle(obstaculoX, 310 - altoObstaculo, ANCHO_OBSTACULO, altoObstaculo))) {
            // Colisión detectada
            estadoJuego = EstadoJuego.DERROTA;
            timer.stop();
            panelJuegos.derrotaJuego();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && estadoJuego == EstadoJuego.MENU) {
            estadoJuego = EstadoJuego.JUGANDO; // Salir del menú principal
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE && estadoJuego != EstadoJuego.JUGANDO) {
            ventanaJuego.dispose(); // Cerrar el juego
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && estadoJuego == EstadoJuego.JUGANDO && !jumping) {
            mascotaVelY = -15; // Velocidad de salto
            jumping = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
