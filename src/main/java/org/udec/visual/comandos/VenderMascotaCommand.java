package org.udec.visual.comandos;

import org.udec.visual.PanelEscenario;

import javax.swing.*;

public class VenderMascotaCommand implements Command{

    private final PanelEscenario panelEscenario;

    public VenderMascotaCommand(PanelEscenario panelEscenario) {
        this.panelEscenario = panelEscenario;
    }

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
