package org.udec.visual.acciones;


import org.udec.util.CargadorDeImagenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;


public class JuegoComun extends JPanel implements ActionListener, KeyListener {

    private JDialog ventanaJuego;
    private PanelJuegos panelJuegos;

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

    // Imagen del fondo
    private final BufferedImage IMAGEN_FONDO = CargadorDeImagenes.cargarImagen("/juegos/fondoComun.png");

    // Variables de estado
    private boolean mainMenu = true; // Indica si estamos en el menú principal
    private boolean gameOver = false;
    private boolean gameWon = false;
    private int puntos = 0;

    private int velObstaculo = 8; // Velocidad inicial de obstáculo
    private final int MAX_VEL = 20; // Velocidad máxima del obstáculo
    private final int PUNTOS_WIN = 30; // Puntos necesarios para ganar el juego


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

        // Cargar la imagen de la mascota
        this.imagenMascota = imagenMascota;

        ventanaJuego.setVisible(true);
        this.requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar fondo
        g2d.drawImage(IMAGEN_FONDO, 0, 0, ANCHO, ALTO - 100, this);

        // Dibujar piso con desplazamiento
       for(int x = -pisoOffset; x < ANCHO; x += IMAGEN_PISO.getWidth()) {
            g.drawImage(IMAGEN_PISO, x, 300, this);
        }

        g2d.setColor(Color.BLACK);
        g2d.setFont(fuente);

        // Dibujar mensajes del menú principal o del juego
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int panelWidth = getWidth();

        if(mainMenu){
            String msg1 = "Si juntas 30 puntos, ganas el juego";
            String msg2 = "Presiona 'Espacio' para iniciar";
            String msg3 = "Presiona 'Esc' para salir";
            g2d.drawString(msg1, (panelWidth - fontMetrics.stringWidth(msg1)) / 2, 100);
            g2d.drawString(msg2, (panelWidth - fontMetrics.stringWidth(msg2)) / 2, 150);
            g2d.drawString(msg3, (panelWidth - fontMetrics.stringWidth(msg3)) / 2, 200);
            return;
        } else if (!gameOver && !gameWon) {
            dibujarMascota(g2d);
        } else if (gameWon){
            String msg1 = "¡Felicidades! Has ganado el juego.";
            String msg2 = "Has ganado $ xxx";
            String msg3 = "Presiona 'Esc' para salir";
            g2d.drawString(msg1, (panelWidth - fontMetrics.stringWidth(msg1)) / 2, 100);
            g2d.drawString(msg2, (panelWidth - fontMetrics.stringWidth(msg2)) / 2, 150);
            g2d.drawString(msg3, (panelWidth - fontMetrics.stringWidth(msg3)) / 2, 200);
            return;
        } else {
            g2d.setColor(Color.RED);
            String msg1 = "¡Juego terminado! Has perdido.";
            String msg2 = "Tu mascota ha perdido un poco de salud";
            String msg3 = "Presiona 'Esc' para salir";
            g2d.drawString(msg1, (panelWidth - fontMetrics.stringWidth(msg1)) / 2, 100);
            g2d.drawString(msg2, (panelWidth - fontMetrics.stringWidth(msg2)) / 2, 150);
            g2d.drawString(msg3, (panelWidth - fontMetrics.stringWidth(msg3)) / 2, 200);
            return;
        }

        // Dibujar obstáculo
        g2d.drawImage(IMAGEN_OBSTACULO, obstaculoX, 316 - altoObstaculo, // Esquina superior izquierda destino
                obstaculoX + ANCHO_OBSTACULO, 316, // Esquina inferior derecha destino
                0, 0 // Esquina superior izquierda de la imagen original
                ,ANCHO_OBSTACULO, altoObstaculo, this); // Parte cortada de acuerdo al tamaño del obstáculo

        // Dibujar puntos
        g2d.drawString("Puntos: " + puntos, 10, 20);
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
        if(mainMenu || gameOver || gameWon) {
            return; // No actualizar si estamos en el menú o el juego ha terminado
        }

        // ====== Movimiento de obstáculo ======
        obstaculoX -= velObstaculo;
        if(obstaculoX + ANCHO_OBSTACULO < 0){
            // Reiniciar obstáculo
            obstaculoX = ANCHO;
            altoObstaculo = 40 + (int)(Math.random() * 50); // Altura aleatoria entre 40 y 90

            puntos++;

            if(puntos == PUNTOS_WIN){
                gameWon = true;
                timer.stop();
                panelJuegos.victoriaJuego(35); // Gana $35
            }

            // Aumentar velocidad gradualmente
            if(puntos % 5 == 0 && velObstaculo < MAX_VEL) {
                velObstaculo++;
            }
        }

        // ====== Movimiento de la mascota ======

        mascotaY += mascotaVelY;

        if(mascotaY >= 270){
            mascotaY = 270; // Asegurarse de que la mascota no caiga por debajo del suelo
            mascotaVelY = 0; // Detener la caída
            jumping = false;
        } else {
            mascotaVelY += 1; // Gravedad
        }

        // Movimiento del piso
        pisoOffset += velObstaculo;
        if(pisoOffset >= IMAGEN_PISO.getWidth()) {
            pisoOffset = 0; // Reiniciar el offset del piso
        }

        // ====== Colisión con obstáculo ======

        if(new Rectangle(100, mascotaY, 30, 30).intersects(new Rectangle(obstaculoX, 310 - altoObstaculo, ANCHO_OBSTACULO, altoObstaculo))) {
            // Colisión detectada
            gameOver = true;
            timer.stop();
            panelJuegos.derrotaJuego();
        }

        repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && mainMenu) {
            mainMenu = false; // Salir del menú principal
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE && (gameOver || gameWon || mainMenu)) {
            ventanaJuego.dispose(); // Cerrar el juego
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !mainMenu && !gameOver && !gameWon && !jumping) {
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
