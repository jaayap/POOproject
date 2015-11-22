package sysexp.modele.visitor;

import sysexp.modele.FaitSymbolique;

public class ConclusionSymboliqueNomFait implements Forme {
	protected FaitSymbolique fait;
	protected String nomFait;
	
	public ConclusionSymboliqueNomFait(FaitSymbolique fait){
		this.fait = fait;
	}
	
	public ConclusionSymboliqueNomFait(String nomFait) {
		this.nomFait = nomFait;
	}

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionSymboliqueNomFait(this);
	}

}
