package org.udec.util.enumerations;

/**
 * Interfaz que define los métodos para los productos disponibles en la tienda.
 * Cada producto debe implementar estos métodos para proporcionar su información.
 */
public interface ProductosEnum {

    /**
     * Obtiene el nombre del producto.
     *
     * @return el nombre del producto.
     */
    String getNombre();
    /**
     * Obtiene la ruta de la imagen del producto.
     *
     * @return la ruta de la imagen del producto.
     */
    String getRutaImagen();

    /**
     * Obtiene el precio de compra del producto.
     *
     * @return el precio del producto.
     */
    int getPrecio();
    /**
     * Obtiene el tipo de mascota que se puede comprar con este producto.
     *
     * @return un arreglo de MascotasEnum que representa las mascotas disponibles.
     */
    MascotasEnum[] getParaQueMascota();
    /**
     * Obtiene la cantidad que posee el usuario de este producto.
     *
     * @return la cantidad de inventario.
     */
    int getInventario();
    /**
     * Establece la cantidad de inventario del producto.
     *
     * @param inventario la nueva cantidad de inventario.
     */
    void setInventario(int inventario);
}
