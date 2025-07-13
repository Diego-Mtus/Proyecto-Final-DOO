package org.udec.util.enumerations;

/**
 * Enumeración que define las diferentes mascotas disponibles en el juego.
 * Cada mascota tiene un nombre, tipo, precio de venta, ruta de imagen,
 * ruta de imagen para el juego, ruta de sonido y el nombre de la fábrica asociada.
 */
public enum MascotasEnum {

    // Nombre, Tipo, rutaImagen
    GATO( "Gato", TiposEnum.COMUN, 80,"/mascotas/gato.png", "/mascotas/gatoJuego.png", "/sonidos/gato.wav", "GatoFactory"),
    PERRO( "Perro", TiposEnum.COMUN, 100,"/mascotas/perro.png", "/mascotas/perroJuego.png", "/sonidos/perro.wav", "PerroFactory"),
    HAMSTER( "Hamster", TiposEnum.ROEDOR, 150,"/mascotas/hamster.png", "/mascotas/hamsterJuego.png", "/sonidos/hamster.wav", "HamsterFactory"),
    RATON( "Ratón", TiposEnum.ROEDOR, 120,"/mascotas/raton.png", "/mascotas/ratonJuego.png", "/sonidos/raton.wav", "RatonFactory"),
    LORO( "Loro", TiposEnum.VOLADOR, 140,"/mascotas/loro.png", "/mascotas/loroJuego.png", "/sonidos/pajaro.wav", "LoroFactory"),
    GOLONDRINA( "Golondrina", TiposEnum.VOLADOR, 130,"/mascotas/golondrina.png", "/mascotas/golondrinaJuego.png", "/sonidos/pajaro.wav", "GolondrinaFactory"),
    PEZDORADO( "Pez dorado", TiposEnum.ACUATICO, 100,"/mascotas/pezdorado.png", "/mascotas/pezdorado.png", "/sonidos/pez.wav", "PezDoradoFactory"),
    PEZPAYASO( "Pez payaso", TiposEnum.ACUATICO, 90,"/mascotas/pezpayaso.png", "/mascotas/pezpayaso.png", "/sonidos/pez.wav", "PezPayasoFactory");

    private final String nombre;
    private final TiposEnum tipo;
    private final int precioVenta;
    private final String rutaImagen;
    private final String rutaImagenJuego;
    private final String rutaSonido;
    private final String nombreFactory;

    /**
     * Constructor de la enumeración MascotasEnum.
     *
     * @param nombre Nombre de la mascota.
     * @param tipo Tipo de la mascota (COMUN, ROEDOR, VOLADOR, ACUATICO).
     * @param precioVenta Precio de venta de la mascota.
     * @param rutaImagen Ruta de la imagen de la mascota.
     * @param rutaImagenJuego Ruta de la imagen de la mascota en el juego.
     * @param rutaSonido Ruta del sonido asociado a la mascota.
     * @param nombreFactory Nombre de la fábrica asociada a esta mascota.
     */
    MascotasEnum(String nombre, TiposEnum tipo, int precioVenta, String rutaImagen, String rutaImagenJuego, String rutaSonido, String nombreFactory) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioVenta = precioVenta;
        this.rutaImagen = rutaImagen;
        this.rutaImagenJuego = rutaImagenJuego;
        this.rutaSonido = rutaSonido;
        this.nombreFactory = nombreFactory;
    }

    /**
     * Obtiene el nombre de la mascota.
     *
     * @return El nombre de la mascota.
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Obtiene el tipo de la mascota.
     *
     * @return El tipo de la mascota como un valor de la enumeración TiposEnum.
     */
    public TiposEnum getTipo(){
        return this.tipo;
    }

    /**
     * Obtiene la ruta de la imagen de la mascota.
     *
     * @return La ruta de la imagen de la mascota.
     */
    public String getRutaImagen(){
        return this.rutaImagen;
    }

    /**
     * Obtiene la ruta de la imagen de la mascota que se muestra en el juego.
     *
     * @return La ruta de la imagen de la mascota en el juego.
     */
    public String getRutaImagenJuego(){
        return this.rutaImagenJuego;
    }

    /**
     * Obtiene la ruta del sonido asociado a la mascota.
     *
     * @return La ruta del sonido de la mascota.
     */
    public String getRutaSonido() {
        return rutaSonido;
    }

    /**
     * Obtiene el nombre de la fábrica asociada a esta mascota.
     *
     * @return El nombre de la fábrica.
     */
    public String getNombreFactory() {
        return nombreFactory;
    }

    /**
     * Obtiene el precio de venta de la mascota.
     *
     * @return El precio de venta de la mascota.
     */
    public int getPrecioVenta() {
        return precioVenta;
    }
}
