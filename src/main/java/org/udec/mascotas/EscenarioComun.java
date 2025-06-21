package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;

public class EscenarioComun extends Escenario{

    EscenarioComun(){
        this.tipoEscenario = TiposEnum.COMUN;
        this.nombre = "Patio";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/acuatico.png");
    }
}
