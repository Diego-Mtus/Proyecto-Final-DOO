package org.udec.productos;

import org.udec.mascotas.Mascota;
import org.udec.util.CargadorDeImagenes;
import org.udec.util.MascotasEnum;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Alimento {
    private String nombre;
    private MascotasEnum paraQueMascota;
    private BufferedImage imagenAlimento;

    public Alimento(AlimentosEnum alimento) {
        this.nombre = alimento.getNombre();
        this.paraQueMascota = alimento.getParaQueMascota();
        this.imagenAlimento = CargadorDeImagenes.cargarImagen(alimento.getRutaImagen());
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

    public String getNombre() {
        return nombre;
    }

    public MascotasEnum getParaQueMascota() {
        return paraQueMascota;
    }

    public BufferedImage getImagenAlimento() {
        return imagenAlimento;
    }

    @Override
    public String toString() {
        return "Alimento: " + nombre +
               "\nPara: " + paraQueMascota.getNombre();
    }
}
