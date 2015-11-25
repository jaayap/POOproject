package sysexp.modele;

public class FaitBooleen implements FaitAbstrait{
	
	protected String nom;
	protected boolean valeur;

	public FaitBooleen(String nom, boolean valeur){
		this.nom = nom;
		this.valeur = valeur;
	}
	
	public FaitBooleen(String nom){
		this.nom = nom;
	}
	
	public boolean getValeur(){
		return valeur;
	}
	
	@Override
	public String getNomFait() {
		return nom;
	}

	public void setValeur(boolean valeur) {
		this.valeur = valeur;
		
	}
	
	@Override
	public String toString(){
		return  valeur+" ";
	}
}
