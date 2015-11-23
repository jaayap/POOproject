package sysexp.modele.visitor;

import sysexp.modele.ExpressionEntiere;
import sysexp.modele.FaitEntier;

/**
 * Correspond partie droite = nom fait
 * Conclusion de la forme  expression = nom_fait
 * @author Jasmine
 *
 */
public class ConclusionEntiereNomFait implements Forme {
	protected FaitEntier fait_entier;
	protected ExpressionEntiere expression;
	protected String comparateur;
	
	public ConclusionEntiereNomFait(FaitEntier fait,String comparateur, ExpressionEntiere expression){
		this.fait_entier = fait;
		this.comparateur = comparateur;
		this.expression = expression;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionEntiereNomFait(this);
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
	
}
