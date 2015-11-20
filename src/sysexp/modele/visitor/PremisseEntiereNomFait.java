package sysexp.modele.visitor;

public class PremisseEntiereNomFait implements Forme {

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseEntiereNomFait(this);
	}

}
