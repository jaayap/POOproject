package sysexp.modele.visitor;

public class ConclusionEntiereNomFait implements Forme {

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionEntiereNomFait(this);
	}

}
