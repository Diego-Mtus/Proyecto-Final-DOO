package org.udec.visual.acciones;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;


public class JuegoComun extends JPanel implements ActionListener, KeyListener {

    private JDialog ventanaJuego;
    private PanelJuegos panelJuegos;

    private final BufferedImage imagenMascota;
    private final Font fuente = new Font("Arial", Font.BOLD, 20);

    // Variables de juego
    private Timer timer;
    private int mascotaY = 250;
    private int mascotaVelY = 0;
    private boolean jumping = false;

    // Obstáculo
    private int obstaculoX = 800; // Posición inicial del obstáculo
    private int anchoObstaculo = 20;
    private int altoObstaculo = 40;

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
        ventanaJuego.setTitle("Juego común");
        ventanaJuego.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ventanaJuego.setSize(800, 400);
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

        // Dibujar fondo
        g.setColor(Color.cyan);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar suelo
        g.setColor(Color.green);
        g.fillRect(0, 300, getWidth(), 100);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
            g.drawImage(imagenMascota, 100, mascotaY, 30, 30, this);
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
        g.setColor(Color.BLACK);
        g.fillRect(obstaculoX, 300 - altoObstaculo, anchoObstaculo, altoObstaculo);

        // Dibujar puntos
        g2d.drawString("Puntos: " + puntos, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(mainMenu || gameOver || gameWon) {
            return; // No actualizar si estamos en el menú o el juego ha terminado
        }

        // ====== Movimiento de obstáculo ======
        obstaculoX -= velObstaculo;
        if(obstaculoX + anchoObstaculo < 0){
            // Reiniciar obstáculo
            obstaculoX = getWidth();
            altoObstaculo = 30 + (int)(Math.random() * 50); // Altura aleatoria entre 30 y 80

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

        // ====== Colisión con obstáculo ======

        if(new Rectangle(100, mascotaY, 30, 30).intersects(new Rectangle(obstaculoX, 300 - altoObstaculo, anchoObstaculo, altoObstaculo))) {
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
