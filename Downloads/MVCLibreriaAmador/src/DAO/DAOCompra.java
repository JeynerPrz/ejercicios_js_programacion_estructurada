package DAO;

import Modelo.Compra;
import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Usuario
 */
public class DAOCompra {

    public int crearCompra(Compra compra) throws SQLException {
        String sql = """
    INSERT INTO Compras (
        ID_Proveedor, 
        Fecha_Compra
    ) VALUES (?, ?)""";

        int generatedId = -1;

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, compra.getIdProveedor());
            stmt.setDate(2, new java.sql.Date(compra.getFechaCompra().getTime()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        }
        return generatedId;
    }

    public List<Compra> leerTodasCompras() throws SQLException {
        String sql = "SELECT * FROM Compras";
        List<Compra> compras = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("ID_Compra"));
                compra.setIdProveedor(rs.getInt("ID_Proveedor"));
                compra.setFechaCompra(rs.getDate("Fecha_Compra"));
                compras.add(compra);
            }
        }
        return compras;
    }

    public void actualizarCompra(Compra compra) throws SQLException {
        String sql = "UPDATE Compras SET ID_Proveedor = ?, Fecha_Compra = ? WHERE ID_Compra = ?";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, compra.getIdProveedor());
            stmt.setDate(2, new java.sql.Date(compra.getFechaCompra().getTime()));
            stmt.setInt(3, compra.getIdCompra());
            stmt.executeUpdate();
        }
    }

    public void eliminarCompra(int idCompra) throws SQLException {
        String sql = "DELETE FROM Compras WHERE ID_Compra = ?";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idCompra);
            stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try {
            DAOCompra dao = new DAOCompra();

            // Actualizar una compra
            Compra compra = new Compra();
            compra.setIdCompra(3); // ID existente
            compra.setIdProveedor(2); // ID del proveedor
            compra.setFechaCompra(new java.util.Date());
            dao.actualizarCompra(compra);
            System.out.println("Compra actualizada.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
