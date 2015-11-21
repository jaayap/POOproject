package sysexp.modele.visitor;

import java.util.HashMap;

import sysexp.modele.FaitAbstrait;

public class VisiteurFormeMoteur implements VisiteurForme {
	/**
	 * Attribut représentant la base de faits
	 */
	protected HashMap<String, FaitAbstrait> baseDeFaits = new HashMap<String, FaitAbstrait>();
	protected boolean dernierePremisseVisitee; // true : est verifiée, false : n'est pas vérifiée
	protected boolean derniereConclusionVisitee; //true : a pu s'executer, false : n'a pas pu s'executer
	protected CodeErreur code;

	
	public boolean PremisseEstVerifiee(){
		return false;
	}
	
	public void ExecuterConclusion(){
		
	}

	@Override
	public void visitPremisseBooleeneAffirmation(
			PremisseBooleeneAffirmation premisse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPremisseBooleeneNegation(PremisseBooleeneNegation premisse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPremisseSymboliqueConstante(
			PremisseSymboliqueConstante premisse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPremisseSymboliqueNomFait(
			PremisseSymboliqueNomFait premisse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPremisseEntiereExpression(
			PremisseEntiereExpression premisse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPremisseEntiereNomFait(PremisseEntiereNomFait premisse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitConclusionBooleeneAffirmation(
			ConclusionBooleeneAffirmation conclusion) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visitConclusionBooleeneNegation(
			ConclusionBooleeneNegation conclusion) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visitConclusionSymboliqueConstante(
			ConclusionSymboliqueConstante conclusion) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visitConclusionSymboliqueNomFait(
			ConclusionSymboliqueNomFait conclusion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitConclusionEntiereNomFait(
			ConclusionEntiereNomFait conclusion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitConclusionEntiereExpression(
			ConclusionEntiereExpression conclusion) {
		// TODO Auto-generated method stub
		
	}




}
