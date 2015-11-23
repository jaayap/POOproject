package sysexp.modele.visitor;

import java.util.HashMap;

import sysexp.modele.FaitAbstrait;
import sysexp.modele.FaitBooleen;
import sysexp.modele.FaitSymbolique;
//visit : executer
public class VisiteurFormeMoteur implements VisiteurForme {
	/**
	 * Attribut représentant la base de faits
	 */
	protected HashMap<String, FaitAbstrait> baseDeFaits;
	protected boolean dernierePremisseVisitee; // true : est verifiée, false : n'est pas vérifiée
	protected boolean derniereConclusionVisitee; //true : a pu s'executer, false : n'a pas pu s'executer
	protected CodeErreur code;

	public VisiteurFormeMoteur(HashMap<String, FaitAbstrait> baseDeFaits){
		this.baseDeFaits = baseDeFaits;
	}
	
	public void setDernierePremisseVisitee(boolean valeur){
		this.dernierePremisseVisitee = valeur;
	}
	
	public void setDerniereConclusionVisitee(boolean valeur){
		this.derniereConclusionVisitee = valeur;
	}
	
	public boolean getDernierePremisseVisitee(){
		return dernierePremisseVisitee;
	}
	
	public boolean getDerniereConclusionVisitee(){
		return derniereConclusionVisitee;
	}
	
	public boolean PremisseEstVerifiee(){
		return false;
	}
	
	

	@Override
	public void visitPremisseBooleeneAffirmation(PremisseBooleeneAffirmation premisse) {
		if(baseDeFaits.containsKey(premisse.getNomFait())){ //Si la base contient le fait
			FaitBooleen fait = (FaitBooleen) baseDeFaits.get(premisse.getNomFait());
			if(fait.getValeur() == true){ //Si la valeur est égal a true - la premisse est vraie
				dernierePremisseVisitee = true;
			}else{//Le fait est dans la base mais est égal a faux
				dernierePremisseVisitee = false;
			}
		}
		else{//Le fait n'est même pas connu
			dernierePremisseVisitee = false;
		}
	}

	@Override
	public void visitPremisseBooleeneNegation(PremisseBooleeneNegation premisse) {
		if(baseDeFaits.containsKey(premisse.getNomFait())){ //Si la base contient le fait
			FaitBooleen fait = (FaitBooleen) baseDeFaits.get(premisse.getNomFait());
			if(fait.getValeur() == true){ //Si la valeur est égal a false - la premisse est vraie
				dernierePremisseVisitee = false;
			}else{//Le fait est dans la base mais est égal a vrai
				dernierePremisseVisitee = true;
			}
		}
		else{//Le fait n'est même pas connu
			dernierePremisseVisitee = false;
		}
	}

	@Override
	public void visitPremisseSymboliqueConstante(PremisseSymboliqueConstante premisse) {
		if(baseDeFaits.containsKey(premisse.getNomFait())){ //Si la base contient le fait
			FaitSymbolique fait = (FaitSymbolique) baseDeFaits.get(premisse.getNomFait());
			if(fait.getValeur() == premisse.getValeur()){ //le fait possède la valeur qu'on souhaite
				dernierePremisseVisitee = true;
			}else{//Le fait est dans la base mais n'est pas égal a notre constante
				dernierePremisseVisitee = false;
			}
		}
		else{//Le fait n'est même pas connu
			dernierePremisseVisitee = false;
		}
	}

	@Override
	public void visitPremisseSymboliqueNomFait(PremisseSymboliqueNomFait premisse) {
		//Fait1 /= Fait2
		if(baseDeFaits.containsKey(premisse.getNomFait())){ //Si la base contient le fait1 dans la base
			if(baseDeFaits.containsKey(premisse.getValeur())){// Fait2 dans la base
			FaitSymbolique fait = (FaitSymbolique) baseDeFaits.get(premisse.getNomFait());
			if(fait.getValeur() == premisse.getValeur()){ //le fait doit être différent de notre fait
				dernierePremisseVisitee = false;
			}else{//Le fait est dans la base mais n'a pas la meme valeur.
				dernierePremisseVisitee = true;
			}
			}else{
				dernierePremisseVisitee = false;
			}
			
		}
		else{//Le fait n'est même pas connu
			dernierePremisseVisitee = false;
		}
		
	}

