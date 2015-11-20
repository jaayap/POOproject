package sysexp.modele.visitor;

public class ConclusionSymboliqueConstante implements Forme {

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionSymboliqueConstante(this);
	}

}
