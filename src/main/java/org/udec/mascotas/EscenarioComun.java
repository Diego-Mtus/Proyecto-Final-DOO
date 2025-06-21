package org.udec.mascotas;

import org.udec.TiposEnum;
import org.udec.util.CargadorDeImagenes;

import javax.imageio.ImageIO;
import java.io.IOException;

public class EscenarioComun extends Escenario{

    EscenarioComun(){
        this.tipoEscenario = TiposEnum.COMUN;
        this.nombre = "Patio";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/acuatico.png");
    }
}
