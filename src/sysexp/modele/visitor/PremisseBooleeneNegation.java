package sysexp.modele.visitor;

import sysexp.modele.FaitBooleen;

public class PremisseBooleeneNegation implements Forme {
	protected FaitBooleen conclusion;
	protected String nomFait;
	
	public PremisseBooleeneNegation(FaitBooleen conclusion){
		this.conclusion = conclusion;
	}
	
	public PremisseBooleeneNegation(String nomFait){
		this.nomFait = nomFait;
	}
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseBooleeneNegation(this);
	}

}
