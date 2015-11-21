package sysexp.modele.visitor;

import sysexp.modele.FaitSymbolique;

public class ConclusionSymboliqueNomFait implements Forme {
	protected FaitSymbolique nomFait;
	
	public ConclusionSymboliqueNomFait(FaitSymbolique nomFait){
		this.nomFait = nomFait;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionSymboliqueNomFait(this);
	}

}
