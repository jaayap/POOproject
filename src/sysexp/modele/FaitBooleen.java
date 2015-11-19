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
	
	public boolean valeur(){
		return valeur;
	}
	
	@Override
	public String getNomFait() {
		return nom;
	}

}
