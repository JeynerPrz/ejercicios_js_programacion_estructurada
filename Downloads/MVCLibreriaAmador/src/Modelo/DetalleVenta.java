package Modelo;

/**
 *
 * @author Usuario
 */
public class DetalleVenta {

    private int idDetalleVen;
    private int idVenta;
    private int idProducto;
    private float cantidadVen;
    private float precioVen;

    public DetalleVenta(int idDetalleVen, int idVenta, int idProducto, float cantidadVen, float precioVen) {
        this.idDetalleVen = idDetalleVen;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidadVen = cantidadVen;
        this.precioVen = precioVen;
    }

    public DetalleVenta() {
    }

    public int getIdDetalleVen() {
        return idDetalleVen;
    }

    public void setIdDetalleVen(int idDetalleVen) {
        this.idDetalleVen = idDetalleVen;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public float getCantidadVen() {
        return cantidadVen;
    }

    public void setCantidadVen(float cantidadVen) {
        this.cantidadVen = cantidadVen;
    }

    public float getPrecioVen() {
        return precioVen;
    }

    public void setPrecioVen(float precioVen) {
        this.precioVen = precioVen;
    }

    public void setIdDetalle(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setCantidad(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setPrecio(double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
