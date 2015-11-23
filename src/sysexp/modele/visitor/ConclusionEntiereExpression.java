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
	protected String comparateur;
	
	public ConclusionEntiereExpression(FaitEntier fait,String comparateur, ExpressionEntiere expression){
		this.fait_entier = fait;
		this.comparateur = comparateur;
		this.expression = expression;
	}
	
	@Override
	public String getNomFait(){
		return fait_entier.getNomFait();
	}

	public String getComparateur(){
		return comparateur;
	}
	
	public ExpressionEntiere getExpression(){
		return expression;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionEntiereExpression(this);
	}

}
