package org.udec.visual;

import org.udec.util.enumerations.TiposEnum;

import javax.swing.*;
import java.awt.*;

public class SelectorEscenario extends JDialog {

    private TiposEnum escenarioSeleccionado = null;

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

        crearBotonesDeEscenario(buttonPanel);

        add(buttonPanel, BorderLayout.CENTER);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public TiposEnum getEscenarioSeleccionado() {
        return escenarioSeleccionado;
    }

    private void crearBotonesDeEscenario(JPanel buttonPanel) {
        for(TiposEnum tipo : TiposEnum.values()) {
            JButton button = new JButton(tipo.getNombreEscenario());
            button.addActionListener(e -> {
                escenarioSeleccionado = tipo;
                dispose();
            });
            button.setFocusable(false);
            buttonPanel.add(button);
        }
    }

}
