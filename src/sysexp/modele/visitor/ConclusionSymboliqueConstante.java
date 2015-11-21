package sysexp.modele.visitor;

public class ConclusionSymboliqueConstante implements Forme {
	
	protected final String nomConstante;
	
	public ConclusionSymboliqueConstante(String nomConstante){
		this.nomConstante = nomConstante;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionSymboliqueConstante(this);
	}

}
