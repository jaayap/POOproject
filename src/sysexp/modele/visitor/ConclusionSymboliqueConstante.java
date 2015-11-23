package sysexp.modele.visitor;

import sysexp.modele.FaitSymbolique;

public class ConclusionSymboliqueConstante implements Forme {
	protected String comparateur;
	protected final String nomConstante;//valeur
	protected FaitSymbolique fait;
	
	public ConclusionSymboliqueConstante(FaitSymbolique fait, String comparateur,String nomConstante){
		this.fait = fait;
		this.comparateur = comparateur;
		this.nomConstante = nomConstante;
	}
	
	@Override
	public String getNomFait() {
		return fait.getNomFait();
	}
	
	public String getComparateur(){
		return comparateur;
	}
	public String getValeur(){
		return nomConstante;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionSymboliqueConstante(this);
	}

	

}
