package sysexp.modele;

import java.util.HashMap;
import java.util.LinkedList;

import sysexp.modele.visitor.Forme;
import sysexp.modele.visitor.VisiteurForme;
import sysexp.modele.visitor.VisiteurFormeMoteur;

public class RegleAvecPremisse extends RegleAbstraite{
	protected final long numero;
	protected LinkedList<Forme> condition;
	protected Forme conclusion;
	protected boolean regledeclenchee;
	
	public RegleAvecPremisse(long numero,LinkedList<Forme> condition, Forme conclusion){
		this.numero = numero;
		this.condition = condition;
		this.conclusion = conclusion;
	}
	
	@Override
	public boolean iterer(HashMap<String, FaitAbstrait> baseDeFait) {
		boolean flag = false,flag2 = false;
		VisiteurForme visiteur = new VisiteurFormeMoteur(baseDeFait);
		
		if(this.estDeclenchable(visiteur, baseDeFait)){ // Toutes les conditions sont vraies
    		this.declencher(baseDeFait, visiteur);//on la déclanche
    		flag =  true;
    	}
    //	if(suivante.possedeSuccesseur()){
		if(suivante != null){
    		flag2 = suivante.iterer(baseDeFait);
    	}
		return flag || flag2;
	}

	public boolean estDeclenchable(VisiteurForme visiteur, HashMap<String, FaitAbstrait> baseDeFait){
		if(regledeclenchee == true && visiteur.getDernierePremisseVisitee()== true){
			return false;
		}
		
		else {
			for(int i=0; i < condition.size(); i++){
				condition.get(i).accept(visiteur);
				
				if(visiteur.getDernierePremisseVisitee() == true){
					return true;
				}
				visiteur.setDernierePremisseVisitee(false);
			}
		}
		return false;
	}

	@Override
	public boolean estDeclenchee() {
		return regledeclenchee;
	}

	@Override
	public Forme getConclusion() {
		return conclusion;
	}

	@Override
	public void declencher(HashMap<String, FaitAbstrait> BaseDeFait,VisiteurForme visiteur) {
		regledeclenchee = true;
		conclusion.accept(visiteur);
		//BaseDeFait.put(conclusion.getNomFait(), new FaitEntier(conclusion.getNonFait(), conclusion.getValeur().executer(baseDeFait)));
	}
	
	
	
	
}
