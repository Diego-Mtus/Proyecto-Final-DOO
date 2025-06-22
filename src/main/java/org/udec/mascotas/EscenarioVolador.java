package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;

public class EscenarioVolador extends Escenario{

    EscenarioVolador(){
        this.tipoEscenario = TiposEnum.VOLADOR;
        this.nombre = "Pajarera";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/volador.png");
    }
}
