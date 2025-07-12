package org.udec.util.comandos;

import org.udec.util.DineroNoSuficienteException;
import org.udec.util.enumerations.TiposEnum;
import org.udec.visual.SelectorEscenario;
import org.udec.util.listeners.CompraListener;

import javax.swing.*;

/**
 * Clase que representa un comando para seleccionar un escenario y realizar la compra del mismo.
 * Implementa la interfaz Command.
 */
public class SelecionarEscenarioCommand implements Command{

    private final TiposEnum tipo;
    private final SelectorEscenario selector;
    private final CompraListener compraListener;

    /**
     * Constructor de la clase SelecionarEscenarioCommand.
     * Guarda referencia del tipo de escenario, el selector de escenario y el listener de compra.
     *
     * @param tipo Tipo de escenario a seleccionar.
     * @param selector SelectorEscenario donde se selecciona el escenario.
     * @param compraListener Listener que maneja la compra del escenario.
     */
    public SelecionarEscenarioCommand(TiposEnum tipo, SelectorEscenario selector, CompraListener compraListener) {
        this.tipo = tipo;
        this.selector = selector;
        this.compraListener = compraListener;
    }

    /**
     * Método que ejecuta la compra del escenario seleccionado.
     * Si el usuario no tiene suficiente dinero, muestra un mensaje de error.
     */
    @Override
    public void execute() {
        try {
            compraListener.comprar(tipo.getPrecioEscenario());
            selector.setEscenarioSeleccionado(tipo);
            selector.dispose();
        } catch (DineroNoSuficienteException ex) {
            JOptionPane.showMessageDialog(selector, "Dinero insuficiente para comprar este escenario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
