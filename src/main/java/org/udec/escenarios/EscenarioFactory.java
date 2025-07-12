package org.udec.escenarios;

import org.udec.util.enumerations.TiposEnum;


/**
 * Clase de fábrica para crear instancias de diferentes tipos de escenarios.
 */
public class EscenarioFactory {

    /**
     * Crea un escenario basado en el tipo proporcionado.
     *
     * @param tipo El tipo de escenario a crear.
     * @return Una instancia del escenario correspondiente al tipo.
     */
    public static Escenario crearEscenario(TiposEnum tipo){
        return switch (tipo) {
            case VOLADOR -> new EscenarioVolador();
            case ACUATICO -> new EscenarioAcuatico();
            case COMUN -> new EscenarioComun();
            case ROEDOR -> new EscenarioRoedor();
        };
    }
}
