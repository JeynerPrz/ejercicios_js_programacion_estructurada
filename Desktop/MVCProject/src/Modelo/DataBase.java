package Modelo;

import java.sql.*;
import java.util.*;



public class DataBase {
  // cadena de conexion de la BD publicación en MYSQL
  private final String URL="jdbc:mysql://localhost:3306/publicacion2";
  private final String user="root";
  private final String password="123456789";

  private Connection conexion;  //para conexion con la BD
  
  public DataBase(){  //contructor de la clase
      try {
          //Usando Driver conector y cadena de conexion para conectar BD 
           conexion=DriverManager.getConnection(URL, user, password);
           System.out.println("Conexion Establecida");
           
      }catch(SQLException e){
           System.out.println("Error Conexion");
           e.printStackTrace();
      }     
     }
 
   public int Actualizar(String consulta){
       try{ //para manejar errores al realizar la conexion y transaccion en BD
         Statement st=conexion.createStatement();
         return st.executeUpdate(consulta);
      }catch(SQLException e){
          e.printStackTrace();
      }
       return 0;
       }
   private List OrganizarDatos(ResultSet rs){ 
    List filas=new ArrayList();
    try{
        
        
        int numColumnas=rs.getMetaData().getColumnCount();
        while(rs.next()){//recorre cada registro de la tabla
            Map<String, Object > renglon=new HashMap();
            for(int i=1; i<=numColumnas; i++){         
            
                
                //se obtiene nombre de campo en la BD
                String nombreCampo=rs.getMetaData().getColumnName(i);
                Object valor=rs.getObject(nombreCampo);
                //por cada campo,se obtieme el nombre y el valor de el mismo
                renglon.put(nombreCampo,valor);           
            }
            filas.add(renglon); //se arregla el arreglo cada registro  
    }
    
    }catch(SQLException e){
        e.printStackTrace();
    }
   return filas;
   }
  public List Listar(String consulta){
      ResultSet rs=null;
      List resultado=new ArrayList();
     
      try{
          
          Statement st=conexion.createStatement();
          rs=st.executeQuery(consulta);
          resultado=OrganizarDatos(rs);
            
      }catch(SQLException e){
          System.out.println("no se realizo la consulta");
          e.printStackTrace();
      }
      return resultado;
  }
  
  public void cerrarConexion(){
      try{
          conexion.close();
      }catch(SQLException e){
          e.printStackTrace(); 
      }
              
  }
   
}
 
  

