package org.udec.visual;

import javax.swing.*;
import java.awt.*;

public class PanelAcciones extends JPanel {
    public PanelAcciones(PanelEscenario panel, int x, int y) {
        setBounds(x, y, 200, 120);
        setLayout(null);
        setVisible(false);
        setOpaque(false);
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(Color.black, 1)); // Para verlo mientras se desarrolla
        panel.add(this);
    }

}
