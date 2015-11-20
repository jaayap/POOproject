package sysexp.modele.visitor;

public class ConclusionBooleeneNegation implements Forme {

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionBooleeneNegation(this);
	}
}
