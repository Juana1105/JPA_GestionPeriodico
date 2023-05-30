package gestionPeriodistas;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Articulo implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String titulo;
    private int anyoPublicacion;
    private int numeroPalabras;
    @ManyToOne
    private Periodista periodista;
    
    

	


	public Articulo(String titulo, int anyoPublicacion, int numeroPalabras, Periodista periodista) {
		
		this.titulo = titulo;
		this.anyoPublicacion = anyoPublicacion;
		this.numeroPalabras = numeroPalabras;
		this.periodista = periodista;
	}






	public Articulo(String titulo, int anyoPublicacion, int numeroPalabras) {
		this.titulo = titulo;
		this.anyoPublicacion = anyoPublicacion;
		this.numeroPalabras = numeroPalabras;
	}


	public Articulo() {
	}
	


	@Override
	public String toString() {
		return "Articulo [id=" + id + ", titulo=" + titulo + ", anyoPublicacion=" + anyoPublicacion
				+ ", numeroPalabras=" + numeroPalabras + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getAnyoPublicacion() {
		return anyoPublicacion;
	}


	public void setAnyoPublicacion(int anyoPublicacion) {
		this.anyoPublicacion = anyoPublicacion;
	}


	public int getNumeroPalabras() {
		return numeroPalabras;
	}


	public void setNumeroPalabras(int numeroPalabras) {
		this.numeroPalabras = numeroPalabras;
	}

	public Periodista getPeriodista() {
		return periodista;
	}


	public void setPeriodista(Periodista periodista) {
		this.periodista = periodista;
	}
    
}