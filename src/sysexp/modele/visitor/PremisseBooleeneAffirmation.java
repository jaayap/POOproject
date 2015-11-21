package sysexp.modele.visitor;

import sysexp.modele.FaitBooleen;

public class PremisseBooleeneAffirmation implements Forme {
	protected FaitBooleen conclusion;
	protected String nomFait;
	
	public PremisseBooleeneAffirmation(FaitBooleen conclusion){
		this.conclusion = conclusion;
	}
	public PremisseBooleeneAffirmation(String nomFait){
		this.nomFait = nomFait;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseBooleeneAffirmation(this);
	}

}
