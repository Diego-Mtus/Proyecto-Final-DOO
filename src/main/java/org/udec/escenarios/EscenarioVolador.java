package org.udec.escenarios;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.TiposEnum;

/**
 * Clase que representa un escenario volador.
 * Hereda de la clase Escenario.
 */
public class EscenarioVolador extends Escenario{

    /**
     * Constructor de la clase EscenarioVolador.
     * Inicializa el tipo de escenario, el nombre y la imagen del escenario volador.
     */
    EscenarioVolador(){
        this.tipoEscenario = TiposEnum.VOLADOR;
        this.nombre = "Pajarera";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/volador.png");
    }
}
