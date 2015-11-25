package sysexp.modele.visitor;

import sysexp.modele.FaitSymbolique;

public class PremisseSymboliqueConstante implements Forme{
	protected FaitSymbolique fait;
	protected String comparateur;
	protected String constante;
	
	public PremisseSymboliqueConstante(FaitSymbolique fait,String comparateur,String constante){
		this.fait = fait;
		this.constante = constante;
		this.comparateur = comparateur;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseSymboliqueConstante(this);
	}

	@Override
	public String getNomFait() {
		return fait.getNomFait();
	}
	
	public String getValeur(){
		return constante;
	}
	
	public String getComparateur(){
		return comparateur;
	}
	
	public String toString(){
		return fait.getNomFait()+" "+comparateur+" "+constante;
	}

}
