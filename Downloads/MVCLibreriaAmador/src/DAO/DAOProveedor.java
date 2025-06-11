package DAO;
import Modelo.Proveedor;
import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Usuario
 */
public class DAOProveedor {
    
    public void crearProveedor(Proveedor proveedor) throws SQLException {
        String sql = """
        INSERT INTO Proveedores (
            Primer_Nombre, 
            Segundo_Nombre, 
            Primer_Apellido, 
            Segundo_Apellido, 
            Contacto, 
            Correo
        ) VALUES (?, ?, ?, ?, ?, ?)""";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, proveedor.getPrimerNombre());
            stmt.setString(2, proveedor.getSegundoNombre());
            stmt.setString(3, proveedor.getPrimerApellido());
            stmt.setString(4, proveedor.getSegundoApellido());
            stmt.setString(5, proveedor.getContacto());
            stmt.setString(6, proveedor.getCorreo());
            stmt.executeUpdate();
        }
    }

    public List<Proveedor> leerTodosProveedores() throws SQLException {
        String sql = "SELECT * FROM Proveedores";
        List<Proveedor> proveedores = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("ID_Proveedor"));
                proveedor.setPrimerNombre(rs.getString("Primer_Nombre"));
                proveedor.setSegundoNombre(rs.getString("Segundo_Nombre"));
                proveedor.setPrimerApellido(rs.getString("Primer_Apellido"));
                proveedor.setSegundoApellido(rs.getString("Segundo_Apellido"));
                proveedor.setContacto(rs.getString("Contacto"));
                proveedor.setCorreo(rs.getString("Correo"));
                proveedores.add(proveedor);
            }
        }
        return proveedores;
    }

    public void actualizarProveedor(Proveedor proveedor) throws SQLException {
        String sql = """
        UPDATE Proveedores 
        SET Primer_Nombre = ?, Segundo_Nombre = ?, Primer_Apellido = ?, Segundo_Apellido = ?, Contacto = ?, Correo = ?
        WHERE ID_Proveedor = ?""";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, proveedor.getPrimerNombre());
            stmt.setString(2, proveedor.getSegundoNombre());
            stmt.setString(3, proveedor.getPrimerApellido());
            stmt.setString(4, proveedor.getSegundoApellido());
            stmt.setString(5, proveedor.getContacto());
            stmt.setString(6, proveedor.getCorreo());
            stmt.setInt(7, proveedor.getIdProveedor());
            stmt.executeUpdate();
        }
    }

    public void eliminarProveedor(int idProveedor) throws SQLException {
        String sql = "DELETE FROM Proveedores WHERE ID_Proveedor = ?";

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, idProveedor);
            stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try {
            DAOProveedor dao = new DAOProveedor();

            // Actualizar un proveedor
            Proveedor proveedor = new Proveedor();
            proveedor.setIdProveedor(1); // ID existente
            proveedor.setPrimerNombre("Carlos");
            proveedor.setSegundoNombre("Eduardo");
            proveedor.setPrimerApellido("Ramírez");
            proveedor.setSegundoApellido("López");
            proveedor.setContacto("87654321");
            proveedor.setCorreo("carlos@proveedor.com");

            dao.actualizarProveedor(proveedor);
            System.out.println("Proveedor actualizado.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
