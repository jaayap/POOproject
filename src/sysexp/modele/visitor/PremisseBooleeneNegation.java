package sysexp.modele.visitor;

public class PremisseBooleeneNegation implements Forme {

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseBooleeneNegation(this);
	}

}
