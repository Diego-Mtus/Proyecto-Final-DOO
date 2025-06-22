package org.udec.visual;

import org.udec.util.TiposEnum;

import javax.swing.*;
import java.awt.*;

public class SelectorEscenario extends JDialog {

    private boolean escenarioSeleccionado = false;

    public SelectorEscenario(PanelEscenario panelEscenario) {
        setTitle("Seleccionar Escenario");
        setSize(300, 200);
        setLocationRelativeTo(panelEscenario);
        setLayout(new BorderLayout());
        setModal(true);

        JLabel label = new JLabel("Seleccione un escenario:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));

        JButton acuaticoButton = new JButton("Pecera");
        acuaticoButton.addActionListener(e -> {
            panelEscenario.establecerEscenario(TiposEnum.ACUATICO);
            escenarioSeleccionado = true;
            dispose();
        });

        JButton voladorButton = new JButton("Pajarera");
        voladorButton.addActionListener(e -> {
            panelEscenario.establecerEscenario(TiposEnum.VOLADOR);
            escenarioSeleccionado = true;
            dispose();
        });

        JButton comunButton = new JButton("Patio");
        comunButton.addActionListener(e -> {
            panelEscenario.establecerEscenario(TiposEnum.COMUN);
            escenarioSeleccionado = true;
            dispose();
        });

        JButton roedorButton = new JButton("Jaula");
        roedorButton.addActionListener(e -> {
            panelEscenario.establecerEscenario(TiposEnum.ROEDOR);
            escenarioSeleccionado = true;
            dispose();
        });

        buttonPanel.add(acuaticoButton);
        buttonPanel.add(voladorButton);
        buttonPanel.add(comunButton);
        buttonPanel.add(roedorButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public boolean isEscenarioSeleccionado() {
        return escenarioSeleccionado;
    }

}
