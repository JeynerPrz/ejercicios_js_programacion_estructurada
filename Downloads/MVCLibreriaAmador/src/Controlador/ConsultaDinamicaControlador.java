package Controlador;

import DAO.ConsultaDinamicaDAO;
import Util.ConsultaGemini;
import java.util.List;
import javax.swing.JOptionPane;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;

/**
 *
 * @author Usuario
 */
public class ConsultaDinamicaControlador {

    private final ConsultaDinamicaDAO consultaDAO;
    private final ConsultaGemini consultaGemini;

    public ConsultaDinamicaControlador() {
        this.consultaDAO = new ConsultaDinamicaDAO();
        this.consultaGemini = new ConsultaGemini();
    }

    public List<Object[]> ejecutarConsultaNatural(String consultaNatural) {

        try {
            // Validar conexión a Internet
            java.net.URL url = new java.net.URL("https://www.google.com");
            java.net.URLConnection connection = url.openConnection();
            connection.setConnectTimeout(1000); // 1 segundo de tiempo de espera
            connection.connect();
        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(null, "No se puede realizar la consulta: No hay conexión a Internet.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            String esquemaBD = consultaGemini.obtenerEsquemaBD();
            String sql = consultaGemini.generarConsultaSQL(consultaNatural, esquemaBD);

            if (!consultaGemini.esConsultaSegura(sql)) {
                throw new Exception("Consulta no permitida: solo se permiten consultas SELECT.");
            }

            return consultaDAO.ejecutarConsulta(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public String[] obtenerNombresColumnas(String consultaNatural) {

        try {
            // Validar conexión a Internet
            java.net.URL url = new java.net.URL("https://www.google.com");
            java.net.URLConnection connection = url.openConnection();
            connection.setConnectTimeout(1000); // 1 segundo de tiempo de espera
            connection.connect();
        } catch (java.io.IOException e) {
            //JOptionPane.showMessageDialog(null, "No se puede obtener columnas: No hay conexión a Internet.", "Error", JOptionPane.ERROR_MESSAGE);
            return new String[]{};
        }

        try {
            String esquemaBD = consultaGemini.obtenerEsquemaBD();
            String sql = consultaGemini.generarConsultaSQL(consultaNatural, esquemaBD);

            if (!consultaGemini.esConsultaSegura(sql)) {
                throw new Exception("Consulta no permitida: solo se permiten consultas SELECT.");
            }

            return consultaDAO.obtenerNombresColumnas(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener columnas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new String[]{};
        }
    }

}
