package org.udec.visual;

import org.udec.util.enumerations.AlimentosEnum;

import javax.swing.*;

public class TiendaAlimentos extends JDialog {

    private AlimentosEnum[] alimentosDisponibles;

    public TiendaAlimentos(JPanel panelEscenario) {
        super((JFrame) null, "Tienda de Alimentos", true);
        this.alimentosDisponibles = AlimentosEnum.values();

    }

}
