package sysexp.builders.lorraine;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;

import sysexp.builders.Builder;
import sysexp.modele.FaitAbstrait;
import sysexp.modele.FaitBooleen;
import sysexp.modele.FaitEntier;
import sysexp.modele.FaitSymbolique;
import sysexp.modele.RegleAbstraite;

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
	protected HashMap<String, FaitAbstrait> baseDeFaits = new HashMap<String, FaitAbstrait>();
	protected HashMap<String, RegleAbstraite> baseDeRegles = new HashMap<String, RegleAbstraite>();
	public boolean test;
	
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
		estBaseDeConnaissance();
		
	}
	
	public void nouvelleRegle(String[] components){
		
	}
	public boolean estBaseDeConnaissance() throws IOException{
		System.out.println("c'est parti");
		// Appel de la methode associee aux déclarations.
				if (!estDeclaration()) {
					System.out.println("c'est parti2");
				    return test = false;
					//System.out.println("erreur");
				}
				System.out.println("c'est parti3"+precharge.lireRepresentation());
				// Il faut verifier que nous avons atteint la fin du texte.
				return test = precharge.estFinExpression();
	}
	protected boolean estDeclaration() throws IOException {
		// C'est une déclaration si c'est
		// une declaration_booleene ou une declatation_symbolique ou une declaration_entiere
		if (estDeclarationEntiere() || estDeclarationBooleene() || estDeclarationSymbolique()) {
			System.out.println("c'est miahou "+precharge.lireRepresentation());
			return true;
		}
		System.out.println("c'est miahou2"+precharge.lireRepresentation());
		// Sinon ce n'est pas une déclaration
		return false;
	}

	protected boolean estDeclarationBooleene() throws IOException {
		System.out.println("ici"+precharge.lireRepresentation());
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
		return true;//tout les tests ont réussis
	}
	
	protected boolean estFaitsBooleens() throws IOException {// Test si l'on a bien une
																// liste de faits booleens
		// Fait_Booleen
		if (!precharge.estFait()) {// Le premier doit etre un fait 
			return false;
		}
		else{
			baseDeFaits.put(precharge.lireRepresentation(),new FaitBooleen(precharge.lireRepresentation()));
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
				baseDeFaits.put(precharge.lireRepresentation(),new FaitBooleen(precharge.lireRepresentation()));
				precharge = lexical.suivant();
			}
		}
		return true;
	}
	
	protected boolean estDeclarationSymbolique() throws IOException{
		System.out.println("c'est parti4"+precharge.lireRepresentation());
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
		System.out.println("c'est ppii "+precharge.lireRepresentation());
		if(precharge.estPointVirgule()){
			precharge = lexical.suivant();
			System.out.println("c'est piiiiii "+precharge.lireRepresentation());
		}else{
			return false;
		}
		return true;
	}
	protected boolean estFaitsSymboliques() throws IOException{
		//Fait_Symbolique
		System.out.println("c'est ii "+precharge.lireRepresentation());
		if(!precharge.estFait()){
			return false;
		}
		else{
			baseDeFaits.put(precharge.lireRepresentation(),new FaitSymbolique(precharge.lireRepresentation()));
			precharge = lexical.suivant();
		}
		// { ',' Fait_Symbolique }
		System.out.println("c'est pii "+precharge.lireRepresentation());
		while(precharge.estVirgule()){
			//on passe au suivant
			precharge = lexical.suivant();
			//Deriere une virgule il doit y avoir un Fait_Symbolique
			if(!precharge.estFait()){
				return false;
			}
			else{
				baseDeFaits.put(precharge.lireRepresentation(),new FaitSymbolique(precharge.lireRepresentation()));
				precharge = lexical.suivant();
			}
		}
		return true;
	}
	protected boolean estDeclarationEntiere() throws IOException{
		// 'faits_entiers' : mot clé
		if(precharge.estFaits_Entiers()){
			precharge = lexical.suivant();//jeton 'faits_entiers'
			System.out.println("c'est parti4"+precharge.lireRepresentation());
		}else{
			return false;
		}
		//'='
		if(precharge.estComparateurEgal()){
			precharge = lexical.suivant();// jeton '='
			System.out.println("aprés égal "+precharge.lireRepresentation());
		}else{
			return false;
		}
		// Faits_Entiers
		if(!estFaitsEntiers()){ // Liste de faits entiers
			System.out.println("fortune "+precharge.lireRepresentation());
			return false;
		}
		System.out.println("fortune ,  ff"+precharge.lireRepresentation());
		// ';'
		if(precharge.estPointVirgule()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		System.out.println("TRUE 2 "+precharge.lireRepresentation());
		return true;
	}
	protected boolean estFaitsEntiers() throws IOException{
		System.out.println("test 1 "+precharge.lireRepresentation());
		//Fait_Entier
		if(!precharge.estFait()){
			System.out.println("test 2 "+precharge.lireRepresentation());
			return false;
		}
		else{
			baseDeFaits.put(precharge.lireRepresentation(),new FaitEntier(precharge.lireRepresentation()));
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
				baseDeFaits.put(precharge.lireRepresentation(),new FaitEntier(precharge.lireRepresentation()));
				precharge = lexical.suivant();
			}
		}
		System.out.println("TRUE 1"+precharge.lireRepresentation());
		return true;
	}
//-----------------------------------------------------------------------------------------------------------------------
// Methodes liées aux regles :---------------------------------------------
/*
	protected boolean estRegles() throws IOException {
		// { Regle ';' }
		while (estRegle()) {
			// il doit obligatoirement y avoir un point virgule derriere une Regle
			if (precharge.estPointVirgule()) {
				precharge = lexical.suivant();
			}
			else {
				return false;
			}
		}
		return true; // devrait retourner true mais ne fonctionne plus
						// lorsqu"on met true
	}
	protected boolean estRegle() throws IOException{
		if(estRegleSansPremisse() || estRegleAvecPremisses()){
			return true;
		}
		return false;
	}
	protected boolean estRegleSansPremisse() throws IOException{
	/*	if(estConclusion()){
			return true;
		}*/
/*		return false;
	}
	
	protected boolean estRegleAvecPremisses() throws IOException{
		return false;
	}
	/**
	 * retourne la base de faits
	 */
	public HashMap<String,FaitAbstrait> getBaseDeFaits(){
		return baseDeFaits;
	}
	/**
	 * 
	 * @return Le produit fini soit la base de regles
	 */
	public HashMap<String, RegleAbstraite> getBaseDeRegles(){
		return baseDeRegles;
	}
}
