package sysexp.modele;

import java.util.HashMap;

public class FaitEntier implements FaitAbstrait,ExpressionEntiere {
	
	protected String nom;
	protected long valeur;
	
	public FaitEntier(String nom, long valeur){
		this.nom = nom;
		this.valeur = valeur;
	}
	
	public FaitEntier(String nom){
		this.nom = nom;
	}
	
	public long valeur(){
		return valeur;
	}

	@Override
	public String getNomFait() {
		return nom;
	}
	
	@Override
	public long interpret(HashMap<String, FaitAbstrait> baseDeFaits)  throws  NoContainsKeyException{
			if(!baseDeFaits.containsKey(nom)){
				 throw new NoContainsKeyException(nom);
			}
			return valeur;
		}

}
