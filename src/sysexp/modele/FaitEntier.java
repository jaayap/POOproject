package sysexp.modele;

public class FaitEntier implements Fait {
	protected String nom;
	
	public int valeur(){
		return 0;
	}

	@Override
	public String getNomFait() {
		return nom;
	}
}
