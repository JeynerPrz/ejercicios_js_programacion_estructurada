package Modelo;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Compra {

    private int idCompra;
    private Date fechaCompra;
    private int idProveedor;

    public Compra(int idCompra, Date fechaCompra, int idProveedor) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.idProveedor = idProveedor;
    }

    public Compra() {
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

}
