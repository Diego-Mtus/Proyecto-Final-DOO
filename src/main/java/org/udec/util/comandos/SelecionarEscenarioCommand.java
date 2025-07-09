package org.udec.util.comandos;

import org.udec.util.DineroNoSuficienteException;
import org.udec.util.enumerations.TiposEnum;
import org.udec.visual.SelectorEscenario;
import org.udec.util.listeners.CompraListener;

import javax.swing.*;

public class SelecionarEscenarioCommand implements Command{

    private final TiposEnum tipo;
    private final SelectorEscenario selector;
    private final CompraListener compraListener;

    public SelecionarEscenarioCommand(TiposEnum tipo, SelectorEscenario selector, CompraListener compraListener) {
        this.tipo = tipo;
        this.selector = selector;
        this.compraListener = compraListener;
    }

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
