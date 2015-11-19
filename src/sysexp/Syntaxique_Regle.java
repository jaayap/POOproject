package sysexp;

import java.io.IOException;
import java.util.HashMap;

import sysexp.builders.lorraine.Jeton;
import sysexp.builders.lorraine.Lexical;
import sysexp.modele.FaitAbstrait;
import sysexp.modele.FaitBooleen;

public class Syntaxique_Regle {
	protected Lexical lexical;
	protected Jeton precharge;
	public HashMap<String, FaitAbstrait> baseDeFaits = new HashMap<String, FaitAbstrait>();
	
	
	public Syntaxique_Regle(Lexical lexical) {
			this.lexical = lexical;
}
	 
	public boolean verifier() throws IOException {
		precharge = lexical.suivant();
		baseDeFaits.put("avoir_fait_prison",new FaitBooleen("avoir_fait_prison"));
		baseDeFaits.put("leger",new FaitBooleen("leger"));
		//while(!precharge.estFinExpression()){
		if (!estRegles()) {
			   return false;
		}
		//}
		return precharge.estFinExpression();
	}
	protected boolean estRegles() throws IOException{
		System.out.println("estRegles");
		//{ Regle ';' }
		while (estRegle()){
			// il doit obligatoirement y avoir un point virgule derriere une Regle
			if(precharge.estPointVirgule()){
				
				precharge = lexical.suivant();
			//	return true;
			}else{
				return false;
			}
		}
		return true; 
	}
	protected boolean estRegle() throws IOException{
		if(estRegleSansPremisse() || estRegleAvecPremisses()){
			return true;
		}
		return false;
	}
	
	protected boolean estRegleSansPremisse() throws IOException{
		if(estConclusion()){
			return true;
		}
		return false;
	}
	protected boolean estRegleAvecPremisses() throws IOException{
		//'si'
		if(precharge.estSi()){
			precharge = lexical.suivant();
		}
		else{
			return false;
		}
		//Condition
		if(!estCondition()){
			return false;
		}
		//'alors'
		if(precharge.estAlors()){
			precharge = lexical.suivant();
		}
		else{
			return false;
		}
		//Conclusion
		if(!estConclusion()){
			System.out.println("pas une conclusion?");
			return false;
		}
		return true;
		
	}
	protected boolean estCondition() throws IOException{
		//Premisse
		if(!estConclusion()){
			return false;
		}
		
		//{ et Premisse } // FAUX DANS 'fortune > 10000 ' car c'est une conclusion entiere
		//					la premisse est égal a fortune seulement :/
		//					PAS de souci avec les profession = medecin
		while(precharge.estEt()){
			precharge = lexical.suivant();
			if(!estConclusion()){
				return false;
			}
		}
		
		return true;
	}
	protected boolean estConclusion() throws IOException{
		if(estConclusionBooleene()){
			return true;
		}
		return false;
	}
	protected boolean estConclusionBooleene() throws IOException{
		//Fait_booleen ou 'non' Fait_Booleen
		if(!precharge.estNon()  && !baseDeFaits.containsKey(precharge.lireRepresentation())){//estFait suffisant car si ce n'est pas un fait, on est sur 
			System.out.println("conclusion boolean 1 if" + precharge.lireRepresentation());
			return false;
		}
		System.out.println("conclusion boolean 1 if'");
		if(baseDeFaits.containsKey(precharge.lireRepresentation())){ // on vérifie que la chaine de caractere contenu 
			//if(baseDeFaits.get(precharge.lireRepresentation()).equals("faits_booleens")){
				
				precharge = lexical.suivant();
			//}
		//	else{
			//	return false;
			//}
		}
		while(precharge.estNon()){
			precharge = lexical.suivant();
			if(!precharge.estFait()){
				return false;
			}
			else{
				precharge = lexical.suivant();
			}
		}
		return true;
	}
	
}
