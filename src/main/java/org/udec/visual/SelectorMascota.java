package org.udec.visual;

import org.udec.mascotas.*;
import org.udec.util.enumerations.MascotasEnum;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class SelectorMascota extends JDialog {

    private MascotasEnum mascotaSeleccionada = null;
    private final List<MascotasEnum> mascotasPosibles;

    public SelectorMascota(PanelEscenario panelEscenario) {
        setTitle("Seleccionar mascota");
        setSize(500, 200);
        setLocationRelativeTo(panelEscenario);
        setLayout(new BorderLayout());
        setModal(true);

        JLabel label = new JLabel("Seleccione una mascota:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        mascotasPosibles = panelEscenario.getEscenario().getTipoEscenario().mascotasCompatibles();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(mascotasPosibles.size() / 3 + 1, 3));

        crearBotonesDeMascota(buttonPanel);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void crearBotonesDeMascota(JPanel buttonPanel){
        for(MascotasEnum mascota : mascotasPosibles){
            System.out.println(mascota.getNombre());
            JButton button = new JButton(mascota.getNombre());
            button.addActionListener(e -> {
                mascotaSeleccionada = mascota;
                dispose();
            });
            buttonPanel.add(button);
        }
    }

    public MascotasEnum getMascotaSeleccionada() {
        return mascotaSeleccionada;
    }
}
