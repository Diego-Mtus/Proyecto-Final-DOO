package org.udec.mascotas;

class MascotaFactory {
     public static Mascota crearMascota(MascotasEnum tipo) {
        return switch (tipo) {
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