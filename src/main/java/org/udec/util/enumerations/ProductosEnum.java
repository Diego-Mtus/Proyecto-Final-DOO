package org.udec.util.enumerations;

public interface ProductosEnum {

    String getNombre();
    String getRutaImagen();
    int getPrecio();
    MascotasEnum[] getParaQueMascota();
    int getInventario();
    void setInventario(int inventario);
}
