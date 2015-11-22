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
	
	public ConclusionEntiereNomFait(FaitEntier fait, ExpressionEntiere expression){
		this.fait_entier = fait;
		this.expression = expression;
	}
	
	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitConclusionEntiereNomFait(this);
	}

}
