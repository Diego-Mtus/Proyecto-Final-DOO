package org.udec.util.enumerations;

public enum AlimentosEnum implements ProductosEnum{

    ALIMENTO_PERRO("Croquetas para perro", MascotasEnum.PERRO, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_GATO("Croquetas para gato", MascotasEnum.GATO, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_GOLONDRINA("Semillas para golondrina", MascotasEnum.GOLONDRINA, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_LORO("Semillas para loro", MascotasEnum.LORO, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_RATON("Queso para ratón", MascotasEnum.RATON, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_HAMSTER("Alimento para hámster", MascotasEnum.HAMSTER, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_PEZDORADO("Alimento para pez dorado", MascotasEnum.PEZDORADO, "/mascotas/pezpayaso.png", 1),
    ALIMENTO_PEZPAYASO("Alimento para pez payaso", MascotasEnum.PEZPAYASO, "/mascotas/pezpayaso.png", 1);

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
