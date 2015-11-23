package sysexp.modele;

import java.util.HashMap;

import sysexp.modele.visitor.Forme;
import sysexp.modele.visitor.VisiteurForme;
import sysexp.modele.visitor.VisiteurFormeMoteur;

public abstract class RegleAbstraite {
	/**
	 * la classe mère des règles devra définir 
	 * un attribut numero (de type long puisque le nombre de règles peut être très important)
	 * dont la valeur sera donnée par le builder de la base de règles.
	*/
	protected RegleAbstraite suivante;
	
	public final long numero = 0;
	
	public RegleAbstraite lireSuccesseur() {
        return suivante;
    }
	
    public void ecrireSuccesseur(RegleAbstraite pSuivante) {
        suivante = pSuivante;
    }
     
    public boolean possedeSuccesseur() {
    	  return (suivante != null);
    }
     
     
     public void reinitialiser(){
    	 
     }
    
    public abstract boolean iterer(HashMap<String, FaitAbstrait> BaseDeFait);
	
	public abstract Forme getConclusion();
	
	
	public abstract boolean estDeclenchee();
	

//	public abstract boolean estDeclenchable(VisiteurForme visiteur,HashMap<String, FaitAbstrait> baseDeFait);

	public abstract void declencher(HashMap<String, FaitAbstrait> BaseDeFait,VisiteurForme visiteur);

	

}
