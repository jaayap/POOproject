package sysexp.modele.visitor;

public interface VisiteurForme {
	
	public void setDernierePremisseVisitee(boolean valeur);
	
	public void setDerniereConclusionVisitee(boolean valeur);
	
	public boolean getDernierePremisseVisitee();
	
	public boolean getDerniereConclusionVisitee();

	void visitPremisseBooleeneAffirmation(PremisseBooleeneAffirmation premisse);

	void visitPremisseBooleeneNegation(PremisseBooleeneNegation premisse);

	void visitPremisseSymboliqueConstante(PremisseSymboliqueConstante premisse);

	void visitPremisseSymboliqueNomFait(PremisseSymboliqueNomFait premisse);

	void visitPremisseEntiereExpression(PremisseEntiereExpression premisse);

	void visitPremisseEntiereNomFait(PremisseEntiereNomFait premisse);
	
	void visitConclusionSymboliqueNomFait(ConclusionSymboliqueNomFait conclusion);

	void visitConclusionBooleeneAffirmation(ConclusionBooleeneAffirmation conclusion);

	void visitConclusionSymboliqueConstante(ConclusionSymboliqueConstante conclusion);

	void visitConclusionEntiereNomFait(ConclusionEntiereNomFait conclusion);

	void visitConclusionEntiereExpression(ConclusionEntiereExpression conclusion);

	void visitConclusionBooleeneNegation(ConclusionBooleeneNegation conclusion);

}
