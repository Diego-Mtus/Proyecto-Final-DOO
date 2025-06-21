package org.udec.mascotas;

import org.udec.util.CargadorDeImagenes;

public class EscenarioAcuatico extends Escenario{

    EscenarioAcuatico(){
        this.tipoEscenario = TiposEnum.ACUATICO;
        this.nombre = "Pecera";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/acuatico.png");
    }
}
