package org.udec.util.comandos;

import org.udec.visual.PanelEscenario;

import javax.swing.*;

/**
 * Clase que representa el comando para vender una mascota.
 * Este comando permite al usuario vender la mascota actual del escenario,
 * mostrando un mensaje de confirmación antes de proceder con la venta.
 * Implementa la interfaz Command.
 */
public class VenderMascotaCommand implements Command{

    private final PanelEscenario panelEscenario;

    /**
     * Constructor de la clase VenderMascotaCommand.
     * Inicializa el comando con el panel del escenario donde se realizará la venta.
     *
     * @param panelEscenario El panel del escenario que contiene la mascota a vender.
     */
    public VenderMascotaCommand(PanelEscenario panelEscenario) {
        this.panelEscenario = panelEscenario;
    }

    /**
     * Método que ejecuta el comando de vender la mascota.
     * Muestra un mensaje de confirmación y, si se acepta, procede a vender la mascota,
     * actualizando el panel del escenario y mostrando un mensaje de éxito.
     */
    @Override
    public void execute() {
        System.out.println("Se vende la mascota");
        if (panelEscenario.tieneEscenario() && panelEscenario.tieneMascota()){
            int respuesta = JOptionPane.showConfirmDialog(panelEscenario,
                    "¿Estás seguro de que quieres vender a la mascota?",
                    "Confirmar venta",
                    JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                int dineroObtenido = panelEscenario.getEscenario().getMascotaActual().getPrecioVenta();
                panelEscenario.venderMascota();
                panelEscenario.mostrarBotonAdoptarMascota();
                JOptionPane.showMessageDialog(panelEscenario,
                        "Has ganado $" + dineroObtenido,
                        "¡Mascota vendida exitosamente!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(panelEscenario,
                    "No tienes una mascota para vender.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

}
