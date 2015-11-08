package sysexp.modele;

public class FaitSymbolique implements Fait {
	
	protected String nom;
	
	public String valeur(){
		return " ";
	}

	@Override
	public String getNomFait() {
		return nom;
	}
}
