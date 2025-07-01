package org.udec.productos;

import org.udec.mascotas.Mascota;
import org.udec.util.CargadorDeImagenes;
import org.udec.util.enumerations.AlimentosEnum;


import java.util.Objects;

public class Alimento extends Producto{

    public Alimento(AlimentosEnum alimento) {
        this.nombre = alimento.getNombre();
        this.paraQueMascota = alimento.getParaQueMascota();
        this.imagen = CargadorDeImagenes.cargarImagen(alimento.getRutaImagen());
        this.precio = alimento.getPrecio();
    }

    public void alimentar(Mascota mascota){
        if (Objects.equals(mascota.getNombreAnimal(), paraQueMascota.getNombre())){
            mascota.getEstado().addHambre(20);
        } else if(mascota.getTipo() == paraQueMascota.getTipo()){
            mascota.getEstado().addHambre(10);
        } else {
            mascota.getEstado().addHambre(5);
            mascota.getEstado().setHambre(mascota.getEstado().verHambre() - 5);
        }
    }

}
