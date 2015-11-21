package sysexp.modele.visitor;

import sysexp.modele.FaitBooleen;

public class ConclusionBooleeneNegation implements Forme {
	protected FaitBooleen conclusion;
	protected String nomFait;
	
	public ConclusionBooleeneNegation(FaitBooleen conclusion){
		this.conclusion = conclusion;
	}

	public ConclusionBooleeneNegation(String nomFait){
		this.nomFait = nomFait;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionBooleeneNegation(this);
	}
}
