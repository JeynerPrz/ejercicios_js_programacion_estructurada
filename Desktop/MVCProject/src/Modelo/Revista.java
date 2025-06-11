package Modelo;
import java.sql.Time;
/**
 *
 * @author Usuario
 */

public class Revista {
    private int numero;
    private String titulo;
    private  int ayo;
    private String issn;
    private float precio;
    private Time Horaventa;
    
public Revista(int numero, String titulo, int ayo, String issn,
        float precio, Time Horaventa){
     this.numero = numero;
     this.titulo = titulo;
     this.ayo = ayo;
     this.issn = issn;
     this.precio = precio;
     this.Horaventa = Horaventa;
}
public Revista(String titulo, int ayo, String issn,
float precio, Time Horaventa){

     this.titulo = titulo;
     this.ayo = ayo;
     this.issn = issn;
     this.precio = precio;
     this.Horaventa = Horaventa;    
}

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAyo() {
        return ayo;
    }

    public void setAyo(int ayo) {
        this.ayo = ayo;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Time getHoraventa() {
        return Horaventa;
    }

    public void setHoraventa(Time Horaventa) {
        this.Horaventa = Horaventa;
    }
}