package org.udec.util;

public enum MascotasEnum {

    // Nombre, Tipo, rutaImagen
    GATO( "Gato", TiposEnum.COMUN, "/mascotas/gato.png", "/mascotas/gato.png", "/sonidos/gato.wav", "GatoFactory"),
    PERRO( "Perro", TiposEnum.COMUN, "/mascotas/perro.png", "/perro.png", "/sonidos/gato.wav", "PerroFactory"),
    HAMSTER( "Hamster", TiposEnum.ROEDOR, "/mascotas/hamster.png", "/mascotas/hamster.png", "/sonidos/hamster.wav", "HamsterFactory"),
    RATON( "Ratón", TiposEnum.ROEDOR, "/mascotas/raton.png", "/mascotas/raton.png", "/sonidos/raton.wav", "RatonFactory"),
    LORO( "Loro", TiposEnum.VOLADOR, "/mascotas/loro.png", "/mascotas/loro.png", "/sonidos/loro.wav", "LoroFactory"),
    GOLONDRINA( "Golondrina", TiposEnum.VOLADOR, "/mascotas/golondrina.png", "/mascotas/golondrina.png", "/sonidos/golondrina.wav", "GolondrinaFactory"),
    PEZDORADO( "Pez dorado", TiposEnum.ACUATICO, "/mascotas/pezdorado.png", "/mascotas/pezdorado.png", "/sonidos/pez.wav", "PezDoradoFactory"),
    PEZPAYASO( "Pez payaso", TiposEnum.ACUATICO, "/mascotas/pezpayaso.png", "/mascotas/pezPayaso.png", "/sonidos/pez.wav", "PezPayasoFactory");

    private final String nombre;
    private final TiposEnum tipo;
    private final String rutaImagen;
    private final String rutaImagenJuego;
    private final String rutaSonido;
    private final String nombreFactory;

    MascotasEnum(String nombre, TiposEnum tipo, String rutaImagen, String rutaImagenJuego, String rutaSonido, String nombreFactory) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.rutaImagen = rutaImagen;
        this.rutaImagenJuego = rutaImagenJuego;
        this.rutaSonido = rutaSonido;
        this.nombreFactory = nombreFactory;
    }

    public String getNombre(){
        return this.nombre;
    }

    public TiposEnum getTipo(){
        return this.tipo;
    }

    public String getRutaImagen(){
        return this.rutaImagen;
    }

    public String getRutaImagenJuego(){
        return this.rutaImagenJuego;
    }

    public String getRutaSonido() {
        return rutaSonido;
    }

    public String getNombreFactory() {
        return nombreFactory;
    }
}
