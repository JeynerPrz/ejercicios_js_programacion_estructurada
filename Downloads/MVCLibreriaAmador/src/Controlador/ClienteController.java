package Controlador;

import DAO.DAOCliente;
import Modelo.Cliente;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteController {

    private final DAOCliente daoCliente;

    public ClienteController() {
        this.daoCliente = new DAOCliente();
        //this. = null;
    }

    // Ahora este método recibe un objeto Cliente
    public void crearCliente(Cliente cliente) {
        try {
            // Validaciones
            if (cliente.getContacto() != null && cliente.getContacto().length() > 20) {
                JOptionPane.showMessageDialog(null, "El contacto no puede exceder los 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cliente.getPrimerNombre() == null || cliente.getPrimerNombre().isEmpty() || cliente.getPrimerNombre().length() > 10) {
                JOptionPane.showMessageDialog(null, "El primer nombre debe tener entre 1 y 10 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cliente.getPrimerApellido() == null || cliente.getPrimerApellido().isEmpty() || cliente.getPrimerApellido().length() > 10) {
                JOptionPane.showMessageDialog(null, "El primer apellido debe tener entre 1 y 10 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            daoCliente.crearCliente(cliente);
            JOptionPane.showMessageDialog(null, "Cliente creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Cliente> obtenerTodosClientes() {
        try {
            return daoCliente.leerTodosClientes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer los clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // También actualizar recibe un objeto Cliente
    public void actualizarCliente(Cliente cliente) {
        try {
            if (cliente.getContacto() != null && cliente.getContacto().length() > 20) {
                JOptionPane.showMessageDialog(null, "El contacto no puede exceder los 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            daoCliente.actualizarCliente(cliente);
            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarCliente(int idCliente) {
        try {
            // Verifica si el cliente tiene ventas asociadas
            if (daoCliente.tieneVentas(idCliente)) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar el cliente porque tiene ventas registradas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Confirmación antes de eliminar
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar al cliente con ID " + idCliente + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                daoCliente.eliminarClientePorId(idCliente);
                JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Eliminación cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
