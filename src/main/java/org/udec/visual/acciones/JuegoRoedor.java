package org.udec.visual.acciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class JuegoRoedor extends JPanel implements ActionListener, KeyListener {

    private JDialog ventanaJuego;
    private PanelJuegos panelJuegos;

    private final BufferedImage imagenMascota;
    private final Font fuente = new Font("Arial", Font.BOLD, 20);

    // Variables de juego
    private Timer timer, limiteTiempo;
    private int tiempoRestante = 40;

    private final int TILE_SIZE = 40; // Tamaño de cada celda del laberinto
    private final int FILAS = 20, COLS = 30; // Dimensiones del laberinto
    private int[][] laberinto = new int[FILAS][COLS]; // 0 = camino, 1 = pared, 2 = meta
    private int mascotaFila = 1, mascotaCol = 1; // Posición inicial del jugador
    private int direccion = 0; // 0: arriba, 1: derecha, 2: abajo, 3: izquierda
    private int radioVision = 3;

    // Variables de estado
    private boolean mainMenu = true; // Indica si estamos en el menú principal
    private boolean gameOver = false;
    private boolean gameWon = false;


    public JuegoRoedor(BufferedImage imagenMascota, PanelJuegos panelJuegos) {
        ventanaJuego = new JDialog();
        ventanaJuego.setContentPane(this);
        ventanaJuego.setLayout(null);
        ventanaJuego.setResizable(false);
        ventanaJuego.setTitle("Juego Roedor");
        ventanaJuego.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ventanaJuego.setSize(TILE_SIZE * COLS, TILE_SIZE * FILAS + 40); // +40 para espacio de puntaje y tiempo
        ventanaJuego.setLocationRelativeTo(null);

        this.panelJuegos = panelJuegos;
        this.imagenMascota = imagenMascota;

        generarLaberinto(); // Generar el laberinto al iniciar el juego

        this.setFocusable(true);
        this.addKeyListener(this);

        timer = new Timer(16, this); // 60 FPS
        timer.start();

        limiteTiempo = new Timer(1000, _ -> tiempoRestante--);

        ventanaJuego.setVisible(true);
        this.requestFocusInWindow();
    }

    // Para la implementación, se usará dfs para generar los caminos.
    // Esto asegura que el laberinto sea un árbol y no tenga ciclos.
    // Además, se asegura que haya un camino desde la entrada hasta la salida.
    // (Gracias matemáticas discretas por hacerme entender esto).
    private void generarLaberinto(){

        // Inicializamos el laberinto lleno de paredes
        for (int r = 0; r < FILAS; r++) {
            for (int c = 0; c < COLS; c++) {
                laberinto[r][c] = 1;
            }
        }

        dfsLaberinto(1, 1); // Comenzar desde la celda (1, 1);

        // Asegurar que exista entrada y salida
        laberinto[1][1] = 0; // Entrada
        laberinto[FILAS - 3][COLS - 3] = 2; // Salida / meta

    }

    private void dfsLaberinto(int fila, int columna){
        laberinto[fila][columna] = 0; // Marcar como camino

        // Definir las direcciones posibles (arriba, derecha, abajo, izquierda), se usa 2 para evitar caminos adyacentes
        int[][] direcciones = {
            {-2, 0}, // Arriba
            {0, 2},  // Derecha
            {2, 0},  // Abajo
            {0, -2}  // Izquierda
        };

        // Se mezclan las direcciones para que sea más random usando algoritmo de Fisher-Yates
        for (int i = direcciones.length -1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1)); // Genera un índice aleatorio entre 0 e i
            int[] temp = direcciones[i];

            // Intercambiar direcciones i y j
            direcciones[i] = direcciones[j];
            direcciones[j] = temp;
        }

        for(int[] dir : direcciones) {
            int nuevaFila = fila + dir[0];
            int nuevaColumna = columna + dir[1];

            // Verificar límites y si la celda es una pared
            if (nuevaFila > 0 && nuevaFila < FILAS - 1 && nuevaColumna > 0 && nuevaColumna < COLS - 1 && laberinto[nuevaFila][nuevaColumna] == 1) {
                // Eliminar la pared entre la celda actual y la nueva
                laberinto[fila + dir[0] / 2][columna + dir[1] / 2] = 0;
                dfsLaberinto(nuevaFila, nuevaColumna); // Recursión
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el laberinto
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLS; c++) {
                if(laberinto[f][c] == 2) { // Siempre mostrar la meta
                    g.setColor(Color.yellow);
                    g.fillRect(c * TILE_SIZE, f * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                } else{
                    int dist = Math.abs(f - mascotaFila) + Math.abs(c - mascotaCol);
                    if(dist <= radioVision) { // Solo se muestran celdas dentro del radio de visión
                        if (laberinto[f][c] == 1) {
                            g.setColor(Color.DARK_GRAY);
                            g.fillRect(c * TILE_SIZE, f * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                        } else if (laberinto[f][c] == 0) {
                            g.setColor(Color.WHITE); // Camino
                            g.fillRect(c * TILE_SIZE, f * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                        }
                    } else {
                        g.setColor(Color.BLACK); // Celdas fuera del radio de visión
                        g.fillRect(c * TILE_SIZE, f * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    }
                }
            }
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(fuente);
        g2d.setColor(Color.WHITE);

        // Dibujar mascota con rotación
        dibujarMascota(g2d);

        // Dibujar mensajes del menú principal o del juego
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int panelWidth = getWidth();

        if(mainMenu){
            String msg1 = "Si llegas a la meta en menos de 40 segundos, ganas el juego";
            String msg2 = "Te mueves con 'W', 'A', 'S', 'D'";
            String msg3 = "Presiona 'Espacio' para iniciar";
            String msg4 = "Presiona 'Esc' para salir";
            g2d.drawString(msg1, (panelWidth - fontMetrics.stringWidth(msg1)) / 2, 100);
            g2d.drawString(msg2, (panelWidth - fontMetrics.stringWidth(msg2)) / 2, 150);
            g2d.drawString(msg3, (panelWidth - fontMetrics.stringWidth(msg3)) / 2, 200);
            g2d.drawString(msg4, (panelWidth - fontMetrics.stringWidth(msg4)) / 2, 250);
            return;
        } else if(gameWon){
            String msg1 = "¡Felicidades! Has ganado el juego.";
            String msg2 = "Has ganado $ xxx";
            String msg3 = "Presiona 'Esc' para salir";
            g2d.drawString(msg1, (panelWidth - fontMetrics.stringWidth(msg1)) / 2, 100);
            g2d.drawString(msg2, (panelWidth - fontMetrics.stringWidth(msg2)) / 2, 150);
            g2d.drawString(msg3, (panelWidth - fontMetrics.stringWidth(msg3)) / 2, 200);
            return;
        } else if(gameOver) {
            g2d.setColor(Color.RED);
            String msg1 = "¡Te has quedado sin tiempo! Has perdido.";
            String msg2 = "Tu mascota ha perdido un poco de salud";
            String msg3 = "Presiona 'Esc' para salir";
            g2d.drawString(msg1, (panelWidth - fontMetrics.stringWidth(msg1)) / 2, 100);
            g2d.drawString(msg2, (panelWidth - fontMetrics.stringWidth(msg2)) / 2, 150);
            g2d.drawString(msg3, (panelWidth - fontMetrics.stringWidth(msg3)) / 2, 200);
            return;
        }

        // Dibujar tiempo restante
        g2d.drawString("Tiempo: " + tiempoRestante, 10, 20);

    }

    private void dibujarMascota(Graphics2D g2d) {
        int cx = mascotaCol * TILE_SIZE + TILE_SIZE / 2;
        int cy = mascotaFila * TILE_SIZE + TILE_SIZE / 2;
        double angulo = Math.toRadians(direccion * 90);

        AffineTransform originalTransform = g2d.getTransform(); // Guardar transformaciones previas
        g2d.rotate(angulo, cx, cy); // Para rotar alrededor del centro de la mascota
        g2d.drawImage(imagenMascota, mascotaCol * TILE_SIZE + 5, mascotaFila * TILE_SIZE + 5, TILE_SIZE - 10, TILE_SIZE - 10, this);
        g2d.setTransform(originalTransform);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(mainMenu) {
            return; // No actualizar el juego si estamos en el menú
        }

        if(gameWon) {
           timer.stop();
           limiteTiempo.stop();
           panelJuegos.victoriaJuego(50);
        }

        if(tiempoRestante <= 0) {
            gameOver = true; // Si el tiempo se agota, el juego termina
            limiteTiempo.stop();
            timer.stop();
            panelJuegos.derrotaJuego();
        }

        repaint(); // Redibujar el panel

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && mainMenu) {
            mainMenu = false; // Salir del menú principal
            limiteTiempo.start();
            return;
        }
        if(gameOver || gameWon || mainMenu) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                ventanaJuego.dispose(); // Cerrar el juego
            }
        } else{
            int nuevaFila = mascotaFila, nuevaColumna = mascotaCol;
            if (e.getKeyCode() == KeyEvent.VK_W) { nuevaFila--; direccion = 2; }
            if (e.getKeyCode() == KeyEvent.VK_S) { nuevaFila++; direccion = 0; }
            if (e.getKeyCode() == KeyEvent.VK_A) { nuevaColumna--; direccion = 1; }
            if (e.getKeyCode() == KeyEvent.VK_D) { nuevaColumna++; direccion = 3; }

            // ver si está chocando con paredes, con meta o límites.
            detectarColisiones(nuevaFila, nuevaColumna);
        }

    }

    private void detectarColisiones(int nuevaFila, int nuevaColumna) {
        if (nuevaFila >= 0 && nuevaFila < FILAS && nuevaColumna >= 0 && nuevaColumna < COLS) {
            // Verificar si es un camino o la meta
            if (laberinto[nuevaFila][nuevaColumna] == 0 || laberinto[nuevaFila][nuevaColumna] == 2) {
                mascotaFila = nuevaFila;
                mascotaCol = nuevaColumna;

                // Verificar si llegó a la meta
                if (laberinto[mascotaFila][mascotaCol] == 2) {
                    gameWon = true;
                }
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
