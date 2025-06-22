package org.udec.mascotas;

import org.jetbrains.annotations.NotNull;
import org.udec.escenarios.Escenario;
import org.udec.util.MascotaViviendoException;
import org.udec.util.TipoIncorrectoException;

public abstract class MascotaFactory {

    public abstract void crearMascota (@NotNull Escenario escenario) throws MascotaViviendoException, TipoIncorrectoException;

}