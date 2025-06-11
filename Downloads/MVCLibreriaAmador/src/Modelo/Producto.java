package Modelo;

/**
 *
 * @author Usuario
 */
public class Producto {
 private int idProducto;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private float precioComp;
    private float precioVent;

    // Getters
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecioComp() {
        return precioComp;
    }

    public float getPrecioVent() {
        return precioVent;
    }

    // Setters
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioComp(float precioComp) {
        this.precioComp = precioComp;
    }

    public void setPrecioVent(float precioVent) {
        this.precioVent = precioVent;
    }
}