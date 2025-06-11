package Controlador;

import Modelo.Proveedor;
import Util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedorController {

    // Método para obtener todos los proveedores
    public List<Proveedor> obtenerTodosProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores"; // Asegúrate de que el nombre de la tabla sea correcto

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("id_proveedor")); // Asegúrate de que el nombre de la columna sea correcto
                proveedor.setPrimerNombre(rs.getString("primer_nombre"));
                proveedor.setSegundoNombre(rs.getString("segundo_nombre"));
                proveedor.setPrimerApellido(rs.getString("primer_apellido"));
                proveedor.setSegundoApellido(rs.getString("segundo_apellido"));
                proveedor.setContacto(rs.getString("contacto"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedores.add(proveedor);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
        return proveedores;
    }

    // Método para crear un nuevo proveedor
    public void crearProveedor(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String contacto, String correo) {
        String sql = "INSERT INTO proveedores (primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, contacto, correo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, primerNombre);
            pstmt.setString(2, segundoNombre);
            pstmt.setString(3, primerApellido);
            pstmt.setString(4, segundoApellido);
            pstmt.setString(5, contacto);
            pstmt.setString(6, correo);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
    }

    // Método para actualizar un proveedor existente
    public void actualizarProveedor(int id, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String contacto, String correo) {
        String sql = "UPDATE proveedores SET primer_nombre = ?, segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ?, contacto = ?, correo = ? WHERE id_proveedor = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, primerNombre);
            pstmt.setString(2, segundoNombre);
            pstmt.setString(3, primerApellido);
            pstmt.setString(4, segundoApellido);
            pstmt.setString(5, contacto);
            pstmt.setString(6, correo);
            pstmt.setInt(7, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
    }

    // Método para eliminar un proveedor
    public void eliminarProveedor(int id) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
    }

    public static void main(String[] args) {
        ProveedorController controlador = new ProveedorController();

        // Crear un proveedor
        controlador.crearProveedor("Juan", "Carlos", "Pérez", "Gómez", "555-1234", "juan@proveedor.com");

        // Leer todos los proveedores
        List<Proveedor> proveedores = controlador.obtenerTodosProveedores();
        if (proveedores != null) {
            System.out.println("Lista de proveedores:");
            for (Proveedor p : proveedores) {
                System.out.println("ID: " + p.getIdProveedor()
                        + ", Nombre: " + p.getPrimerNombre() + " " + p.getPrimerApellido()
                        + ", Correo: " + p.getCorreo());
            }
        }

        // Actualizar un proveedor (suponiendo que ID 1 existe)
        controlador.actualizarProveedor(1, "Juan", "Carlos", "Pérez", "Martínez", "555-5678", "juan_nuevo@proveedor.com");

        // Eliminar un proveedor
        controlador.eliminarProveedor(1);
    }
}
