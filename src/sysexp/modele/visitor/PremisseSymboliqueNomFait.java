package sysexp.modele.visitor;

import sysexp.modele.FaitSymbolique;

public class PremisseSymboliqueNomFait implements Forme {
	protected FaitSymbolique nomFait;
	
	public PremisseSymboliqueNomFait(FaitSymbolique nomFait){
		this.nomFait = nomFait;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseSymboliqueNomFait(this);
	}

}
