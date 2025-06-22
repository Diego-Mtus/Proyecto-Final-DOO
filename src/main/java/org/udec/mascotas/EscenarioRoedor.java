package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;

public class EscenarioRoedor extends Escenario {

    EscenarioRoedor(){
        this.tipoEscenario = TiposEnum.ROEDOR;
        this.nombre = "Jaula";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/roedor.png");
    }
}
