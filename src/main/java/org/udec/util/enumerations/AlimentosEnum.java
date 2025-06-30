package org.udec.util.enumerations;

public enum AlimentosEnum {

    ALIMENTO_PERRO("Croquetas para perro", MascotasEnum.PERRO, "imagenes/alimentos/croquetas_perro.png", 1),
    ALIMENTO_GATO("Croquetas para gato", MascotasEnum.GATO, "imagenes/alimentos/croquetas_gato.png", 1),
    ALIMENTO_GOLONDRINA("Semillas para golondrina", MascotasEnum.GOLONDRINA, "imagenes/alimentos/semillas_pajaro.png", 1),
    ALIMENTO_LORO("Semillas para loro", MascotasEnum.LORO, "imagenes/alimentos/semillas_loro.png", 1),
    ALIMENTO_RATON("Queso para ratón", MascotasEnum.RATON, "imagenes/alimentos/queso_raton.png", 1),
    ALIMENTO_HAMSTER("Alimento para hámster", MascotasEnum.HAMSTER, "imagenes/alimentos/alimento_hamster.png", 1),
    ALIMENTO_PEZDORADO("Alimento para pez dorado", MascotasEnum.PEZDORADO, "imagenes/alimentos/alimento_pez_dorado.png", 1),
    ALIMENTO_PEZPAYASO("Alimento para pez payaso", MascotasEnum.PEZPAYASO, "imagenes/alimentos/alimento_pez_payaso.png", 1);

    private final String nombre;
    private final MascotasEnum paraQueMascota;
    private final String rutaImagen;
    private final int precio;

    AlimentosEnum(String nombre, MascotasEnum paraQueMascota, String rutaImagen, int precio) {
        this.nombre = nombre;
        this.paraQueMascota = paraQueMascota;
        this.rutaImagen = rutaImagen;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public MascotasEnum getParaQueMascota() {
        return paraQueMascota;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public int getPrecio() {
        return precio;
    }
}
