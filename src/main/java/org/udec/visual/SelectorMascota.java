package org.udec.visual;

import org.udec.util.enumerations.MascotasEnum;
import org.udec.util.comandos.Command;
import org.udec.util.comandos.SeleccionarMascotaCommand;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SelectorMascota extends JDialog {

    private MascotasEnum mascotaSeleccionada = null;
    private final List<MascotasEnum> mascotasPosibles;

    private final int ANCHO = 500;
    private final int ALTO = 200;
    private final int COLUMNAS = 3;

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

    public void setMascotaSeleccionada(MascotasEnum mascotaSeleccionada) {
        this.mascotaSeleccionada = mascotaSeleccionada;
    }

    public MascotasEnum getMascotaSeleccionada() {
        return mascotaSeleccionada;
    }
}
