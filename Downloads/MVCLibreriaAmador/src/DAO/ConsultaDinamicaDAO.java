package DAO;

import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Usuario
 */
public class ConsultaDinamicaDAO {
    
      public List<Object[]> ejecutarConsulta(String sql) throws Exception {
        List<Object[]> resultados = new ArrayList<>();
        try (Connection c = ConexionDB.getConnection();
             PreparedStatement stmt = c.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                resultados.add(fila);
            }
            return resultados;
        }
    }
    
    public String[] obtenerNombresColumnas(String sql) throws Exception {
        try (Connection c = ConexionDB.getConnection();
             PreparedStatement stmt = c.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnas = metaData.getColumnCount();
            String[] nombreColumnas = new String[columnas];
            for (int i = 0; i < columnas; i++) {
                nombreColumnas[i] = metaData.getColumnName(i + 1);
            }
            return nombreColumnas;
        }
    }
    
    
    
}
