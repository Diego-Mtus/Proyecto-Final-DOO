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

        crearBotonesDeEscenario(panelEscenario, buttonPanel);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public boolean isEscenarioSeleccionado() {
        return escenarioSeleccionado;
    }

    private void crearBotonesDeEscenario(PanelEscenario panelEscenario, JPanel buttonPanel) {
        for(TiposEnum tipo : TiposEnum.values()) {
            JButton button = new JButton(tipo.name());
            button.addActionListener(e -> {
                panelEscenario.establecerEscenario(tipo);
                escenarioSeleccionado = true;
                dispose();
            });
            buttonPanel.add(button);
        }
    }

}
