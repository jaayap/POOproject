package sysexp.modele.visitor;

import sysexp.modele.ExpressionEntiere;
import sysexp.modele.FaitEntier;
/**
 * Correspond partie droite = expression
 * Conclusion de la forme nom_fait =  expression
 * @author Jasmine
 *
 */
public class ConclusionEntiereExpression implements Forme {
	protected FaitEntier fait_entier;
	protected ExpressionEntiere expression;
	
	public ConclusionEntiereExpression(FaitEntier fait, ExpressionEntiere expression){
		this.fait_entier = fait;
		this.expression = expression;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionEntiereExpression(this);
	}

}
