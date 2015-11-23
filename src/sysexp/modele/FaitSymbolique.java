package sysexp.modele;

public class FaitSymbolique implements FaitAbstrait {
	
	protected String nom;
	protected String valeur;
	

	public FaitSymbolique(String nom,String valeur){
		this.nom = nom;
		this.valeur = valeur;
	}
	
	public FaitSymbolique(String nom){
		this.nom = nom;
	}
	
	public String getValeur(){
		return valeur;
	}

	@Override
	public String getNomFait() {
		return nom;
	}
	
	public void setValeur(String valeur){
		this.valeur = valeur;
	}
}
