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
	protected ExpressionEntiere expression;
	protected FaitEntier fait;
	protected OperateurBinaire comparateur;// '=' '>' '<'
	
	public PremisseEntiereNomFait(ExpressionEntiere expression,FaitEntier fait, OperateurBinaire comparateur){
		this.expression = expression;
		this.fait = fait;
		this.comparateur = comparateur;
	}

	@Override
	public void accept(VisiteurForme visiteur) {
		visiteur.visitPremisseEntiereNomFait(this);
	}

}
