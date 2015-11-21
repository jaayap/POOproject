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
	protected ExpressionEntiere expression;
	protected FaitEntier fait;
	protected OperateurBinaire comparateur;// '=' '>' '<'
	
	public PremisseEntiereExpression(ExpressionEntiere expression,FaitEntier fait, OperateurBinaire comparateur){
		this.expression = expression;
		this.fait = fait;
		this.comparateur = comparateur;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseEntiereExpression(this);
	}

}
