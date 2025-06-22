package org.udec.escenarios;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.TiposEnum;

public class EscenarioAcuatico extends Escenario{

    EscenarioAcuatico(){
        this.tipoEscenario = TiposEnum.ACUATICO;
        this.nombre = "Pecera";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/acuatico.png");
    }
}
