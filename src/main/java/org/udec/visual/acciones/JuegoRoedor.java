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

public class JuegoRoedor extends JPanel implements ActionListener, KeyListener {

    private final JDialog ventanaJuego;
    private final PanelJuegos panelJuegos;
    private final int RECOMPENSA = 40; // Recompensa por ganar el juego

    private final BufferedImage imagenMascota;
    private final int anchoDibujadoMascota;
    private final int altoDibujadoMascota;

    private final Font fuente = new Font("Arial", Font.BOLD, 20);

    // Variables de juego
    private final Timer timer;
    private final Timer limiteTiempo;
    private int tiempoRestante = 40;

    private final int TILE_SIZE = 40; // Tamaño de cada celda del laberinto
    private final int FILAS = 20, COLS = 30; // Dimensiones del laberinto
    private final int RADIO_VISION = 3;
    private final int ANCHO = COLS * TILE_SIZE;
    private final int ALTO = FILAS * TILE_SIZE;

    private final int[][] laberinto = new int[FILAS][COLS]; // 0 = camino, 1 = pared, 2 = meta
    private int mascotaFila = 1, mascotaCol = 1; // Posición inicial del jugador
    private int direccion = 0; // Para multiplicarlo por 90 y obtener la rotación.

    // Carga de imagenes
    private final BufferedImage META = CargadorDeImagenes.cargarImagen("/juegos/metaRoedor.png");
    private final BufferedImage PISO = CargadorDeImagenes.cargarImagen("/juegos/pisoRoedor.png");
    private final BufferedImage PARED = CargadorDeImagenes.cargarImagen("/juegos/paredRoedor.png");

    // Variables de estado
    private EstadoJuego estadoJuego = EstadoJuego.MENU;

    public JuegoRoedor(BufferedImage imagenMascota, PanelJuegos panelJuegos) {
        ventanaJuego = new JDialog();
        ventanaJuego.setContentPane(this);
        ventanaJuego.setLayout(null);
        ventanaJuego.setResizable(false);
        ventanaJuego.setTitle("Juego Roedor");
        ventanaJuego.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ventanaJuego.setSize(ANCHO, ALTO);
        ventanaJuego.setLocationRelativeTo(null);

        this.panelJuegos = panelJuegos;

        int imagenMascotaWidth = imagenMascota.getWidth();
        int imagenMascotaHeight = imagenMascota.getHeight();
        double escalaMascota = Math.min((double) (TILE_SIZE - 4) / imagenMascotaWidth, (double) (TILE_SIZE - 4)/ imagenMascotaHeight);
        
        anchoDibujadoMascota = (int)(imagenMascotaWidth * escalaMascota);
        altoDibujadoMascota = (int)(imagenMascotaHeight * escalaMascota);

        // Para ahorrar recursos, se escala la imagen de la mascota desde un inicio
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

    private BufferedImage redimensionarImagenMascota(BufferedImage imagenMascota){
        BufferedImage imagenMascotaEscalada = new BufferedImage(anchoDibujadoMascota, altoDibujadoMascota, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gMascota = imagenMascotaEscalada.createGraphics();
        gMascota.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        gMascota.drawImage(imagenMascota, 0, 0, anchoDibujadoMascota, altoDibujadoMascota, null);
        gMascota.dispose();
        return imagenMascotaEscalada;
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

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(fuente);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        
        // Dibujar el laberinto
        dibujarLaberinto(g2d);

        // Dibujar mascota con rotación
        dibujarMascota(g2d);

        switch(estadoJuego){
            case MENU -> dibujarMenu(g2d, fontMetrics);
            case VICTORIA -> dibujarVictoria(g2d, fontMetrics);
            case DERROTA -> dibujarDerrota(g2d, fontMetrics);
        }

        // Dibujar tiempo restante
        g2d.setColor(Color.WHITE);
        g2d.drawString("Tiempo: " + tiempoRestante, 10, 20);

    }

    private void dibujarMenu(Graphics2D g2d, FontMetrics fontMetrics) {
        g2d.setColor(Color.WHITE);
        String msg1 = "Si llegas a la meta en menos de " + tiempoRestante + " segundos, ganas el juego";
        String msg2 = "Te mueves con 'W', 'A', 'S', 'D'";
        String msg3 = "Presiona 'Espacio' para iniciar";
        String msg4 = "Presiona 'Esc' para salir";
        g2d.drawString(msg1, (ANCHO - fontMetrics.stringWidth(msg1)) / 2, 100);
        g2d.drawString(msg2, (ANCHO - fontMetrics.stringWidth(msg2)) / 2, 150);
        g2d.drawString(msg3, (ANCHO - fontMetrics.stringWidth(msg3)) / 2, 200);
        g2d.drawString(msg4, (ANCHO - fontMetrics.stringWidth(msg4)) / 2, 250);
    }

    private void dibujarVictoria(Graphics2D g2d, FontMetrics fontMetrics) {
        g2d.setColor(Color.WHITE);
        String msg1 = "¡Felicidades! Has ganado el juego.";
        String msg2 = "Has ganado $" + RECOMPENSA;
        String msg3 = "Presiona 'Esc' para salir";
        g2d.drawString(msg1, (ANCHO - fontMetrics.stringWidth(msg1)) / 2, 100);
        g2d.drawString(msg2, (ANCHO - fontMetrics.stringWidth(msg2)) / 2, 150);
        g2d.drawString(msg3, (ANCHO - fontMetrics.stringWidth(msg3)) / 2, 200);
    }

    private void dibujarDerrota(Graphics2D g2d, FontMetrics fontMetrics) {
        g2d.setColor(Color.RED);
        String msg1 = "¡Te has quedado sin tiempo! Has perdido.";
        String msg2 = "Tu mascota ha perdido un poco de salud";
        String msg3 = "Presiona 'Esc' para salir";
        g2d.drawString(msg1, (ANCHO - fontMetrics.stringWidth(msg1)) / 2, 100);
        g2d.drawString(msg2, (ANCHO - fontMetrics.stringWidth(msg2)) / 2, 150);
        g2d.drawString(msg3, (ANCHO - fontMetrics.stringWidth(msg3)) / 2, 200);
    }

    private void dibujarLaberinto(Graphics2D g2d) {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLS; c++) {

                int dist = Math.abs(f - mascotaFila) + Math.abs(c - mascotaCol);
                    
                if(dist <= RADIO_VISION) { // Solo se muestran celdas dentro del radio de visión
                        
                    if (laberinto[f][c] == 1) dibujarPared(g2d, c, f);
                    else if (laberinto[f][c] == 0 || laberinto[f][c] == 2) dibujarCamino(g2d, c, f);

                } else dibujadoFueraDeVision(g2d, c, f);

                if (laberinto[f][c] == 2) dibujarMeta(g2d, c, f);

            }
        }
    }

