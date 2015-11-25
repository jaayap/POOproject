package sysexp.modele.visitor;

import sysexp.modele.FaitBooleen;

public class ConclusionBooleeneNegation implements Forme {
	protected FaitBooleen conclusion;
	
	public ConclusionBooleeneNegation(FaitBooleen conclusion){
		this.conclusion = conclusion;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionBooleeneNegation(this);
	}

	@Override
	public String getNomFait() {
		return conclusion.getNomFait();
	}
	
	public String toString(){
		return "non "+conclusion.getNomFait();
	}

}
