package Controlador;

import DAO.DAOProducto;
import Modelo.Producto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ProductoController {

    private final DAOProducto daoProducto;

    public ProductoController() {
        this.daoProducto = new DAOProducto();
    }

    // Método para crear un nuevo producto
    public void crearProducto(String nombre, String descripcion, int cantidad,
            float precioCompra, float precioVenta) {
        try {
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setCantidad(cantidad);
            producto.setPrecioComp(precioCompra);
            producto.setPrecioVent(precioVenta);
            daoProducto.crearProducto(producto);
            JOptionPane.showMessageDialog(null, "Producto creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para obtener todos los productos
    public List<Producto> obtenerTodosProductos() {
        try {
            return daoProducto.leerTodosProductos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer los productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();  // ✅ devuelve una lista vacía en lugar de null
        }
    }

    // Método para actualizar un producto existente
    public void actualizarProducto(int idProducto, String nombre, String descripcion, int cantidad,
            float precioCompra, float precioVenta) {
        try {
            Producto producto = new Producto();
            producto.setIdProducto(idProducto);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setCantidad(cantidad);
            producto.setPrecioComp(precioCompra);
            producto.setPrecioVent(precioVenta);
            daoProducto.actualizarProducto(producto);
            JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para eliminar un producto
    public void eliminarProducto(int idProducto) {
        try {
            daoProducto.eliminarProducto(idProducto);
            JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        ProductoController controlador = new ProductoController();

        // Crear un producto
        controlador.crearProducto("Laptop", "Laptop de alta gama", 10, 900.50f, 1500.99f);

        // Leer todos los productos
        List<Producto> productos = controlador.obtenerTodosProductos();
        if (productos != null) {
            System.out.println("Lista de productos:");
            for (Producto p : productos) {
                System.out.println("ID: " + p.getIdProducto()
                        + ", Nombre: " + p.getNombre()
                        + ", Precio Venta: " + p.getPrecioVent());
            }
        }

        // Actualizar un producto (suponiendo que ID 1 existe)
        controlador.actualizarProducto(1, "Laptop Pro", "Laptop mejorada", 8, 950.00f, 1800.50f);

        // Eliminar un producto
        controlador.eliminarProducto(1);
    }

}
