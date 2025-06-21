package org.udec.mascotas;

import java.util.ArrayList;
import java.util.List;

public enum TiposEnum {
    VOLADOR,
    ACUATICO,
    ROEDOR,
    COMUN;

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
