package org.udec.escenarios;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.TiposEnum;

public class EscenarioRoedor extends Escenario {

    EscenarioRoedor(){
        this.tipoEscenario = TiposEnum.ROEDOR;
        this.nombre = "Jaula";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/roedor.png");
    }
}
