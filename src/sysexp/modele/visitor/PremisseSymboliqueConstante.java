package sysexp.modele.visitor;

public class PremisseSymboliqueConstante implements Forme{

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseSymboliqueConstante(this);
	}

}
