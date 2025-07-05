package org.udec.util.enumerations;

public enum MascotasEnum {

    // Nombre, Tipo, rutaImagen
    GATO( "Gato", TiposEnum.COMUN, 80,"/mascotas/gato.png", "/mascotas/gato.png", "/sonidos/gato.wav", "GatoFactory"),
    PERRO( "Perro", TiposEnum.COMUN, 100,"/mascotas/perro.png", "/perro.png", "/sonidos/rand.wav", "PerroFactory"),
    HAMSTER( "Hamster", TiposEnum.ROEDOR, 150,"/mascotas/hamster.png", "/mascotas/hamster.png", "/sonidos/hamster.wav", "HamsterFactory"),
    RATON( "Ratón", TiposEnum.ROEDOR, 120,"/mascotas/raton.png", "/mascotas/raton.png", "/sonidos/raton.wav", "RatonFactory"),
    LORO( "Loro", TiposEnum.VOLADOR, 140,"/mascotas/loro.png", "/mascotas/loro.png", "/sonidos/loro.wav", "LoroFactory"),
    GOLONDRINA( "Golondrina", TiposEnum.VOLADOR, 130,"/mascotas/golondrina.png", "/mascotas/golondrina.png", "/sonidos/golondrina.wav", "GolondrinaFactory"),
    PEZDORADO( "Pez dorado", TiposEnum.ACUATICO, 100,"/mascotas/pezdorado.png", "/mascotas/pezdorado.png", "/sonidos/pez.wav", "PezDoradoFactory"),
    PEZPAYASO( "Pez payaso", TiposEnum.ACUATICO, 90,"/mascotas/pezpayaso.png", "/mascotas/pezPayaso.png", "/sonidos/pez.wav", "PezPayasoFactory");

    private final String nombre;
    private final TiposEnum tipo;
    private final int precioVenta;
    private final String rutaImagen;
    private final String rutaImagenJuego;
    private final String rutaSonido;
    private final String nombreFactory;

    MascotasEnum(String nombre, TiposEnum tipo, int precioVenta, String rutaImagen, String rutaImagenJuego, String rutaSonido, String nombreFactory) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioVenta = precioVenta;
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

    public int getPrecioVenta() {
        return precioVenta;
    }
}
