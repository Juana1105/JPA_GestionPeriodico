package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import gestionPeriodistas.Articulo;
import gestionPeriodistas.Periodista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class GestorPeriodico {
	
	private EntityManager em; //Creamos el EntityManager 
	
	public GestorPeriodico() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("gestorPeriodico");
		em=factory.createEntityManager();
	}
	
	public Periodista leerPeriodista(String dni) {
		String jpql="SELECT p FROM Periodista p where p.dni='"+dni+"'";
		Query query=em.createQuery(jpql);
		List<Periodista> periodistas = query.getResultList();
		return periodistas.get(0);//devuelve el elemento 0 de la lista Periodistas
		/*FORMA 2
		 * Periodista p = (Periodista)query.getSingleResult();
		 * return p*/
	}
	public Periodista leerPeriodistaV2(String dni) {
		
		Periodista p = em.find(Periodista.class, dni);	
		return p;
	}
	
	
	
	
	public boolean altaPeriodista(Periodista p) {
		boolean valido=true;
		try {
			EntityTransaction tran=em.getTransaction();
			tran.begin();
			em.persist(p);
			tran.commit();
		}catch(Exception ex) {
			valido=false;
			System.out.println(ex.toString());
		}
	
		return valido;
	}
	public boolean bajaPeriodista(String dni) {
		String jpql="DELETE FROM Periodista p WHERE p.dni='"+dni+"'";
		Query query=em.createQuery(jpql);
		EntityTransaction tran=em.getTransaction();
		tran.begin();
		int valores=query.executeUpdate();
		tran.commit();
		return valores==1?true:false;
	}
	
	public boolean nuevoArticulo(Articulo a) {

		boolean valido=true;
		try {
			EntityTransaction tran=em.getTransaction();
			tran.begin();
			em.persist(a);
			tran.commit();
		}catch(Exception ex) {
			valido=false;
			System.out.println(ex.toString());
		}
	
		return valido;
	}

	public List<Articulo> articulosXPeriodista(String dni) {
	    List<Periodista> periodistas = new ArrayList<>();
	    List<Articulo> articulos=new ArrayList<>();
	    Periodista p=null;
	    
	    String jpql= "SELECT p FROM Periodista p  WHERE p.dni ='"+dni+"'";
	    Query query= em.createQuery(jpql);
	    periodistas = query.getResultList();
	    if(periodistas.size()==1) { //tiene q devolver 1 periodista solamente
	    	p=periodistas.get(0);
	    	articulos=p.getArticulos();
	    }
	   return articulos; //HACER COMPARATORS
	}

	public List<Articulo> articulosXAnyo(int anyo) {
	    List<Articulo> articulos=new ArrayList<>();
	    String jpql= "SELECT a FROM Articulo a WHERE a.anyoPublicacion= :anyo ORDER BY a.periodista.nombre, a.titulo";
	    Query query= em.createQuery(jpql);
	    query.setParameter("anyo", anyo);
	    articulos= query.getResultList();
	    return articulos;
	}

}
