package org.udec.visual.listeners;

import org.udec.util.DineroNoSuficienteException;

public interface CompraListener {
    void comprar(int precio) throws DineroNoSuficienteException;
}
