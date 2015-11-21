package sysexp.modele;

import java.util.HashMap;
import sysexp.modele.visitor.Forme;

public class RegleSansPremisse implements RegleAbstraite{
	protected final long numero;
	protected Forme conclusion;
	
	public RegleSansPremisse(long numero, Forme conclusion){
		this.numero = numero;
		this.conclusion = conclusion;
	}
	
	@Override
	public boolean iterer(HashMap<String, FaitAbstrait> BaseDeFait) {
		// TODO Auto-generated method stub
		return false;
	}

}