	@Override
	public void visitPremisseEntiereExpression(PremisseEntiereExpression premisse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPremisseEntiereNomFait(PremisseEntiereNomFait premisse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitConclusionBooleeneAffirmation(ConclusionBooleeneAffirmation conclusion) {
		System.out.println("conclubool A");
		if(baseDeFaits.containsKey(conclusion.getNomFait())){ //Si la base contient déja le fait
			//on doit tester s'il on la meme valeur pour les booleenS ?
			derniereConclusionVisitee = false;
		}
		else{
			baseDeFaits.put(conclusion.getNomFait(), new FaitBooleen(conclusion.getNomFait(), true)); // la valeur est true car c'est une affirmation
			derniereConclusionVisitee = true;
		}
		
	}
	
	@Override
	public void visitConclusionBooleeneNegation(ConclusionBooleeneNegation conclusion) {
		System.out.println("conclubool N");
		if(baseDeFaits.containsKey(conclusion.getNomFait())){ //Si la base contient déja le fait
			derniereConclusionVisitee = false;
		}
		else{
			baseDeFaits.put(conclusion.getNomFait(), new FaitBooleen(conclusion.getNomFait(), false)); // la valeur est faux car c'est une négation
			derniereConclusionVisitee = true;
		}
		
	}
	
	@Override
	public void visitConclusionSymboliqueConstante(ConclusionSymboliqueConstante conclusion) {
		if(baseDeFaits.containsKey(conclusion.getNomFait())){ //Si la base contient déja le fait
			//on doit tester si le fait de la base à la meme valeur que la conclusion
			FaitSymbolique valeurDansLaBase = (FaitSymbolique) baseDeFaits.get(conclusion.getNomFait());
			if(conclusion.getValeur() == valeurDansLaBase.getValeur()){
				dernierePremisseVisitee = false;
			}
			else{// on doit mettre a jour le fait
		//	valeurDansLaBase.setValeur(conclusion.getValeur());
			baseDeFaits.remove(conclusion.getNomFait());
			baseDeFaits.put(conclusion.getNomFait(), new FaitSymbolique(conclusion.getNomFait(), conclusion.getValeur()));
			dernierePremisseVisitee = true;
			}
		}
		else{
			baseDeFaits.put(conclusion.getNomFait(), new FaitSymbolique(conclusion.getNomFait(), conclusion.getValeur())); 			dernierePremisseVisitee = true;
		}
		
	}
	
	@Override
	public void visitConclusionSymboliqueNomFait(ConclusionSymboliqueNomFait conclusion) {
		if(baseDeFaits.containsKey(conclusion.getNomFait())){ //Si la base contient déja le fait
			//on doit tester si le fait de la base à la meme valeur que la conclusion
			FaitSymbolique valeurDansLaBase = (FaitSymbolique) baseDeFaits.get(conclusion.getNomFait());
			if(conclusion.getValeur() == valeurDansLaBase.getValeur()){
				dernierePremisseVisitee = false;
			}
			else{// on doit mettre a jour le fait
		//	valeurDansLaBase.setValeur(conclusion.getValeur());
			baseDeFaits.remove(conclusion.getNomFait());
			baseDeFaits.put(conclusion.getNomFait(), new FaitSymbolique(conclusion.getNomFait(), conclusion.getValeur()));
			dernierePremisseVisitee = true;
			}
		}
		else{
			baseDeFaits.put(conclusion.getNomFait(), new FaitSymbolique(conclusion.getNomFait(), conclusion.getValeur())); 			dernierePremisseVisitee = true;
		}
		
	}

	@Override
	public void visitConclusionEntiereNomFait(ConclusionEntiereNomFait conclusion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitConclusionEntiereExpression(ConclusionEntiereExpression conclusion) {
		// TODO Auto-generated method stub
		
	}




}
