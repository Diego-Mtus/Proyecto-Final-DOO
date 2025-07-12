package org.udec.util.enumerations;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeración que define los tipos de escenarios disponibles para las mascotas.
 * Cada tipo tiene un nombre y un precio asociado.
 */
public enum TiposEnum {
    VOLADOR("Pajarera", 100),
    ACUATICO("Pecera", 150),
    ROEDOR("Jaula", 80),
    COMUN("Patio", 50),;

    private final String nombreEscenario;
    private final int precioEscenario;

    /**
     * Constructor de la enumeración TiposEnum.
     *
     * @param nombreEscenario El nombre del escenario.
     * @param precioEscenario El precio del escenario.
     */
    TiposEnum(String nombreEscenario, int precioEscenario) {
        this.nombreEscenario = nombreEscenario;
        this.precioEscenario = precioEscenario;
    }

    /**
     * Obtiene el precio del escenario asociado a este tipo.
     *
     * @return El precio del escenario.
     */
    public int getPrecioEscenario() {
        return precioEscenario;
    }

    /**
     * Obtiene el nombre del escenario asociado a este tipo.
     *
     * @return El nombre del escenario.
     */
    public String getNombreEscenario() {
        return nombreEscenario;
    }

    /**
     * Obtiene una lista de las mascotas compatibles con este tipo de escenario.
     *
     * @return Una lista de enumeraciones de mascotas compatibles.
     */
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
