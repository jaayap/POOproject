package sysexp.modele.visitor;

import sysexp.modele.ExpressionEntiere;
import sysexp.modele.FaitEntier;
import sysexp.modele.OperateurBinaire;

/**
 * nomfait comparateur Expression
 * @author Jasmine
 *
 */
public class PremisseEntiereNomFait implements Forme {
	protected FaitEntier fait;
	protected String comparateur;// '=' '>' '<'
	protected FaitEntier valeur;
	
	public PremisseEntiereNomFait(FaitEntier fait,FaitEntier valeur, String comparateur){
		this.valeur = valeur;
		this.fait = fait;
		this.comparateur = comparateur;
	}

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseEntiereNomFait(this);
	}
	
	@Override
	public String getNomFait() {
		return fait.getNomFait();
	}
	
	public String getComparateur(){
		return comparateur;
	}
	
	public String getExpression(){
		return valeur.getNomFait();
	}
}
