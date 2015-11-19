package sysexp.modele;

import java.util.HashMap;

public class RegleSansPremisse implements RegleAbstraite{
	final long numero;
	
	public RegleSansPremisse(long numero){
		this.numero = numero;
	}
	
	@Override
	public boolean iterer(HashMap<String, FaitAbstrait> BaseDeFait) {
		// TODO Auto-generated method stub
		return false;
	}

}
