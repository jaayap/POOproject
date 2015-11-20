package sysexp.modele.visitor;

public class ConclusionBooleeneAffirmation implements Forme {

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionBooleeneAffirmation(this);
	}

}
