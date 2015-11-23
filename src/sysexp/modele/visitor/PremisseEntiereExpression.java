package sysexp.modele.visitor;

import sysexp.modele.ExpressionEntiere;
import sysexp.modele.FaitEntier;
import sysexp.modele.OperateurBinaire;

/**
 * expression comparateur fait_entier
 * 
 * @author Jasmine
 *
 */
public class PremisseEntiereExpression implements Forme{
	protected FaitEntier fait;
	protected String comparateur;// '=' '>' '<'
	protected ExpressionEntiere expression;
	

	
	public PremisseEntiereExpression(ExpressionEntiere expression,FaitEntier fait, String comparateur){
		this.expression = expression;
		this.fait = fait;
		this.comparateur = comparateur;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseEntiereExpression(this);
	}
	@Override
	public String getNomFait() {
		return fait.getNomFait();
	}
	
	public String getComparateur(){
		return comparateur;
	}
	
	public ExpressionEntiere getExpression(){
		return expression;
	}

}
