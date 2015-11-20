package sysexp.modele.visitor;

public class ConclusionSymboliqueExpression implements Forme {

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionSymboliqueExpression(this);
	}

}
