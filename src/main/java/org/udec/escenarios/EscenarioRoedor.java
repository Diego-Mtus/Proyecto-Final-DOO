package org.udec.escenarios;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.TiposEnum;

/**
 * Clase que representa un escenario específico para roedores.
 * Hereda de la clase Escenario y define un tipo de escenario específico.
 */
public class EscenarioRoedor extends Escenario {

    /**
     * Constructor de la clase EscenarioRoedor.
     * Inicializa el tipo de escenario, el nombre y la imagen del escenario para roedores.
     */
    EscenarioRoedor(){
        this.tipoEscenario = TiposEnum.ROEDOR;
        this.nombre = "Jaula";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/roedor.png");
    }
}
