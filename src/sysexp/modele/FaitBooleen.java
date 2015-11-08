package sysexp.modele;

public class FaitBooleen implements Fait{
	
	protected String nom;
	
	public boolean valeur(){
		return false;
	}

	@Override
	public String getNomFait() {
		return nom;
	}

}
