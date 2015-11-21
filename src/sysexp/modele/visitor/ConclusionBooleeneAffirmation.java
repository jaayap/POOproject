package sysexp.modele.visitor;

import sysexp.modele.FaitBooleen;

public class ConclusionBooleeneAffirmation implements Forme {
	protected FaitBooleen conclusion;
	protected String nomFait;
	public ConclusionBooleeneAffirmation(FaitBooleen conclusion){
		this.conclusion = conclusion;
	}
	
	public ConclusionBooleeneAffirmation(String nomFait){
		this.nomFait = nomFait;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionBooleeneAffirmation(this);
	}

}
