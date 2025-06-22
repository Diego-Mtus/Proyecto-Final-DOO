package org.udec.util;

public enum MascotasEnum {

    // Nombre, Tipo, rutaImagen
    GATO( "Gato", TiposEnum.COMUN, "/mascotas/gato.png", "/mascotas/gato.png", "/sonidos/gato.wav"),
    PERRO( "Perro", TiposEnum.COMUN, "/perro.png", "/perro.png", "/sonidos/perro.wav"),
    HAMSTER( "Hamster", TiposEnum.ROEDOR, "/hamster.png", "/hamster.png", "/sonidos/hamster.wav"),
    RATON( "Ratón", TiposEnum.ROEDOR, "/raton.png", "/raton.png", "/sonidos/raton.wav"),
    LORO( "Loro", TiposEnum.VOLADOR, "/loro.png", "/loro.png", "/sonidos/loro.wav"),
    GOLONDRINA( "Golondrina", TiposEnum.VOLADOR, "/golondrina.png", "/golondrina.png", "/sonidos/golondrina.wav"),
    PEZDORADO( "Pez dorado", TiposEnum.ACUATICO, "/pezDorada.png", "/pezDorada.png", "/sonidos/pez.wav"),
    PEZPAYASO( "Pez payaso", TiposEnum.ACUATICO, "/pezPayaso.png", "/pezPayaso.png", "/sonidos/pez.wav");

    private final String nombre;
    private final TiposEnum tipo;
    private final String rutaImagen;
    private final String rutaImagenJuego;
    private final String rutaSonido;

    MascotasEnum(String nombre, TiposEnum tipo, String rutaImagen, String rutaImagenJuego, String rutaSonido) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.rutaImagen = rutaImagen;
        this.rutaImagenJuego = rutaImagenJuego;
        this.rutaSonido = rutaSonido;
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
}
