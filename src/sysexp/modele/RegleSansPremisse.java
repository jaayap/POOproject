package sysexp.modele;

import java.util.HashMap;

import sysexp.modele.visitor.Forme;
import sysexp.modele.visitor.VisiteurForme;
import sysexp.modele.visitor.VisiteurFormeMoteur;

public class RegleSansPremisse extends RegleAbstraite{
	protected final long numero;
	protected Forme conclusion;
	protected boolean regledeclenchee = false;
	
	public RegleSansPremisse(long numero, Forme conclusion){
		this.numero = numero;
		this.conclusion = conclusion;
	}
	
	@Override
	public boolean iterer(HashMap<String, FaitAbstrait> baseDeFait) {
		boolean flag = false,flag2 = false;
		
		VisiteurForme visiteur = new VisiteurFormeMoteur(baseDeFait);
		if(this.estDeclenchable(visiteur)){ // Toutes les conditions sont vraies
    		this.declencher(baseDeFait, visiteur);//on la déclanche
    		flag =  true;
    	}
		
    //	if(this.possedeSuccesseur() == true){
    	if(suivante!=null){ 
    		flag2 = suivante.iterer(baseDeFait);
    	}
		return flag || flag2;
	}

	@Override
	public boolean estDeclenchee() {
		return regledeclenchee;
	}

	@Override
	public Forme getConclusion() {
		return conclusion;
	}

	public boolean estDeclenchable(VisiteurForme visiteur) {
		//une regle sans prémisse est toujours déclenchable sauf si elle a été déclenché
		if(regledeclenchee && visiteur.getDerniereConclusionVisitee()== true){
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void declencher(HashMap<String, FaitAbstrait> BaseDeFait, VisiteurForme visiteur) {
		regledeclenchee = true;
		conclusion.accept(visiteur);
	}

}
