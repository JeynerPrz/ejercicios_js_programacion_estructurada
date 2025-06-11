package DAO;

import Modelo.DetalleCompra;
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
public class DAODetalleCompra {
      public void crearDetalleCompra(DetalleCompra detalle) throws SQLException {
        String sql = """
        INSERT INTO Detalle_Compras (
            ID_Compra, 
            ID_Producto, 
            Cantidad_Com, 
            Precio_Com
        ) VALUES (?, ?, ?, ?)""";

        try (Connection c = ConexionDB.getConnection(); 
             PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, detalle.getIdCompra());
            stmt.setInt(2, detalle.getIdProducto());
            stmt.setFloat(3, detalle.getCantidadCom());
            stmt.setFloat(4, detalle.getPrecioCom());
            stmt.executeUpdate();
        }
    }

    public List<DetalleCompra> leerTodosDetallesCompra() throws SQLException {
        String sql = "SELECT * FROM Detalle_Compras";
        List<DetalleCompra> detalles = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection(); 
             PreparedStatement stmt = c.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DetalleCompra detalle = new DetalleCompra();
                detalle.setIdDetalleCom(rs.getInt("ID_Detalle_Com"));
                detalle.setIdCompra(rs.getInt("ID_Compra"));
                detalle.setIdProducto(rs.getInt("ID_Producto"));
                detalle.setCantidadCom(rs.getFloat("Cantidad_Com"));
                detalle.setPrecioCom(rs.getFloat("Precio_Com"));
                detalles.add(detalle);
            }
        }
        return detalles;
    }

    public void actualizarDetalleCompra(DetalleCompra detalle) throws SQLException {
        String sql = """
        UPDATE Detalle_Compras 
        SET ID_Compra = ?, ID_Producto = ?, Cantidad_Com = ?, Precio_Com = ? 
        WHERE ID_Detalle_Com = ?""";

        try (Connection c = ConexionDB.getConnection(); 
             PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, detalle.getIdCompra());
            stmt.setInt(2, detalle.getIdProducto());
            stmt.setFloat(3, detalle.getCantidadCom());
            stmt.setFloat(4, detalle.getPrecioCom());
            stmt.setInt(5, detalle.getIdDetalleCom());
            stmt.executeUpdate();
        }
    }

    public void eliminarDetalleCompra(int idDetalleCom) throws SQLException {
        String sql = "DELETE FROM Detalle_Compras WHERE ID_Detalle_Com = ?";

        try (Connection c = ConexionDB.getConnection(); 
             PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idDetalleCom);
            stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try {
            DAODetalleCompra dao = new DAODetalleCompra();

            // Actualizar un detalle de compra
            DetalleCompra detalle = new DetalleCompra();
            detalle.setIdDetalleCom(1); // ID existente
            detalle.setIdCompra(1);
            detalle.setIdProducto(2);
            detalle.setCantidadCom(5);
            detalle.setPrecioCom(100.0f);
            dao.actualizarDetalleCompra(detalle);
            System.out.println("Detalle de compra actualizado.");

            // Eliminar un detalle de compra
            dao.eliminarDetalleCompra(2); // ID a eliminar
            System.out.println("Detalle de compra eliminado.");

            // Leer y mostrar todos los detalles de compra
            List<DetalleCompra> detalles = dao.leerTodosDetallesCompra();
            System.out.println("Lista de detalles de compra:");
            for (DetalleCompra det : detalles) {
                System.out.println("ID: " + det.getIdDetalleCom()
                        + ", Compra ID: " + det.getIdCompra()
                        + ", Producto ID: " + det.getIdProducto()
                        + ", Cantidad: " + det.getCantidadCom()
                        + ", Precio: " + det.getPrecioCom());
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}