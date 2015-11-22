package sysexp.modele.visitor;

import sysexp.modele.FaitSymbolique;

public class PremisseSymboliqueNomFait implements Forme {
	protected FaitSymbolique fait;
	protected String nomFait;
	
	public PremisseSymboliqueNomFait(FaitSymbolique fait){
		this.fait = fait;
	}
	
	public PremisseSymboliqueNomFait(String nomFait) {
		this.nomFait = nomFait;
	}

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseSymboliqueNomFait(this);
	}

}
