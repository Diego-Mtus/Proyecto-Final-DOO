package org.udec.util.enumerations;

import java.util.ArrayList;
import java.util.List;

public enum TiposEnum {
    VOLADOR("Pajarera"),
    ACUATICO("Pecera"),
    ROEDOR("Jaula"),
    COMUN("Patio"),;

    private final String nombreEscenario;

    TiposEnum(String nombreEscenario) {
        this.nombreEscenario = nombreEscenario;
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
