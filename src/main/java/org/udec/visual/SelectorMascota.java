package org.udec.visual;

import org.udec.escenarios.Escenario;
import org.udec.mascotas.*;
import org.udec.util.MascotasEnum;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class SelectorMascota extends JDialog {

    private boolean mascotaSeleccionada = false;
    private List<MascotasEnum> mascotasPosibles;

    public SelectorMascota(PanelEscenario panelEscenario) {
        setTitle("Seleccionar mascota");
        setSize(500, 500);
        setLocationRelativeTo(panelEscenario);
        setLayout(new BorderLayout());
        setModal(true);

        JLabel label = new JLabel("Seleccione una mascota:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        mascotasPosibles = panelEscenario.getEscenario().getTipoEscenario().mascotasCompatibles();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(mascotasPosibles.size() / 3 + 1, 3));

        crearBotonesDeMascota(panelEscenario, buttonPanel);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void crearBotonesDeMascota(PanelEscenario panelEscenario, JPanel buttonPanel){
        for(MascotasEnum mascota : mascotasPosibles){
            System.out.println(mascota.getNombre());
            JButton button = new JButton(mascota.getNombre());
            button.addActionListener(e -> {
                MascotaFactory mascotaFactory = seleccionDinamicaDeFactory(mascota);
                if(mascotaFactory != null) {
                    panelEscenario.establecerMascota(mascotaFactory);
                    mascotaSeleccionada = true;
                    dispose();
                }

            });
            buttonPanel.add(button);
        }
    }

    private MascotaFactory seleccionDinamicaDeFactory(MascotasEnum mascotasEnum){
        try {
            Constructor<?>[] factories = Class.forName("org.udec.mascotas." + mascotasEnum.getNombreFactory()).getConstructors();
            System.out.println(Arrays.toString(factories));
            return (MascotaFactory) factories[0].newInstance();
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.getMessage();
        }
        return null;
    }

    public boolean isMascotaSeleccionada() {
        return mascotaSeleccionada;
    }
}
