package org.udec.mascotas;

import org.udec.TiposEnum;

public abstract class Mascota {

    protected String nombrePropio;
    protected String nombreAnimal;
    protected TiposEnum tipo;

    public TiposEnum getTipo(){
        return this.tipo;
    }

    public String getNombreAnimal(){
        return this.nombreAnimal;
    }

    public String getNombrePropio(){
        return this.nombrePropio;
    }

    @Override
    public String toString() {
        return "Mascota: " +
                "nombrePropio='" + nombrePropio + '\'' +
                ", nombreAnimal='" + nombreAnimal + '\'' +
                ", tipo=" + tipo;
    }
}
