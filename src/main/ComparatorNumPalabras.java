package main;

import java.util.Comparator;

import gestionPeriodistas.Articulo;

public class ComparatorNumPalabras implements Comparator<Articulo>{

	@Override
	public int compare(Articulo a1, Articulo a2) {
		// TODO Auto-generated method stub
		return a1.getNumeroPalabras()-a2.getNumeroPalabras();
	}

}
