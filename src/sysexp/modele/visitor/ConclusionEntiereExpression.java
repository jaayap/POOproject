package sysexp.modele.visitor;

public class ConclusionEntiereExpression implements Forme {

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionEntiereExpression(this);
	}

}
