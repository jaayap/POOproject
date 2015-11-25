package sysexp.modele.visitor;

import sysexp.modele.FaitBooleen;

public class PremisseBooleeneAffirmation implements Forme {
	protected FaitBooleen conclusion;
	
	public PremisseBooleeneAffirmation(FaitBooleen conclusion){
		this.conclusion = conclusion;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseBooleeneAffirmation(this);
	}
	@Override
	public String getNomFait() {
		return conclusion.getNomFait();
	}
	public String toString(){
		return conclusion.getNomFait();
	}

}
