package main;

import java.util.List;
import java.util.Scanner;

import gestionPeriodistas.Articulo;
import gestionPeriodistas.Periodista;

public class Main {

	public static void main(String[] args) {
		
		GestorPeriodico gestor=new GestorPeriodico();
		
		Scanner sc=new Scanner(System.in);
		int opcion=0;
		String dniPeriodista="",nombrePeriodista="",telefonoPeriodista="";
		String tituloArticulo="";
		int anyoPublicacion=0,numPalabras=0;
		do{
			System.out.println("\tMENU");
			System.out.println("1.Alta de un nuevo periodista");
			System.out.println("2.Baja de un periodista");
			System.out.println("3.Nuevo artículo");
			System.out.println("4.Mostrar artículos de un periodista");
			System.out.println("5.Mostrar todos los artículo de un año");
			System.out.println("6.Salir");
			opcion=sc.nextInt();
			sc.nextLine();
			switch(opcion) {
				
				case 1:
				altaPeriodista(gestor, sc);
					break;
				case 2:
				bajaPeriodista(gestor, sc);
					break;
				case 3:
				nuevoArticulo(gestor, sc);
					break;
				case 4:
				buscarArticulosXPeriodista(gestor, sc);
					break;
				case 5:
				buscarArticulosXAnyo(gestor, sc);
					break;
				case 6:
					break;
	
				default:
					System.out.println("Introduce un número del 1 al 6");
				
			}
		}while(opcion!=6);
	}

	public static void buscarArticulosXAnyo(GestorPeriodico gestor, Scanner sc) {
		int anyoPublicacion;
		System.out.println("BUSCAR ARTICULOS POR AÑO DE PUBLICACION");
		System.out.println("Introduce el año a buscar");
		anyoPublicacion=sc.nextInt();
		List<Articulo> articuloss=gestor.articulosXAnyo(anyoPublicacion);
		for(Articulo ar:articuloss) {
			System.out.println(ar);
		}
	}

	public static void buscarArticulosXPeriodista(GestorPeriodico gestor, Scanner sc) {
		String dniPeriodista;
		System.out.println("BUSCAR ARTICULOS POR PERIODISTA");
		System.out.println("Introduce el DNI del periodista");
		dniPeriodista=sc.nextLine();
		List<Articulo> articulos=gestor.articulosXPeriodista(dniPeriodista);
		ComparatorAnyo ordenarXAnyo=new ComparatorAnyo();
		ComparatorNumPalabras ordernarXNumPalabras=new ComparatorNumPalabras();
		articulos.sort(ordenarXAnyo.thenComparing(ordernarXNumPalabras));
		//ya tengo los articulos ordenados
		for(Articulo ar:articulos) {
			System.out.println(ar);
		}
	}

	public static void nuevoArticulo(GestorPeriodico gestor, Scanner sc) {
		String dniPeriodista;
		String tituloArticulo;
		int anyoPublicacion;
		int numPalabras;
		System.out.println("NUEVO ARTICULO");
		System.out.println("Introduce el titulo");
		tituloArticulo=sc.nextLine();
		System.out.println("Introduce el año de publicacion");
		anyoPublicacion=sc.nextInt();
		System.out.println("Introduce el nº de palabras");
		numPalabras=sc.nextInt();
		System.out.println("Introduce el DNI del periodista");
		dniPeriodista=sc.next();
		
		Articulo a=new Articulo(tituloArticulo,anyoPublicacion,numPalabras,gestor.leerPeriodistaV2(dniPeriodista));
		gestor.nuevoArticulo(a);
	}

	public static void bajaPeriodista(GestorPeriodico gestor, Scanner sc) {
		String dniPeriodista;
		System.out.println("BAJA DE PERIODISTA");
		System.out.println("Introduce el DNI:");
		dniPeriodista=sc.nextLine();
		boolean exito=gestor.bajaPeriodista(dniPeriodista);
		if(exito) {
			System.out.println("Periodista '"+dniPeriodista+"' eliminado.");
		}else {
			System.out.println("No existe ningun periodista con DNI:"+dniPeriodista);
		}
	}

	public static void altaPeriodista(GestorPeriodico gestor, Scanner sc) {
		String dniPeriodista;
		String nombrePeriodista;
		String telefonoPeriodista;
		System.out.println("ALTA DE PERIODISTA");
		System.out.println("Introduce el DNI:");
		dniPeriodista=sc.nextLine();
		System.out.println("Introduce el nombre:");
		nombrePeriodista=sc.nextLine();
		System.out.println("Introduce el telefono:");
		telefonoPeriodista=sc.nextLine();
		Periodista p=new Periodista(dniPeriodista,nombrePeriodista,telefonoPeriodista);
		gestor.altaPeriodista(p);
	}
}
