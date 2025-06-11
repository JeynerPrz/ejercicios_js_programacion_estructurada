package Controlador;

import DAO.DAOCompra;
import DAO.DAODetalleCompra;
import Modelo.Compra;
import Modelo.DetalleCompra;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CompraController {

    private final DAOCompra daoCompra;
    private final DAODetalleCompra daoDetalleCompra;

    public CompraController() {
        this.daoCompra = new DAOCompra();
        this.daoDetalleCompra = new DAODetalleCompra();
    }

    // Método para crear una nueva compra con sus detalles
    public void crearCompra(int idProveedor, Date fechaCompra, List<DetalleCompra> detalles) {
        try {
            Compra compra = new Compra();
            compra.setIdProveedor(idProveedor);
            compra.setFechaCompra(fechaCompra);
            int idCompra = daoCompra.crearCompra(compra);

            if (idCompra == -1) {
                throw new SQLException("No se pudo obtener el ID de la compra.");
            }

            JOptionPane.showMessageDialog(null, "Compra creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para obtener todas las compras
    public List<Compra> obtenerTodasCompras() {
        try {
            return daoCompra.leerTodasCompras();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer las compras: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Método para actualizar una compra existente
    public void actualizarCompra(int idCompra, int idProveedor, Date fechaCompra) {
        try {
            Compra compra = new Compra();
            compra.setIdCompra(idCompra);
            compra.setIdProveedor(idProveedor);
            compra.setFechaCompra(fechaCompra);
            daoCompra.actualizarCompra(compra);
            JOptionPane.showMessageDialog(null, "Compra actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para eliminar una compra
    public void eliminarCompra(int idCompra) {
        try {
            daoCompra.eliminarCompra(idCompra);
            JOptionPane.showMessageDialog(null, "Compra eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        CompraController controlador = new CompraController();

        // Crear una lista de detalles de compra
        List<DetalleCompra> detalles = new ArrayList<>();
        DetalleCompra detalle1 = new DetalleCompra();
        detalle1.setIdProducto(4);
        detalle1.setCantidadCom(11);
        detalle1.setPrecioCom(51.51f);
        detalles.add(detalle1);

        // Crear una compra con detalles
        controlador.crearCompra(1, new Date(System.currentTimeMillis()), detalles);

        // Leer todas las compras
        List<Compra> compras = controlador.obtenerTodasCompras();
        if (compras != null) {
            System.out.println("Lista de compras:");
            for (Compra c : compras) {
                System.out.println("ID: " + c.getIdCompra()
                        + ", Proveedor: " + c.getIdProveedor()
                        + ", Fecha: " + c.getFechaCompra());
            }
        }

        // Actualizar una compra (suponiendo que ID 1 existe)
        controlador.actualizarCompra(1, 2, new Date(System.currentTimeMillis()));

        // Eliminar una compra
        controlador.eliminarCompra(1);
    }

}
