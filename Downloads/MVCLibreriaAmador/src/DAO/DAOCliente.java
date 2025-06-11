package DAO;

import Modelo.Cliente;
import Util.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOCliente {

    public void crearCliente(Cliente cliente) throws SQLException {
        if (cliente.getContacto() != null && cliente.getContacto().length() > 20) {
            throw new SQLException("El contacto no puede exceder los 20 caracteres.");
        }
        // Agrega esta línea para depurar los valores
        System.out.println("Valores a insertar: " + cliente.getPrimerNombre() + ", "
                + cliente.getSegundoNombre() + ", " + cliente.getPrimerApellido() + ", "
                + cliente.getSegundoApellido() + ", " + cliente.getCedula() + ", "
                + cliente.getContacto());
        try (Connection c = ConexionDB.getConnection(); CallableStatement cs = c.prepareCall("{CALL Crear_Cliente(?, ?, ?, ?, ?, ?)}")) {
            cs.setString(1, cliente.getPrimerNombre());
            cs.setString(2, cliente.getSegundoNombre());
            cs.setString(3, cliente.getPrimerApellido());
            cs.setString(4, cliente.getSegundoApellido());
            cs.setString(5, cliente.getCedula());
            cs.setString(6, cliente.getContacto());
            cs.executeUpdate();
        }
    }

    public List<Cliente> leerTodosClientes() throws SQLException {
        String sql = "SELECT * FROM Clientes";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_Cliente"));
                cliente.setPrimerNombre(rs.getString("Primer_Nombre"));
                cliente.setSegundoNombre(rs.getString("Segundo_Nombre"));
                cliente.setPrimerApellido(rs.getString("Primer_Apellido"));
                cliente.setSegundoApellido(rs.getString("Segundo_Apellido"));
                cliente.setCedula(rs.getString("Cedula"));
                cliente.setContacto(rs.getString("Contacto"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        if (cliente.getContacto() != null && cliente.getContacto().length() > 20) {
            throw new SQLException("El contacto no puede exceder los 20 caracteres.");
        }
        try (Connection c = ConexionDB.getConnection(); CallableStatement cs = c.prepareCall("{CALL ActualizarCliente(?, ?, ?, ?, ?, ?, ?)}")) {
            cs.setInt(1, cliente.getIdCliente());
            cs.setString(2, cliente.getPrimerNombre());
            cs.setString(3, cliente.getSegundoNombre());
            cs.setString(4, cliente.getPrimerApellido());
            cs.setString(5, cliente.getSegundoApellido());
            cs.setString(6, cliente.getCedula());
            cs.setString(7, cliente.getContacto());
            cs.executeUpdate();
        }
    }

    public boolean tieneVentas(String cedula) throws SQLException {
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM ventas WHERE id_cliente = ?")) {
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    public boolean tieneVentas(int idCliente) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Ventas WHERE ID_Cliente = ?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }

    public Cliente buscarPorCedula(String cedula) throws SQLException {
        Cliente cliente = null;
        String sql = "SELECT * FROM Clientes WHERE Cedula = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_Cliente"));
                cliente.setPrimerNombre(rs.getString("Primer_Nombre"));
                cliente.setSegundoNombre(rs.getString("Segundo_Nombre"));
                cliente.setPrimerApellido(rs.getString("Primer_Apellido"));
                cliente.setSegundoApellido(rs.getString("Segundo_Apellido"));
                cliente.setCedula(rs.getString("Cedula"));
                cliente.setContacto(rs.getString("Contacto"));
            }
        }

        return cliente;
    }

    public void eliminarClientePorId(int idCliente) throws SQLException {
        try (Connection c = ConexionDB.getConnection(); CallableStatement cs = c.prepareCall("{CALL Eliminar_Cliente(?)}")) {
            cs.setInt(1, idCliente);
            cs.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try {
            DAOCliente dao = new DAOCliente();

            // Actualizar un cliente
            Cliente cliente = new Cliente();
            cliente.setIdCliente(4); // ID existente
            cliente.setPrimerNombre("Juan");
            cliente.setSegundoNombre("Carlos");
            cliente.setPrimerApellido("Pérez");
            cliente.setSegundoApellido("Gómez");
            cliente.setCedula("12345678");
            cliente.setContacto("12345678");

            dao.actualizarCliente(cliente);

            System.out.println("Cliente actualizado.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
