package sysexp.modele.visitor;

public class PremisseSymboliqueNomFait implements Forme {

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseSymboliqueNomFait(this);
	}

}
