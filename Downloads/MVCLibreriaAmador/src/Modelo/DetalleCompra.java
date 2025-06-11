
package Modelo;

/**
 *
 * @author Usuario
 */
public class DetalleCompra {
       private int idDetalleCom;
    private int idCompra;
    private int idProducto;
    private float cantidadCom;
    private float precioCom;

    // Constructor vacío
    public DetalleCompra() {}

    // Constructor completo
    public DetalleCompra(int idDetalleCom, int idCompra, int idProducto, float cantidadCom, float precioCom) {
        this.idDetalleCom = idDetalleCom;
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.cantidadCom = cantidadCom;
        this.precioCom = precioCom;
    }

    // Getters y Setters
    public int getIdDetalleCom() {
        return idDetalleCom;
    }

    public void setIdDetalleCom(int idDetalleCom) {
        this.idDetalleCom = idDetalleCom;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public float getCantidadCom() {
        return cantidadCom;
    }

    public void setCantidadCom(float cantidadCom) {
        this.cantidadCom = cantidadCom;
    }

    public float getPrecioCom() {
        return precioCom;
    }

    public void setPrecioCom(float precioCom) {
        this.precioCom = precioCom;
    }
}