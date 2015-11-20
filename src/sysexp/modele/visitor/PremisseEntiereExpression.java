package sysexp.modele.visitor;

public class PremisseEntiereExpression implements Forme{

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseEntiereExpression(this);
	}

}
