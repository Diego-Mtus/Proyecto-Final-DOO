package org.udec.escenarios;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.TiposEnum;

public class EscenarioVolador extends Escenario{

    EscenarioVolador(){
        this.tipoEscenario = TiposEnum.VOLADOR;
        this.nombre = "Pajarera";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/volador.png");
    }
}
