package sysexp.modele;

public class FaitEntier implements FaitAbstrait {
	
	protected String nom = "";
	protected int valeur=0;
	
	public FaitEntier(String nom, int valeur){
		this.nom = nom;
		this.valeur = valeur;
	}
	
	public FaitEntier(String nom){
		this.nom = nom;
	}
	
	public int valeur(){
		return valeur;
	}

	@Override
	public String getNomFait() {
		return nom;
	}
}
