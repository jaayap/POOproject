package sysexp.modele;

import java.util.HashMap;
import java.util.LinkedList;

import sysexp.modele.visitor.Forme;

public class RegleAvecPremisse implements RegleAbstraite{
	protected final long numero;
	protected LinkedList<Forme> condition;
	protected Forme conclusion;
	
	public RegleAvecPremisse(long numero,LinkedList<Forme> condition, Forme conclusion){
		this.numero = numero;
		this.condition = condition;
		this.conclusion = conclusion;
	}
	
	@Override
	public boolean iterer(HashMap<String, FaitAbstrait> BaseDeFait) {
		// TODO Auto-generated method stub
		return false;
	}

}
