package org.udec.mascotas;

import org.udec.TiposEnum;
import org.udec.util.CargadorDeImagenes;

import javax.imageio.ImageIO;
import java.io.IOException;

public class EscenarioAcuatico extends Escenario{

    EscenarioAcuatico(){
        this.tipoEscenario = TiposEnum.ACUATICO;
        this.nombre = "Pecera";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/acuatico.png");
    }
}
