package sysexp.modele.visitor;

public class PremisseBooleeneAffirmation implements Forme {

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseBooleeneAffirmation(this);
	}

}
