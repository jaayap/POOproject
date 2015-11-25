package sysexp.modele.visitor;

import sysexp.modele.FaitSymbolique;

public class PremisseSymboliqueNomFait implements Forme {
	protected FaitSymbolique fait;
	protected String comparateur;
	protected FaitSymbolique valeur;
	
	public PremisseSymboliqueNomFait(FaitSymbolique fait, String comparateur, FaitSymbolique valeur){
		this.fait = fait;
		this.comparateur = comparateur;
		this.valeur = valeur;
	}
	public String toString(){
		return fait.getNomFait()+" "+comparateur+" "+valeur.getNomFait();
	}

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseSymboliqueNomFait(this);
	}
	
	
	@Override
	public String getNomFait() {
		return fait.getNomFait();
	}
	
	public String getValeur(){
		return valeur.getNomFait();
	}
	
	public String getComparateur(){
		return comparateur;
	}
}
