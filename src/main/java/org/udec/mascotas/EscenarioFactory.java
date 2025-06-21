package org.udec.mascotas;

public class EscenarioFactory {

    public static Escenario crearEscenario(TiposEnum tipo){
        return switch (tipo) {
            case VOLADOR -> new EscenarioVolador();
            case ACUATICO -> new EscenarioAcuatico();
            case COMUN -> new EscenarioComun();
            case ROEDOR -> new EscenarioRoedor();
        };
    }
}
