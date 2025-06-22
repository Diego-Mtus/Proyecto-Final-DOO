package org.udec.escenarios;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.TiposEnum;

public class EscenarioComun extends Escenario{

    EscenarioComun(){
        this.tipoEscenario = TiposEnum.COMUN;
        this.nombre = "Patio";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/comun.png");
    }
}
