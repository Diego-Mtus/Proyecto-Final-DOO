package org.udec.escenarios;

import org.udec.util.enumerations.TiposEnum;

public class EscenarioFactory {

    // TODO: Implementar un patrón de diseño para crear los escenarios, no usar un switch estático.
    public static Escenario crearEscenario(TiposEnum tipo){
        return switch (tipo) {
            case VOLADOR -> new EscenarioVolador();
            case ACUATICO -> new EscenarioAcuatico();
            case COMUN -> new EscenarioComun();
            case ROEDOR -> new EscenarioRoedor();
        };
    }
}
