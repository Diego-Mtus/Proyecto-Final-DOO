package org.udec.visual;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.comandos.Command;
import org.udec.util.comandos.SeleccionarMascotaCommand;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Diálogo para seleccionar una mascota en el escenario.
 * Muestra un conjunto de botones, cada uno representando una mascota disponible.
 * Al seleccionar una mascota, se cierra el diálogo y se guarda la selección.
 */
public class SelectorMascota extends JDialog {

    private MascotasEnum mascotaSeleccionada = null;
    private final List<MascotasEnum> mascotasPosibles;

    private final int ANCHO = 500;
    private final int ALTO = 200;
    private final int COLUMNAS = 3;

    /**
     * Constructor del selector de mascotas.
     * Inicializa el diálogo y configura su apariencia y comportamiento.
     *
     * @param panelEscenario El panel del escenario desde el cual se invoca el selector.
     */
    public SelectorMascota(PanelEscenario panelEscenario) {
        setTitle("Seleccionar mascota");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(panelEscenario);
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());
        JLabel label = new JLabel("Seleccione una mascota:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        mascotasPosibles = panelEscenario.getEscenario().getTipoEscenario().mascotasCompatibles();

        JPanel buttonPanel = crearPanelBotones();
        this.add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel crearPanelBotones() {
        int filas = (int) Math.ceil(mascotasPosibles.size() / (double) COLUMNAS);
        JPanel buttonPanel = new JPanel(new GridLayout(filas, COLUMNAS));

        for (MascotasEnum mascota : mascotasPosibles) {
            buttonPanel.add(crearBotonDeMascota(mascota));
        }

        return buttonPanel;
    }

    private JButton crearBotonDeMascota(MascotasEnum mascota){
        JButton button = new JButton(mascota.getNombre());
        button.setFocusable(false);

        Command comando = new SeleccionarMascotaCommand(mascota, this);
        button.addActionListener(e -> comando.execute());
        return button;
    }

    /**
     * Establece la mascota seleccionado por el usuario.
     * Este método se invoca cuando el usuario selecciona una mascota.
     *
     * @param mascotaSeleccionada La mascota seleccionada por el usuario.
     */
    public void setMascotaSeleccionada(MascotasEnum mascotaSeleccionada) {
        this.mascotaSeleccionada = mascotaSeleccionada;
    }

    /**
     * Obtiene la mascota seleccionada por el usuario.
     * Este método se utiliza para recuperar la mascota seleccionada después de cerrar el diálogo.
     *
     * @return La mascota seleccionada, o null si no se ha seleccionado ninguna.
     */
    public MascotasEnum getMascotaSeleccionada() {
        return mascotaSeleccionada;
    }
}
