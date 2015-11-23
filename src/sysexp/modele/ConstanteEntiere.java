package sysexp.modele;

import java.util.HashMap;

public class ConstanteEntiere implements ExpressionEntiere{
	protected long valeur;
	protected String nomConstante;
	
	
	public ConstanteEntiere(String nomConstante,long valeur){
		this.nomConstante = nomConstante;
		this.valeur = valeur;
	}
	
	public ConstanteEntiere(String nomConstante){
		this.nomConstante = nomConstante;
	}
	
	@Override
	public long interpret(HashMap<String, FaitAbstrait> baseDeFaits) throws NoContainsKeyException {
		return valeur;
	}
}