    private void dibujarMeta(Graphics2D g2d, int c, int f) {
        g2d.drawImage(META, c * TILE_SIZE, f * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
    }

    private void dibujarPared(Graphics2D g2d, int c, int f) {
        g2d.drawImage(PARED, c * TILE_SIZE, f * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
    }

    private void dibujarCamino(Graphics2D g2d, int c, int f) {
        g2d.drawImage(PISO, c * TILE_SIZE, f * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
    }

    private void dibujadoFueraDeVision(Graphics2D g2d, int c, int f) {
        g2d.setColor(Color.BLACK); // Celdas fuera del radio de visión
        g2d.fillRect(c * TILE_SIZE, f * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }


    private void dibujarMascota(Graphics2D g2d) {
        int centroX = mascotaCol * TILE_SIZE + TILE_SIZE / 2;
        int centroY = mascotaFila * TILE_SIZE + TILE_SIZE / 2;
        double angulo = Math.toRadians(direccion * 90);

        int dibujadoX = mascotaCol * TILE_SIZE + (TILE_SIZE - anchoDibujadoMascota) / 2;
        int dibujadoY = mascotaFila * TILE_SIZE + (TILE_SIZE - altoDibujadoMascota) / 2;

        AffineTransform originalTransform = g2d.getTransform(); // Guardar transformaciones previas
        g2d.rotate(angulo, centroX, centroY);
        g2d.drawImage(imagenMascota, dibujadoX, dibujadoY, anchoDibujadoMascota, altoDibujadoMascota, this);
        g2d.setTransform(originalTransform);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(estadoJuego != EstadoJuego.JUGANDO) return;

        if(tiempoRestante <= 0) {
            estadoJuego = EstadoJuego.DERROTA; // Cambiar el estado del juego a derrota
            limiteTiempo.stop();
            timer.stop();
            panelJuegos.derrotaJuego();
        }

        repaint(); // Redibujar el panel

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && estadoJuego == EstadoJuego.MENU) {
            estadoJuego = EstadoJuego.JUGANDO; // Salir del menú principal
            limiteTiempo.start(); // Iniciar el temporizador
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE && estadoJuego != EstadoJuego.JUGANDO) {
            ventanaJuego.dispose(); // Cerrar el juego
            return;
        }
        if (estadoJuego == EstadoJuego.JUGANDO) {

            int nuevaFila = mascotaFila, nuevaColumna = mascotaCol;
            if (e.getKeyCode() == KeyEvent.VK_W) { nuevaFila--; direccion = 0; }
            if (e.getKeyCode() == KeyEvent.VK_S) { nuevaFila++; direccion = 2; }
            if (e.getKeyCode() == KeyEvent.VK_A) { nuevaColumna--; direccion = 3; }
            if (e.getKeyCode() == KeyEvent.VK_D) { nuevaColumna++; direccion = 1; }

            // Se mueve la mascota verificando las paredes y meta
            moverMascota(nuevaFila, nuevaColumna);
        }

    }

    private void moverMascota(int nuevaFila, int nuevaColumna) {
        if (nuevaFila >= 0 && nuevaFila < FILAS && nuevaColumna >= 0 && nuevaColumna < COLS) {
            // Verificar si es un camino o la meta
            if (laberinto[nuevaFila][nuevaColumna] == 0 || laberinto[nuevaFila][nuevaColumna] == 2) {
                mascotaFila = nuevaFila;
                mascotaCol = nuevaColumna;

                // Verificar si llegó a la meta
                if (laberinto[mascotaFila][mascotaCol] == 2) {
                    estadoJuego = EstadoJuego.VICTORIA; // Cambiar el estado del juego a victoria
                    timer.stop();
                    limiteTiempo.stop();
                    panelJuegos.victoriaJuego(RECOMPENSA);
                    repaint(); // Redibujar el panel
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
