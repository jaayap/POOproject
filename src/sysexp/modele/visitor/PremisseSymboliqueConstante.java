package sysexp.modele.visitor;

public class PremisseSymboliqueConstante implements Forme{
	protected String constante;
	
	public PremisseSymboliqueConstante(String constante) {
		this.constante = constante;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseSymboliqueConstante(this);
	}

}
