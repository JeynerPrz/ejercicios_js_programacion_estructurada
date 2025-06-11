package Modelo;
import java.sql.Time;
import java.util.*;
/**
 *
 * @author Usuario
 */

public class DAORevista {

    
    public Revista Insertar(String titulo, int ayo, String issn,
            float precio, java.sql.Time Horaventa) {
        String transaccion = "INSERT INTO Revista (titulo,ayo,issn,precio,Horaventa) VALUES ('"
                + titulo + "','"
                + ayo + "','"
                + issn + "','"
                + precio + "','"
                + Horaventa + "')";
        //Llama al metodo Actualizar ubicado en DateBase.java
        if (new DataBase() .Actualizar(transaccion)> 0){
            return new Revista(titulo, ayo, issn,precio, Horaventa);
        }
       return null;  
    }
    //Metodo para Actualizar un registro en la BD
    public int Actualizar(int numero, String titulo,int ayo, String issn,
            float precio, Time Horaventa){
        
        
            String transaccion = "UPDATE Revista SET titulo='"
                    + titulo + "', ayo='"
                    + ayo + "', issn= '"
                    + issn + "', precio='"
                    + precio + "', Horaventa='"
                    + Horaventa + "'WHERE numero="
                    + numero;
            
            return new DataBase().Actualizar(transaccion);
             
    }
    // Metodos para seleccionar todos los registro de la tabla
    public List ObtenerDatos() {
        String transaccion = "SELECT * FROM Revista";
        //Llama a metodo Listar de DataBase.java
        List<Map> registros = new DataBase().Listar(transaccion);
        List<Revista> revistas = new ArrayList();// Arreglo de autores
        //Ciclo que recorre cada registro y los agrega al arreglo autores
        for (Map registro : registros) {
            Revista aut = new Revista((int) registro.get("numero"),
                    (String) registro.get("titulo"),
                    (int) registro.get("ayo"),
                    (String) registro.get("issn"),
                    (float) registro.get("precio"),
                   (java.sql.Time) registro.get("Horaventa"));
              revistas.add(aut);
        }
    return revistas;
    }
     public int Eliminar(int num){
        String transaccion = "DELETE FROM Revista WHERE numero='"+ num +"'";
        
        return new DataBase().Actualizar(transaccion);
    }
}

