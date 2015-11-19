package sysexp.modele;

import java.util.HashMap;

public class RegleAvecPremisse implements RegleAbstraite{
	final long numero;
	
	public RegleAvecPremisse(long numero){
		this.numero = numero;
	}
	
	@Override
	public boolean iterer(HashMap<String, FaitAbstrait> BaseDeFait) {
		// TODO Auto-generated method stub
		return false;
	}

}
