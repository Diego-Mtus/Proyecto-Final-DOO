package org.udec.visual;

import org.udec.util.DineroNoSuficienteException;

public interface CompraListener {
    void comprar(int precio) throws DineroNoSuficienteException;
}
