package Util;

/**
 *
 * @author Usuario
 */
import okhttp3.*;
import org.json.JSONObject;

public class ConsultaGemini {

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    private static final String API_KEY = "AIzaSyDeAdTOmv-wzntLuEsLuI2ySxUL-Xlhny4"; // Reemplaza con tu clave de API de Gemini
    
    
     public String generarConsultaSQL(String consultaNatural, String esquemaBD) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String prompt = """
            Genera una consulta SQL válida para una base de datos MySQL con las siguientes tablas y relaciones:
            %s
            Instrucciones:
            - Usa solo las columnas listadas en cada tabla.
            - Respeta el orden de los atributos de cada tabla según el esquemade la base de datos.
            - Asegúrate de que los JOINs sean correctos y utilicen las claves foráneas especificadas.
            - Si se solicita información de múltiples tablas, usa JOINs explícitos.
            - No generes subconsultas complejas ni funciones avanzadas a menos que sean explícitamente solicitadas.
            - Devuelve la consulta SQL en una sola línea, sin saltos de línea, comillas triples, ni formato adicional.
            Pregunta del usuario: "%s"
            """.formatted(esquemaBD, consultaNatural);

        JSONObject json = new JSONObject();
        json.put("contents", new JSONObject().put("parts", new JSONObject().put("text", prompt)));
        json.put("generationConfig", new JSONObject().put("response_mime_type", "text/plain"));

        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(API_URL + "?key=" + API_KEY)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Error en la solicitud a Gemini: " + response.code());
            }
            String respuesta = response.body().string();
            return new JSONObject(respuesta).getJSONArray("candidates")
                    .getJSONObject(0)
                    .getJSONObject("content")
                    .getJSONArray("parts")
                    .getJSONObject(0)
                    .getString("text");
        }
    }
     
     public String obtenerEsquemaBD() {
    return """
        Tabla: Clientes (
            ID_Cliente INT AUTO_INCREMENT PRIMARY KEY,
            Primer_Nombre VARCHAR(50),
            Segundo_Nombre VARCHAR(50),
            Primer_Apellido VARCHAR(50),
            Segundo_Apellido VARCHAR(50),
            Cedula VARCHAR(15),
            Contacto VARCHAR(50)
        )
        Tabla: Proveedores (
            ID_Proveedor INT AUTO_INCREMENT PRIMARY KEY,
            Primer_Nombre VARCHAR(50),
            Segundo_Nombre VARCHAR(50),
            Primer_Apellido VARCHAR(50),
            Segundo_Apellido VARCHAR(50),
            Contacto VARCHAR(50),
            Correo VARCHAR(50)
        )
        Tabla: Productos (
            ID_Producto INT AUTO_INCREMENT PRIMARY KEY,
            Nombre VARCHAR(100),
            Descripcion VARCHAR(255),
            Cantidad INT,
            Precio_Comp FLOAT,
            Precio_Vent FLOAT
        )
        Tabla: Ventas (
            ID_Venta INT AUTO_INCREMENT PRIMARY KEY,
            Fecha_Venta DATETIME,
            ID_Cliente INT
        )
        Tabla: Compras (
            ID_Compra INT AUTO_INCREMENT PRIMARY KEY,
            Fecha_Compra DATETIME,
            ID_Proveedor INT
        )
        Tabla: Detalle_Ventas (
            ID_Detalle_Ven INT AUTO_INCREMENT PRIMARY KEY,
            ID_Venta INT,
            ID_Producto INT,
            Cantidad_Ven INT,
            Precio_Ven FLOAT
        )
        Tabla: Detalle_Compras (
            ID_Detalle_Com INT AUTO_INCREMENT PRIMARY KEY,
            ID_Compra INT,
            ID_Producto INT,
            Cantidad_Com INT,
            Precio_Com FLOAT
        )
        Relaciones:
        - Ventas.ID_Cliente -> Clientes.ID_Cliente
        - Compras.ID_Proveedor -> Proveedores.ID_Proveedor
        - Detalle_Ventas.ID_Venta -> Ventas.ID_Venta (ON DELETE CASCADE)
        - Detalle_Ventas.ID_Producto -> Productos.ID_Producto
        - Detalle_Compras.ID_Compra -> Compras.ID_Compra (ON DELETE CASCADE)
        - Detalle_Compras.ID_Producto -> Productos.ID_Producto
        """;
}
    
      // Validación de consultas SQL
    public boolean esConsultaSegura(String sql) {
        String consulta = sql.toLowerCase().trim();
        return consulta.startsWith("select") &&
               !consulta.contains("drop") &&
               !consulta.contains("update") &&
               !consulta.contains("insert");
    }
    
}
