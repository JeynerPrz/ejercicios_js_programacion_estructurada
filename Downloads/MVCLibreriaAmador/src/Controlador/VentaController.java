package Controlador;

import DAO.DAOVenta;
import DAO.DAODetalleVenta;
import Modelo.Venta;
import Modelo.DetalleVenta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class VentaController {

    private final DAOVenta daoVenta;
    private final DAODetalleVenta daoDetalleVenta;

    public VentaController() {
        this.daoVenta = new DAOVenta();
        this.daoDetalleVenta = new DAODetalleVenta();
    }

    // Método para crear una nueva venta con sus detalles
    public void crearVenta(int idCliente, Date fechaVenta, List<DetalleVenta> detalles) {
        try {
            Venta venta = new Venta();
            venta.setIdCliente(idCliente);
            venta.setFechaVenta(fechaVenta);
            int idVenta = daoVenta.crearVenta(venta);

            if (idVenta == -1) {
                throw new SQLException("No se pudo obtener el ID de la venta.");
            }

            // Asignar el id_venta a cada detalle y guardarlos
            for (DetalleVenta detalle : detalles) {
                detalle.setIdVenta(idVenta);
                daoDetalleVenta.crearDetalleVenta(detalle);
            }

            JOptionPane.showMessageDialog(null, "Venta y detalles creados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para obtener todas las ventas
    public List<Venta> obtenerTodasVentas() {
        try {
            return daoVenta.leerTodasVentas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer las ventas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Método para actualizar una venta existente
    public void actualizarVenta(int idVenta, int idCliente, Date fechaVenta) {
        try {
            Venta venta = new Venta();
            venta.setIdVenta(idVenta);
            venta.setIdCliente(idCliente);
            venta.setFechaVenta(fechaVenta);
            daoVenta.actualizarVenta(venta);
            JOptionPane.showMessageDialog(null, "Venta actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para eliminar una venta
    public void eliminarVenta(int idVenta) {
        try {
            daoVenta.eliminarVenta(idVenta);
            JOptionPane.showMessageDialog(null, "Venta eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        VentaController controlador = new VentaController();

        // Crear una lista de detalles de venta
        List<DetalleVenta> detalles = new ArrayList<>();
        DetalleVenta detalle1 = new DetalleVenta();
        detalle1.setIdProducto(1);
        detalle1.setCantidadVen(11);
        detalle1.setPrecioVen(33.22f);
        detalles.add(detalle1);

        // Crear una venta con detalles
        controlador.crearVenta(1, new Date(), detalles);

        // Leer todas las ventas
        List<Venta> ventas = controlador.obtenerTodasVentas();
        if (ventas != null) {
            System.out.println("Lista de ventas:");
            for (Venta v : ventas) {
                System.out.println("ID: " + v.getIdVenta()
                        + ", Cliente: " + v.getIdCliente()
                        + ", Fecha: " + v.getFechaVenta());
            }
        }

        // Actualizar una venta (suponiendo que ID 1 existe)
        controlador.actualizarVenta(1, 5, new Date());

        // Eliminar una venta
        controlador.eliminarVenta(1);
    }

}
