package org.udec.visual;

import org.udec.util.comandos.AdoptarMascotaCommand;
import org.udec.util.comandos.Command;
import org.udec.util.enumerations.BotonesUI;


/** Botón para adoptar una mascota en el escenario.
 * Al hacer clic en el botón, se ejecuta un comando que maneja la lógica de adopción.
 * Extiende de JButtonAnimado para incluir animación al presionar.
 */
public class BotonAdoptarMascota extends JButtonAnimado {

    /**
     * Constructor del botón de adoptar mascota.
     * @param panelEscenario El panel del escenario donde se mostrará el botón.
     * @param x Posición x del botón en el panel.
     * @param y Posición y del botón en el panel.
     */
    public BotonAdoptarMascota(PanelEscenario panelEscenario, int x, int y) {
        super(BotonesUI.BOTON_ADOPTARMASCOTA.getImagen(), x, y, 240, 90);
        setVisible(true);

        panelEscenario.add(this);

        Command comando = new AdoptarMascotaCommand(panelEscenario);
        this.addActionListener(e -> comando.execute());

    }
}
