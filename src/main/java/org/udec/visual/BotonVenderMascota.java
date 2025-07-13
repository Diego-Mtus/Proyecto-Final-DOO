package org.udec.visual;

import org.udec.util.comandos.Command;
import org.udec.util.comandos.VenderMascotaCommand;
import org.udec.util.enumerations.BotonesUI;

import javax.swing.*;

/**
 * Botón para vender una mascota en el escenario.
 * Al hacer clic en el botón, se ejecuta un comando que maneja la lógica de venta.
 * Extiende de JButtonAnimado para incluir animación al presionar.
 */
public class BotonVenderMascota extends JButtonAnimado{

    /**
     * Constructor del botón de vender mascota.
     * @param panelEscenario El panel del escenario donde se mostrará el botón.
     * @param x Posición x del botón en el panel.
     * @param y Posición y del botón en el panel.
     */
    BotonVenderMascota(PanelEscenario panelEscenario, int x, int y) {
        super(BotonesUI.BOTON_VENDERMASCOTA.getImagen(), x, y, 50, 140);
        setVisible(false);

        panelEscenario.add(this);

        Command comando = new VenderMascotaCommand(panelEscenario);
        this.addActionListener(_ -> comando.execute());

    }
}
