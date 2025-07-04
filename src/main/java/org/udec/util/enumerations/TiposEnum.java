package org.udec.util.enumerations;

import java.util.ArrayList;
import java.util.List;

public enum TiposEnum {
    VOLADOR("Pajarera", 100),
    ACUATICO("Pecera", 150),
    ROEDOR("Jaula", 80),
    COMUN("Patio", 50),;

    private final String nombreEscenario;
    private final int precioEscenario;

    TiposEnum(String nombreEscenario, int precioEscenario) {
        this.nombreEscenario = nombreEscenario;
        this.precioEscenario = precioEscenario;
    }

    public int getPrecioEscenario() {
        return precioEscenario;
    }
    public String getNombreEscenario() {
        return nombreEscenario;
    }

    public List<MascotasEnum> mascotasCompatibles(){
        List<MascotasEnum> lista = new ArrayList<>();
        for(MascotasEnum mascota : MascotasEnum.values()){
            if(mascota.getTipo() == this){
                lista.add(mascota);
            }
        }
        return lista;
    }

}
