package DAO;


import Modelo.Producto;
import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class DAOProducto {
   
    public void crearProducto(Producto producto) throws SQLException {
        String sql = """
        INSERT INTO Productos (
            Nombre, 
            Descripcion, 
            Cantidad, 
            Precio_Comp, 
            Precio_Vent
        ) VALUES (?, ?, ?, ?, ?)""";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setInt(3, producto.getCantidad());
            stmt.setFloat(4, producto.getPrecioComp());
            stmt.setFloat(5, producto.getPrecioVent());
            stmt.executeUpdate();
        }
    }

    public List<Producto> leerTodosProductos() throws SQLException {
        String sql = "SELECT * FROM Productos";
        List<Producto> productos = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection(); 
             PreparedStatement stmt = c.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("ID_Producto"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setCantidad(rs.getInt("Cantidad"));
                producto.setPrecioComp(rs.getFloat("Precio_Comp"));
                producto.setPrecioVent(rs.getFloat("Precio_Vent"));
                productos.add(producto);
            }
        }
        return productos;
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        String sql = """
        UPDATE Productos SET 
            Nombre = ?, 
            Descripcion = ?, 
            Cantidad = ?, 
            Precio_Comp = ?, 
            Precio_Vent = ?
        WHERE ID_Producto = ?""";

        try (Connection c = ConexionDB.getConnection(); 
             PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setInt(3, producto.getCantidad());
            stmt.setFloat(4, producto.getPrecioComp());
            stmt.setFloat(5, producto.getPrecioVent());
            stmt.setInt(6, producto.getIdProducto());
            stmt.executeUpdate();
        }
    }

    public void eliminarProducto(int idProducto) throws SQLException {
        String sql = "DELETE FROM Productos WHERE ID_Producto = ?";

        try (Connection c = ConexionDB.getConnection(); 
             PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try {
            DAOProducto dao = new DAOProducto();

            // Actualizar un producto
            Producto producto = new Producto();
            producto.setIdProducto(1); // ID existente
            producto.setNombre("Mouse Gamer");
            producto.setDescripcion("Mouse con luces RGB y 8 botones programables");
            producto.setCantidad(100);
            producto.setPrecioComp(15.0f);
            producto.setPrecioVent(25.0f);
            dao.actualizarProducto(producto);
            System.out.println("Producto actualizado.");

            // Eliminar un producto
            dao.eliminarProducto(2); // ID a eliminar
            System.out.println("Producto eliminado.");

            // Leer y mostrar todos los productos
            List<Producto> productos = dao.leerTodosProductos();
            System.out.println("Lista de productos:");
            for (Producto prod : productos) {
                System.out.println("ID: " + prod.getIdProducto()
                        + ", Nombre: " + prod.getNombre()
                        + ", Descripción: " + prod.getDescripcion()
                        + ", Cantidad: " + prod.getCantidad()
                        + ", Precio Compra: " + prod.getPrecioComp()
                        + ", Precio Venta: " + prod.getPrecioVent());
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    } 
    
}
