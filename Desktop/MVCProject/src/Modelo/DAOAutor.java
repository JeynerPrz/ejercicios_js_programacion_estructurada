package Modelo;

import java.util.*;
/**
 *
 * @author Usuario
 */

public class DAOAutor {
    //Metodos para insertar datos en la BD
    public Autor Insertar(String cedula, String nombres, String apellidos,
            String email, java.sql.Date FechaNac) {
        String transaccion = "INSERT INTO Autor (nombres,apellidos,email,cedula,fechaNac) VALUES ('"
                + nombres + "','"
                + apellidos + "','"
                + email + "','"
                + cedula + "','"
                + FechaNac + "')";
        //Llama al metodo Actualizar ubicado en DateBase.java
        if (new DataBase() .Actualizar(transaccion)> 0){
            return new Autor(cedula, nombres, apellidos, email, FechaNac);
        }
       return null;  
    }
  //Metodo para Actualizar un registro en la BD
    public int Actualizar(int id, String nombres, String Apellidos,
            String email, String cedula, java.sql.Date FechaNac){
        
        
            String transaccion = "UPDATE Autor SET nombres='"
                    + nombres + "', apellidos='"
                    + Apellidos + "', email= '"
                    + email + "', fechaNac='"
                    + FechaNac + "', cedula='"
                    + cedula + "' WHERE id_autor="
                    + id;
            
            return new DataBase().Actualizar(transaccion);
             
    }
   // Metodos para seleccionar todos los registro de la tabla
    public List ObtenerDatos() {
        String transaccion = "SELECT * FROM Autor";
        //Llama a metodo Listar de DataBase.java
        List<Map> registros = new DataBase().Listar(transaccion);
        List<Autor> autores = new ArrayList();// Arreglo de autores
        //Ciclo que recorre cada registro y los agrega al arreglo autores
        for (Map registro : registros) {
            Autor aut = new Autor((int) registro.get("id_autor"),
                    (String) registro.get("nombres"),
                    (String) registro.get("apellidos"),
                    (String) registro.get("email"),
                    (String) registro.get("cedula"),
                   (java.sql.Date) registro.get("fechaNac"));
              autores.add(aut);
        }
    return autores;
    
    }
    public int Eliminar(int id){
        String transaccion = "DELETE FROM Autor WHERE id_autor='"+ id +"'";
        
        return new DataBase().Actualizar(transaccion);
    }
    }

   
  
