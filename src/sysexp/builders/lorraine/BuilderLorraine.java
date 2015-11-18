package sysexp.builders.lorraine;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;

import sysexp.builders.Builder;
import sysexp.modele.Fait;
import sysexp.modele.FaitBooleen;
import sysexp.modele.FaitEntier;
import sysexp.modele.FaitSymbolique;
import sysexp.modele.Regle;

/**
 * Cette classe represente le ConcreteBuilder
 * Cette classe doit assurer l'analyse Syntaxique ET sémantique de la base de règles
 * Ainsi que la construction de la base de règles
 * @author Jasmine
 *
 */
public class BuilderLorraine implements Builder{
	protected Lexical lexical;
	protected Jeton precharge;
	protected HashMap<String, Fait> baseDeFaits = new HashMap<String, Fait>();
	protected HashMap<String, Regle> baseDeRegles = new HashMap<String, Regle>();
	
	public BuilderLorraine(LineNumberReader lecteur) {
		this.lexical = new Lexical(lecteur);
	}
	
	public Lexical getlexical(){
		return lexical;
	}
	/**
	 * @throws IOException 
	 */
	@Override
	public void buildPart() throws IOException {
		// Pre-chargement du premier jeton.
		precharge = lexical.suivant();

		// Appel de la methode associee aux déclarations.
		if (!estDeclaration()) {
		    //return false;
			System.out.println("erreur");
		}

		// Il faut verifier que nous avons atteint la fin du texte.
		//return precharge.estFinExpression();
		
	}
	protected boolean estDeclaration() throws IOException {
		// C'est une déclaration si c'est
		// une declaration_booleene ou une declatation_symbolique ou une
		// declaration_entiere
		if (estDeclarationBooleene() || estDeclarationSymbolique() || estDeclarationEntiere()) {
			return true;
		}
		// Sinon ce n'est pas une déclaration
		return false;
	}

	protected boolean estDeclarationBooleene() throws IOException {

		// 'faits_booleens' : mot clé
		if (precharge.estFaits_Booleens()) { // On test si c'est bien le jeton 'faits_booleens'
			precharge = lexical.suivant();// Oui, on passe a la suite
		} else {
			return false; // Non, on signal que ce n'est pas une déclaration booleene
		}
		// '='
		if (precharge.estComparateurEgal()) {// Ensuite ce doit être le signe égal
			precharge = lexical.suivant();// Oui, on passe a la suite
		} else {
			return false;
		}

		// 'Faits_Booleens' : liste de faits
		if (!estFaitsBooleens()) {
			return false;
		}
		// ';'
		if (precharge.estPointVirgule()) {// la déclaration doit être terminée par un point virgule
			precharge = lexical.suivant();
		} else {
			return false;
		}
		return true;
	}
	
	protected boolean estFaitsBooleens() throws IOException {// Test si l'on a bien une
																// liste de faits booleens
		// Fait_Booleen
		if (!precharge.estFait()) {// Le premier doit etre un fait 
			return false;
		}
		else{
			baseDeFaits.put(precharge.lireRepresentation(),new FaitBooleen());
			precharge = lexical.suivant();
		}
		// { ',' Fait_Booleen }
		while (precharge.estVirgule()) {// suivi d'une virgule eventuellement
			// on passe au suivant
			precharge = lexical.suivant();
			// Deriere une virgule il doit y avoir un fait_booleen
			if (!precharge.estFait()) {
				return false;
			}else{
				baseDeFaits.put(precharge.lireRepresentation(),new FaitBooleen());
				precharge = lexical.suivant();
			}
		}
		return true;
	}
	
	protected boolean estDeclarationSymbolique() throws IOException{
		// 'faits_symboliques'  : mot clé
		if(precharge.estFaits_Symboliques()){//jeton 'faits_symboliques'
			precharge = lexical.suivant();
		}else{
			return false;
		}
		//'='
		if(precharge.estComparateurEgal()){//jeton 'egal'
			precharge = lexical.suivant();
		}else{
			return false;
		}
		//Faits_Symboliques
		if(!estFaitsSymboliques()){//Liste de faits
			return false;
		}
		// ';'
		if(precharge.estPointVirgule()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		return true;
	}
	protected boolean estFaitsSymboliques() throws IOException{
		//Fait_Symbolique
		if(!precharge.estFait()){
			return false;
		}
		else{
			baseDeFaits.put(precharge.lireRepresentation(),new FaitSymbolique());
			precharge = lexical.suivant();
		}
		// { ',' Fait_Symbolique }
		while(precharge.estVirgule()){
			//on passe au suivant
			precharge = lexical.suivant();
			//Deriere une virgule il doit y avoir un Fait_Symbolique
			if(!precharge.estFait()){
				return false;
			}
			else{
				baseDeFaits.put(precharge.lireRepresentation(),new FaitSymbolique());
				precharge = lexical.suivant();
			}
		}
		return true;
	}
	protected boolean estDeclarationEntiere() throws IOException{
		// 'faits_entiers' : mot clé
		if(precharge.estFaits_Entiers()){
			precharge = lexical.suivant();//jeton 'faits_entiers'
		}else{
			return false;
		}
		//'='
		if(precharge.estComparateurEgal()){
			precharge = lexical.suivant();// jeton '='
		}else{
			return false;
		}
		// Faits_Entiers
		if(!estFaitsEntiers()){ // Liste de faits entiers
			return false;
		}
		// ';'
		if(precharge.estPointVirgule()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		return true;
	}
	protected boolean estFaitsEntiers() throws IOException{
		//Fait_Entier
		if(!precharge.estFait()){
			return false;
		}
		else{
			baseDeFaits.put(precharge.lireRepresentation(),new FaitEntier());
			precharge = lexical.suivant();
		}
		// { ',' Fait_Entier }
		while(precharge.estVirgule()){
			//on passe au suivant
			precharge = lexical.suivant();
			//Deriere une virgule il doit y avoir un fait_Entier
			if(!precharge.estFait()){
				return false;
			}
			else{
				baseDeFaits.put(precharge.lireRepresentation(),new FaitEntier());
				precharge = lexical.suivant();
			}
		}
		return true;
	}

	/*public BaseDeRegles getResult(){
		
		return null;
		
	}*/
	/**
	 * retourne la base de faits
	 */
	public HashMap<String,Fait> getBaseDeFaits(){
		return baseDeFaits;
	}
	/**
	 * 
	 * @return Le produit fini soit la base de regles
	 */
	public HashMap<String, Regle> getBaseDeRegles(){
		return baseDeRegles;
	}
}
