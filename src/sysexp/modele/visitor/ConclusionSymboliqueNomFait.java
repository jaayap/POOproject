package sysexp.modele.visitor;

import sysexp.modele.FaitSymbolique;

public class ConclusionSymboliqueNomFait implements Forme {
	protected FaitSymbolique fait;
	protected String comparateur;
	protected FaitSymbolique valeur;
	
	public ConclusionSymboliqueNomFait(FaitSymbolique fait,String comparateur, FaitSymbolique valeur){
		this.fait = fait;
		this.comparateur = comparateur;
		this.valeur = valeur;
	}
	
	@Override
	public String getNomFait() {
		return fait.getNomFait();
	}
	
	public String getComparateur(){
		return comparateur;
	}
	
	public String getValeur(){
		return valeur.getNomFait();
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionSymboliqueNomFait(this);
	}


}
