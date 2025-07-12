package org.udec.util.comandos;

import org.udec.util.DineroNoSuficienteException;
import org.udec.util.enumerations.ProductosEnum;
import org.udec.visual.PanelDescripcion;
import org.udec.util.listeners.CompraListener;

import javax.swing.*;


/**
 * Comando para comprar un producto en la tienda.
 * Esta clase encapsula la lógica de compra de un producto específico,
 * actualizando el inventario del producto y notificando al listener de compra.
 * Implementa la interfaz Command.
 */
public class ComprarProductoCommand implements Command{

    private final ProductosEnum producto;
    private final CompraListener compraListener;
    private final PanelDescripcion panelDescripcion;

    /**
     * Constructor de la clase ComprarProductoCommand.
     * Guarda referencia del producto a comprar, el listener de compra y el panel de descripción.
     *
     * @param producto El producto que se desea comprar.
     * @param compraListener El listener que maneja la lógica de compra.
     * @param panelDescripcion El panel que muestra la descripción del producto.
     */
    public ComprarProductoCommand(ProductosEnum producto, CompraListener compraListener, PanelDescripcion panelDescripcion) {
        this.producto = producto;
        this.compraListener = compraListener;
        this.panelDescripcion = panelDescripcion;
    }

    /**
     * Ejecuta el comando de compra del producto.
     * Intenta comprar el producto, actualizando su inventario y manejando excepciones
     * si el dinero del usuario no es suficiente.
     */
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
