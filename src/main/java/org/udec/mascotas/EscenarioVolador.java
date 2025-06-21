package org.udec.mascotas;

import org.udec.TiposEnum;
import org.udec.util.CargadorDeImagenes;

import javax.imageio.ImageIO;
import java.io.IOException;

public class EscenarioVolador extends Escenario{

    EscenarioVolador(){
        this.tipoEscenario = TiposEnum.VOLADOR;
        this.nombre = "Pajarera";
        this.imagenEscenario = CargadorDeImagenes.cargarImagen("/acuatico.png");
    }
}
