package org.udec.visual.acciones;

import org.udec.util.enumerations.EstadoJuego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class JuegoAcuatico extends JPanel implements MouseMotionListener, ActionListener, KeyListener {

    private final JDialog ventanaJuego;
    private final PanelJuegos panelJuegos;
    private final int RECOMPENSA = 60; // Recompensa por ganar el juego

    private final int ANCHO = 700;
    private final int ALTO = 700;
    private final int CENTRO_X = ANCHO / 2;
    private final int CENTRO_Y = ALTO / 2;

    private final BufferedImage imagenMascota;
    private final Font fuente = new Font("Arial", Font.BOLD, 20);

    // Variables de juego
    private Timer timer;
    private int mascotaX = CENTRO_X;
    private int mascotaY = CENTRO_Y;
    private final int MASCOTA_SIZE = 30; // Para colisiones
    private final int anchoDibujadoMascota;
    private final int altoDibujadoMascota;

    // Variables de obstáculos
    private double[] anguloBaseDeConjunto; // Se generará un angulo aleatorio para cada conjunto de obstáculos
    private final int OBSTACULOS_POR_CONJUNTO = 24;
    private final int CANTIDAD_CONJUNTOS = 4;
    private final int DISTANCIA_ENTRE_CONJUNTOS = 140;
    private final double RANGO_ANGULO = 290.0; // Los obstaculos cubren 290 grados
    private final double RADIO_DE_PRIMER_CONJUNTO = 200.0; // El más cercano al centro
    private List<Obstaculo> obstaculos = new ArrayList<>();


    // Variables de estado
    private EstadoJuego estadoJuego = EstadoJuego.MENU;
    private final int PUNTOS_WIN = 30; // Puntos necesarios para ganar
    private int puntos = 0;
    private int puntosAux = 0;

    public JuegoAcuatico(BufferedImage imagenMascota, PanelJuegos panelJuegos) {
        ventanaJuego = new JDialog();
        ventanaJuego.setContentPane(this);
        ventanaJuego.setLayout(null);
        ventanaJuego.setResizable(false);
        ventanaJuego.setTitle("Juego acuático: Esquiva los obstáculos");
        ventanaJuego.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ventanaJuego.setSize(ANCHO, ALTO);
        ventanaJuego.setLocationRelativeTo(null);

        this.panelJuegos = panelJuegos;

        int imagenMascotaWidth = imagenMascota.getWidth();
        int imagenMascotaHeight = imagenMascota.getHeight();
        double escalaMascota = Math.min((double) MASCOTA_SIZE / imagenMascotaWidth, (double) MASCOTA_SIZE/ imagenMascotaHeight);

        anchoDibujadoMascota = (int)(imagenMascotaWidth * escalaMascota);
        altoDibujadoMascota = (int)(imagenMascotaHeight * escalaMascota);

        // Para ahorrar recursos, se escala la imagen de la mascota desde un inicio
        this.imagenMascota = redimensionarImagenMascota(imagenMascota);

        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        timer = new Timer(16, this);

        inicializarObstaculos();

        setCursor(getMouseInvisible());
        setVisible(true);
        setFocusable(true);
        ventanaJuego.setVisible(true);
        requestFocusInWindow();
    }

    private BufferedImage redimensionarImagenMascota(BufferedImage imagenMascota) {
        BufferedImage imagenMascotaEscalada = new BufferedImage(anchoDibujadoMascota, altoDibujadoMascota, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gMascota = imagenMascotaEscalada.createGraphics();
        gMascota.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        gMascota.drawImage(imagenMascota, 0, 0, anchoDibujadoMascota, altoDibujadoMascota, null);
        gMascota.dispose();
        return imagenMascotaEscalada;
    }

    private void revisarParaInicioJuego() {
        int tolerancia = 20; // Tolerancia para iniciar el juego
        if (Math.abs(mascotaX - CENTRO_X) <= tolerancia &&
            Math.abs(mascotaY - CENTRO_Y) <= tolerancia) {
            estadoJuego = EstadoJuego.JUGANDO;
            timer.start();
        }
        repaint();
    }

    // Lo busqué para implementarlo, crea una imagen transparente de 1x1 píxel y la usa como mouse
    private Cursor getMouseInvisible() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image transparentImage = toolkit.createImage(new byte[0]);
        return toolkit.createCustomCursor(transparentImage, new Point(0, 0), "MouseInvisible");
    }

    private void inicializarObstaculos() {
        anguloBaseDeConjunto = new double[CANTIDAD_CONJUNTOS];

        for(int conj = 0; conj < CANTIDAD_CONJUNTOS; conj++){

            double radio = RADIO_DE_PRIMER_CONJUNTO + DISTANCIA_ENTRE_CONJUNTOS * conj;
            anguloBaseDeConjunto[conj] = Math.random() * 360; // Ángulo base aleatorio para cada conjunto
            double anguloSeparacion = RANGO_ANGULO / OBSTACULOS_POR_CONJUNTO;
            for(int i = 0; i < OBSTACULOS_POR_CONJUNTO; i++) {
                double anguloGrados = anguloBaseDeConjunto[conj] + i * anguloSeparacion;
                obstaculos.add(new Obstaculo(radio, anguloGrados, conj, i));
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Configuración inicial de gráficos
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(fuente);
        FontMetrics fontMetrics = g2d.getFontMetrics();

        // Dibujar fondo
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, ANCHO, ALTO);

        // Dibujar mascota
        dibujarMascota(g2d);

        switch (estadoJuego) {
            case MENU -> dibujarMenu(g2d, fontMetrics);
            case JUGANDO -> dibujarObstaculos(g2d);
            case VICTORIA -> dibujarVictoria(g2d, fontMetrics);
            case DERROTA -> dibujarDerrota(g2d, fontMetrics);
        }

        // Dibujar puntos
        g2d.setColor(Color.WHITE);
        g2d.drawString("Puntos: " + puntos, 10, 20);
    }

    private void dibujarMascota(Graphics2D g2d){
        int dibujadoX = mascotaX - anchoDibujadoMascota / 2;
        int dibujadoY = mascotaY - altoDibujadoMascota / 2;

        g2d.drawImage(imagenMascota, dibujadoX, dibujadoY, anchoDibujadoMascota, altoDibujadoMascota, null);
    }

    private void dibujarMenu(Graphics2D g2d, FontMetrics fontMetrics) {
        g2d.setColor(Color.WHITE);
        String msg1 = "Si juntas " + PUNTOS_WIN + " puntos, ganas el juego";
        String msg2 = "Acerca el mouse al centro para iniciar";
        String msg3 = "Presiona 'Esc' para salir";
        g2d.drawString(msg1, (ANCHO - fontMetrics.stringWidth(msg1)) / 2, 100);
        g2d.drawString(msg2, (ANCHO - fontMetrics.stringWidth(msg2)) / 2, 150);
        g2d.drawString(msg3, (ANCHO - fontMetrics.stringWidth(msg3)) / 2, 200);
    }

    private void dibujarVictoria(Graphics2D g2d, FontMetrics fontMetrics) {
        g2d.setColor(Color.GREEN);
        String msg1 = "¡Felicidades! Has ganado el juego.";
        String msg2 = "Has ganado $" + RECOMPENSA;
        String msg3 = "Presiona 'Esc' para salir";
        g2d.drawString(msg1, (ANCHO - fontMetrics.stringWidth(msg1)) / 2, 100);
        g2d.drawString(msg2, (ANCHO - fontMetrics.stringWidth(msg2)) / 2, 150);
        g2d.drawString(msg3, (ANCHO - fontMetrics.stringWidth(msg3)) / 2, 200);
    }

    private void dibujarDerrota(Graphics2D g2d, FontMetrics fontMetrics) {
        g2d.setColor(Color.RED);
        String msg1 = "¡Has perdido! No esquivaste los obstáculos.";
        String msg2 = "Presiona 'Esc' para salir";
        g2d.drawString(msg1, (ANCHO - fontMetrics.stringWidth(msg1)) / 2, 100);
        g2d.drawString(msg2, (ANCHO - fontMetrics.stringWidth(msg2)) / 2, 150);
    }

    private void dibujarObstaculos(Graphics2D g2d) {
        for (Obstaculo obstaculo : obstaculos) {
            obstaculo.dibujar(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(estadoJuego != EstadoJuego.JUGANDO) return; // No actualizar si no estamos en el estado de juego

        actualizarObstaculos();

        if(puntos >= PUNTOS_WIN) {
            estadoJuego = EstadoJuego.VICTORIA;

            panelJuegos.victoriaJuego(RECOMPENSA);
            timer.stop();
        }

        repaint();
    }

    private void actualizarObstaculos() {

        for (Obstaculo obstaculo : obstaculos) {
            obstaculo.actualizarPosicion();

            if(obstaculo.detectarColision(mascotaX, mascotaY, MASCOTA_SIZE)) {
                estadoJuego = EstadoJuego.DERROTA;
                timer.stop();
                panelJuegos.derrotaJuego();
                repaint();
            }

            if(obstaculo.getRadio() < 20){
                obstaculo.reiniciarPosicion();
                actualizarPuntuacion();
            }
        }

    }

    private void actualizarPuntuacion() {
        puntosAux++;
        if(puntosAux == OBSTACULOS_POR_CONJUNTO){
            puntos++;
            for(Obstaculo obs : obstaculos) {
                obs.addVelocidad(0.04);
            }

            puntosAux = 0; // Reinicia el contador de puntos por conjunto
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mascotaX = e.getX();
        mascotaY = e.getY();
        if (estadoJuego == EstadoJuego.MENU) revisarParaInicioJuego();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mascotaX = e.getX();
        mascotaY = e.getY();
        if (estadoJuego == EstadoJuego.MENU) revisarParaInicioJuego();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(estadoJuego != EstadoJuego.JUGANDO) ventanaJuego.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    // ============================================================

    // Ya que tiene mucha lógica propia, se implementará como clase interna
    class Obstaculo {

        private double radio;
        private double angulo;
        private double velocidad = 3.2;
        private final int SIZE = 14;

        private final int indiceConjunto;
        private final int indiceIndividual;

        public Obstaculo(double radio, double anguloEnGrados, int indiceConjunto, int indiceIndividual) {
            this.radio = radio;
            this.angulo = Math.toRadians(anguloEnGrados);
            this.indiceConjunto = indiceConjunto;
            this.indiceIndividual = indiceIndividual;
        }

        public void actualizarPosicion() {
            this.radio -= velocidad; // Se va contrayendo
        }

        public void dibujar(Graphics2D g2d) {
            // Se transforma de coordenadas polares a cartesianas, y se establece en centro;
            int x = (int) (CENTRO_X + radio * Math.cos(angulo)) - SIZE / 2;
            int y = (int) (CENTRO_Y + radio * Math.sin(angulo)) - SIZE / 2;
            g2d.setColor(Color.GREEN);
            g2d.fillOval(x, y, SIZE, SIZE);
        }

        public void addVelocidad(double incremento) {
            this.velocidad += incremento;
        }

        public boolean detectarColision(int mascotaX, int mascotaY, int mascotaSize) {

            // Verifica si la mascota colisiona con el obstáculo
            int centroX = (int) (CENTRO_X + radio * Math.cos(angulo));
            int centroY = (int) (CENTRO_Y + radio * Math.sin(angulo));
            int distanciaCuadrado = (centroX - mascotaX - mascotaSize / 2) * (centroX - mascotaX - mascotaSize / 2)
                    + (centroY - mascotaY - mascotaSize / 2) * (centroY - mascotaY - mascotaSize / 2);

            return distanciaCuadrado <= SIZE * SIZE;
        }

        public double getRadio() {
            return radio;
        }

        public void reiniciarPosicion() {
            this.radio = RADIO_DE_PRIMER_CONJUNTO + DISTANCIA_ENTRE_CONJUNTOS * (CANTIDAD_CONJUNTOS - 1);

            // Para que cada conjunto de obstaculos se regenere con un ángulo diferente
            if(indiceIndividual == 0) {
                anguloBaseDeConjunto[indiceConjunto] = Math.random() * 360;
                double anguloSeparacion = RANGO_ANGULO / OBSTACULOS_POR_CONJUNTO;

                for(Obstaculo obs : obstaculos){
                    if(obs.indiceConjunto == indiceConjunto){
                        double anguloNuevo = anguloBaseDeConjunto[indiceConjunto] + obs.indiceIndividual * anguloSeparacion;
                        obs.angulo = Math.toRadians(anguloNuevo);
                    }
                }

            }
        }


    }
}
