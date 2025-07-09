package org.udec.util.comandos;

import org.udec.util.DineroNoSuficienteException;
import org.udec.util.enumerations.ProductosEnum;
import org.udec.visual.PanelDescripcion;
import org.udec.util.listeners.CompraListener;

import javax.swing.*;

public class ComprarProductoCommand implements Command{

    private final ProductosEnum producto;
    private final CompraListener compraListener;
    private final PanelDescripcion panelDescripcion;

    public ComprarProductoCommand(ProductosEnum producto, CompraListener compraListener, PanelDescripcion panelDescripcion) {
        this.producto = producto;
        this.compraListener = compraListener;
        this.panelDescripcion = panelDescripcion;
    }

    @Override
    public void execute() {
        try{
            compraListener.comprar(producto.getPrecio());
            producto.setInventario(producto.getInventario() + 1);
            panelDescripcion.actualizarDescripcion(producto);
        } catch (DineroNoSuficienteException e){
            JOptionPane.showMessageDialog(  null,
                    "No tienes suficiente dinero para comprar este producto.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
