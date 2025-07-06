package org.udec.util.enumerations;

import org.udec.mascotas.Mascota;

import java.util.Arrays;

public enum MedicinasEnum implements ProductosEnum{

    MEDICINA_VOLADOR("Medicamento para aves", TiposEnum.VOLADOR.mascotasCompatibles().toArray(new MascotasEnum[0]) , "/mascotas/pezpayaso.png", 5),
    MEDICINA_COMUN("Medicamento para mascotas comúnes", TiposEnum.COMUN.mascotasCompatibles().toArray(new MascotasEnum[0]), "/mascotas/pezpayaso.png", 5),
    MEDICINA_ROEDOR("Medicamento para roedores", TiposEnum.ROEDOR.mascotasCompatibles().toArray(new MascotasEnum[0]), "/mascotas/pezpayaso.png", 5),
    MEDICINA_PECES("Medicamento para peces", TiposEnum.ACUATICO.mascotasCompatibles().toArray(new MascotasEnum[0]), "/mascotas/pezpayaso.png", 5),
    CURITA_HERIDAS("Curita para heridas", null, "/mascotas/pezpayaso.png", 2);

    private final String nombre;
    private final MascotasEnum[] paraQueMascota;
    private final String rutaImagen;
    private final int precio;
    private int inventario = 0;

    MedicinasEnum(String nombre, MascotasEnum[] paraQueMascota, String rutaImagen, int precio) {
        this.nombre = nombre;
        this.paraQueMascota = paraQueMascota;
        this.rutaImagen = rutaImagen;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public MascotasEnum[] getParaQueMascota() {
        return paraQueMascota;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public int getPrecio() {
        return precio;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public void curar(Mascota mascota){
        if(this == CURITA_HERIDAS){
            mascota.getEstado().setHerido(false);
            return;
        }
        else{
            if(Arrays.stream(paraQueMascota).anyMatch(m -> m.getNombre().equals(mascota.getNombreAnimal()))){
                mascota.getEstado().addSalud(20);
            } else{
                mascota.getEstado().setSalud(mascota.getEstado().verSalud() - 5);
            }
        }
    }
}
