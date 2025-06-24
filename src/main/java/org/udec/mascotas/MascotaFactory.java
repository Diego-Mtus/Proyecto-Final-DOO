package org.udec.mascotas;

import org.udec.escenarios.Escenario;
import org.udec.util.MascotaViviendoException;
import org.udec.util.TipoIncorrectoException;

public abstract class MascotaFactory {

    public abstract void crearMascota (Escenario escenario) throws MascotaViviendoException, TipoIncorrectoException;

}