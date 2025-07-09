package org.udec.visual.acciones;

import org.udec.util.enumerations.EstadoJuego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class JuegoVolador extends JPanel implements ActionListener, KeyListener {

    private JDialog ventanaJuego;
    private PanelJuegos panelJuegos;
    private final BufferedImage imagenMascota;
    private final Font fuente = new Font("Arial", Font.BOLD, 20);

    private final int ANCHO = 800;
    private final int ALTO = 600;

    // Variables de juego
    private Timer timer;
    private int mascotaY = 300;
    private int mascotaVelY = 0;
    private final int MASCOTA_SIZE = 40;

    // Tuberías
    private final ArrayList<Integer> tuberiasX = new ArrayList<>();
    private final ArrayList<Integer> tuberiasAltura = new ArrayList<>();
    private final int TUBERIA_ANCHO = 60;
    private final int TUBERIA_ESPACIO = 180; // Espacio entre tuberías verticalmente
    private final int TUBERIA_SEPARACION = 300; // Separación horizontal entre tuberías

    // Variables de estado
    private EstadoJuego estadoJuego = EstadoJuego.MENU;
    private int puntos = 0;
    private final int PUNTOS_WIN = 10; // Puntos necesarios para ganar
    private final int GRAVEDAD = 1; // Gravedad que afecta a la mascota
    private final int FUERZA_SALTO = 12;
    private final int VELOCIDAD_TUBERIA = 5;



    public JuegoVolador(BufferedImage imagenMascota, PanelJuegos panelJuegos){
        ventanaJuego = new JDialog();
        ventanaJuego.setContentPane(this);
        ventanaJuego.setLayout(null);
        ventanaJuego.setResizable(false);
        ventanaJuego.setTitle("Juego volador: Evita las tuberías");
        ventanaJuego.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ventanaJuego.setSize(ANCHO, ALTO);
        ventanaJuego.setLocationRelativeTo(null);

        this.addKeyListener(this);
        this.setFocusable(true);

        timer = new Timer(20, this);
        timer.start();

        this.panelJuegos = panelJuegos;
        this.imagenMascota = imagenMascota;

        // Inicializar tuberías
        for(int i = 0; i < 3; i++){
            tuberiasX.add(800 + i * TUBERIA_SEPARACION);
            tuberiasAltura.add(50 + (int) (Math.random() * (500 - TUBERIA_ESPACIO)));
        }

        ventanaJuego.setVisible(true);
        this.requestFocusInWindow();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.setFont(fuente);
        FontMetrics fontMetrics = g2d.getFontMetrics();


        // Dibujar fondo
        g2d.setColor(Color.CYAN);
        g2d.fillRect(0, 0, ANCHO, ALTO);

        dibujarTuberias(g2d);
        dibujarMascota(g2d);

        switch (estadoJuego){
            case MENU -> dibujarMenu(g2d, fontMetrics);
            case VICTORIA -> dibujarVictoria(g2d, fontMetrics);
            case DERROTA -> dibujarDerrota(g2d, fontMetrics);
        }

        // Dibujar puntos
        g2d.setColor(Color.BLACK);
        g2d.drawString("Puntos: " + puntos, 10, 20);

    }

    private void dibujarMenu(Graphics2D g2d, FontMetrics fontMetrics) {
        g2d.setColor(Color.BLACK);
        String msg1 = "Si juntas 10 puntos, ganas el juego";
        String msg2 = "Presiona 'Espacio' para iniciar";
        String msg3 = "Presiona 'Esc' para salir";
        g2d.drawString(msg1, (ANCHO - fontMetrics.stringWidth(msg1)) / 2, 100);
        g2d.drawString(msg2, (ANCHO - fontMetrics.stringWidth(msg2)) / 2, 150);
        g2d.drawString(msg3, (ANCHO - fontMetrics.stringWidth(msg3)) / 2, 200);
    }

    private void dibujarVictoria(Graphics2D g2d, FontMetrics fontMetrics) {
        g2d.setColor(Color.BLACK);
        String msg1 = "¡Felicidades! Has ganado el juego.";
        String msg2 = "Has ganado $ xxx";
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

    private void dibujarTuberias(Graphics2D g2d) {
        // Dibujar tuberías
        g2d.setColor(Color.GREEN);
        for(int i = 0; i < tuberiasX.size(); i++){
            // Tubería superior
            g2d.fillRect(tuberiasX.get(i), 0, TUBERIA_ANCHO, tuberiasAltura.get(i));
            // Tubería inferior
            g2d.fillRect(tuberiasX.get(i), tuberiasAltura.get(i) + TUBERIA_ESPACIO, TUBERIA_ANCHO, 800 - tuberiasAltura.get(i) - TUBERIA_ESPACIO);
        }
    }

    private void dibujarMascota(Graphics2D g2d){
        // Calcular el ángulo basado en la velocidad de la mascota
        // entre -45° subiendo a 90° bajando
        double angulo = Math.toRadians(Math.max(-45, Math.min(90, mascotaVelY * 3)));

        // Dibujar con rotación
        int mascotaX = 200;
        AffineTransform originalTransform = g2d.getTransform();
        g2d.rotate(angulo, mascotaX + MASCOTA_SIZE / 2, mascotaY + MASCOTA_SIZE / 2); // Para rotar alrededor del centro

        g2d.drawImage(imagenMascota, mascotaX, mascotaY, MASCOTA_SIZE, MASCOTA_SIZE, null);
        g2d.setTransform(originalTransform); // Restaurar la transformación original
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(estadoJuego != EstadoJuego.JUGANDO) return; // No actualizar si no estamos en el estado de juego

        // Movimiento de la mascota
        mascotaY += mascotaVelY;
        mascotaVelY += GRAVEDAD; // Aplicar gravedad

        // Movimiento de tuberías
        moverTuberias();

        if(detectarColision()){
            estadoJuego = EstadoJuego.DERROTA;
            timer.stop();
            panelJuegos.derrotaJuego();
        }

        if(puntos >= PUNTOS_WIN){
            estadoJuego = EstadoJuego.VICTORIA;
            timer.stop();
            panelJuegos.victoriaJuego(60); // Gana $60
        }

        repaint();

    }

    private void moverTuberias() {
        for(int i = 0; i < tuberiasX.size(); i++){
            tuberiasX.set(i, tuberiasX.get(i) - VELOCIDAD_TUBERIA); // Mover tuberías hacia la izquierda
            if(tuberiasX.get(i) + TUBERIA_ANCHO < 0){
                int indiceTuberiaPrevia = (i == 0) ? tuberiasX.size() - 1 : i - 1; // Para obtener el indice de la tuberia previa de forma circular
                tuberiasX.set(i, tuberiasX.get(indiceTuberiaPrevia) + TUBERIA_SEPARACION);
                tuberiasAltura.set(i, 50 + (int) (Math.random() * (500 - TUBERIA_ESPACIO))); // Nueva altura aleatoria
                puntos++; // Incrementar puntos al pasar una tubería
            }
        }
    }

    private boolean detectarColision(){

        if (mascotaY < 0 || mascotaY + MASCOTA_SIZE > getHeight()) {
            return true; // Colisión con el techo o el suelo
        }

        for(int i = 0; i < tuberiasX.size(); i++){
            if(mascotaY < tuberiasAltura.get(i) && 200 + MASCOTA_SIZE > tuberiasX.get(i) && 200 < tuberiasX.get(i) + TUBERIA_ANCHO) {
                return true; // Colisión con la tubería superior
            }
            if(mascotaY + MASCOTA_SIZE > tuberiasAltura.get(i) + TUBERIA_ESPACIO && 200 + MASCOTA_SIZE > tuberiasX.get(i) && 200 < tuberiasX.get(i) + TUBERIA_ANCHO) {
                return true; // Colisión con la tubería inferior
            }
        }
        return false; // No hay colisión
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && estadoJuego == EstadoJuego.MENU) {
            estadoJuego = EstadoJuego.JUGANDO; // Cambiar al estado de juego
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE && estadoJuego != EstadoJuego.JUGANDO) {
            ventanaJuego.dispose(); // Cerrar el juego
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && estadoJuego == EstadoJuego.JUGANDO) {
            mascotaVelY = -FUERZA_SALTO; // Hacer saltar a la mascota
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
