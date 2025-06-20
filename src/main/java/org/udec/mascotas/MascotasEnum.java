package org.udec.mascotas;

import org.udec.TiposEnum;

public enum MascotasEnum {

    // Nombre, Tipo, rutaImagen
    GATO( "Gato", TiposEnum.COMUN, "gato.png", "gato.png"),
    PERRO( "Perro", TiposEnum.COMUN, "perro.png", "perro.png"),
    HAMSTER( "Hamster", TiposEnum.ROEDOR, "hamster.png", "hamster.png"),
    RATON( "Ratón", TiposEnum.ROEDOR, "raton.png", "raton.png"),
    LORO( "Loro", TiposEnum.VOLADOR, "loro.png", "loro.png"),
    GOLONDRINA( "Golondrina", TiposEnum.VOLADOR, "golondrina.png", "golondrina.png"),
    PEZDORADO( "Pez dorado", TiposEnum.ACUATICO, "pezDorada.png", "pezDorada.png"),
    PEZPAYASO( "Pez payaso", TiposEnum.ACUATICO, "pezPayaso.png", "pezPayaso.png");

    private String nombre;
    private TiposEnum tipo;
    private String rutaImagen;
    private String rutaImagenJuego;

    MascotasEnum(String nombre, TiposEnum tipo, String rutaImagen, String rutaImagenJuego){
        this.nombre = nombre;
        this.tipo = tipo;
        this.rutaImagen = rutaImagen;
        this.rutaImagenJuego = rutaImagenJuego;
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

    Mascota crearMascota(){
        return switch (this) {
            case PERRO -> new Perro();
            case GATO -> new Gato();
            case LORO -> new Loro();
            case GOLONDRINA -> new Golondrina();
            case HAMSTER -> new Hamster();
            case RATON -> new Raton();
            case PEZDORADO -> new PezDorado();
            case PEZPAYASO -> new PezPayaso();
        };
    }

}
