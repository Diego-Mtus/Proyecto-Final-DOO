package org.udec.escenarios;

import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.TiposEnum;

/**
 * Clase que representa un escenario acuático.
 * Hereda de la clase Escenario y define un tipo específico de escenario.
 */
public class EscenarioAcuatico extends Escenario{

    /**
     * Constructor de la clase EscenarioAcuatico.
     * Inicializa el tipo de escenario, el nombre y la imagen del escenario acuático.
     */
    EscenarioAcuatico(){
        this.tipoEscenario = TiposEnum.ACUATICO;
        this.nombre = "Pecera";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/escenarios/acuatico.png");
    }
}
