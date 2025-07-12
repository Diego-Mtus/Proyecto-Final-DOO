package org.udec.escenarios;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.TiposEnum;

/**
 * Clase que representa un escenario común.
 * Hereda de la clase Escenario y define un tipo específico de escenario.
 */
public class EscenarioComun extends Escenario{

    /**
     * Constructor de la clase EscenarioComun.
     * Inicializa el tipo de escenario, el nombre y la imagen del escenario común.
     */
    EscenarioComun(){
        this.tipoEscenario = TiposEnum.COMUN;
        this.nombre = "Patio";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/comun.png");
    }
}
