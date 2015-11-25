package sysexp.builders.lorraine;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.LinkedList;

import sysexp.builders.Builder;
import sysexp.modele.FaitAbstrait;
import sysexp.modele.FaitBooleen;
import sysexp.modele.FaitEntier;
import sysexp.modele.FaitSymbolique;
import sysexp.modele.RegleAbstraite;
import sysexp.modele.RegleAvecPremisse;
import sysexp.modele.RegleSansPremisse;
import sysexp.modele.visitor.ConclusionBooleeneAffirmation;
import sysexp.modele.visitor.ConclusionBooleeneNegation;
import sysexp.modele.visitor.ConclusionEntiereExpression;
import sysexp.modele.visitor.ConclusionEntiereNomFait;
import sysexp.modele.visitor.ConclusionSymboliqueConstante;
import sysexp.modele.visitor.ConclusionSymboliqueNomFait;
import sysexp.modele.visitor.Forme;
import sysexp.modele.visitor.PremisseBooleeneAffirmation;
import sysexp.modele.visitor.PremisseBooleeneNegation;
import sysexp.modele.visitor.PremisseEntiereExpression;
import sysexp.modele.visitor.PremisseEntiereNomFait;
import sysexp.modele.visitor.PremisseSymboliqueConstante;
import sysexp.modele.visitor.PremisseSymboliqueNomFait;

/**
 * Cette classe represente le ConcreteBuilder du design pattern builder
 * Cette classe s'assure de l'analyse Syntaxique ET sémantique de la base de règles
 * Ainsi que la construction de la base de règles
 * @author Jasmine
 */

public class BuilderLorraine implements Builder{
	 /**
     * Analyseur lexical.
     */
	protected Lexical lexical;
	
	/**
     * Dernier jeton precharge.
     */
	protected Jeton precharge;
	
	/**
	 * Declaration des Faits Entiers.
	 */
	protected HashMap<String, FaitEntier> faitsDeclaresEntier = new HashMap<String, FaitEntier>();
	/**
	 * Declaration des Faits Booleens.
	 */
	protected HashMap<String, FaitBooleen> faitsDeclaresBooleen = new HashMap<String, FaitBooleen>();
	/**
	 * Declaration des Faits Symboliques.
	 */
	protected HashMap<String, FaitSymbolique> faitsDeclaresSymbolique = new HashMap<String, FaitSymbolique>();
	
	/**
	 * Liste des premisses pour les regles
	 */
	protected LinkedList<Forme> condition = new LinkedList<Forme>();
	
	/**
	 * 
	 */
	protected FaitAbstrait faitAbs;
	protected PremisseBooleeneAffirmation premisseBoolAffirmation;
	protected PremisseBooleeneNegation premisseBoolNegation;
	protected PremisseEntiereExpression premisseEntExp;
	protected PremisseEntiereNomFait premisseEntFait;
	protected PremisseSymboliqueConstante premisseSymboConst;
	protected PremisseSymboliqueNomFait premisseSymboFait;
	protected String expressionEntiere;
	
	/**
	 * Si la conclusion est une conclusion booleenne affirmative
	 * on l'enregistre dans cette variable.
	 */
	protected ConclusionBooleeneAffirmation conclusionBoolAffirmation = null;
	/**
	 * Si la conclusion est une conclusion booleenne de la forme 'non fait_booleen'
	 * on l'enregistre dans cette variable.
	 */
	protected ConclusionBooleeneNegation conclusionBoolNegation = null;
	/**
	 * Si la conclusion est une conclusion entiere de la forme 'fait = expression'
	 * on l'enregistre dans cette variable.
	 */
	protected ConclusionEntiereExpression conclusionEntExp = null;
	/**
	 * Si la conclusion est une conclusion entiere de la forme 'fait = fait'
	 * on l'enregistre dans cette variable.
	 */
	protected ConclusionEntiereNomFait conclusionEntFait = null;
	/**
	 * Si la conclusion est une conclusion symbolique de la forme 'fait = constante'
	 * on l'enregistre dans cette variable.
	 */
	protected ConclusionSymboliqueConstante conclusionSymboConst = null;
	/**
	 * Si la conclusion est une conclusion symbolique de la forme 'fait = fait'
	 * on l'enregistre dans cette variable.
	 */
	protected ConclusionSymboliqueNomFait conclusionSymboFait = null;
	
	/**
	 * représente la base de regle.
	 */
	protected RegleAbstraite baseDeRegle;
	/**
	 * permet de numéroter les régles
	 */
	protected long numeroRegle;
	/**
	 * enregistre vrai, si c'est une base de connaissance,
	 * sinon false.
	 */
	public boolean test;
	
	/**
	 * Constructeur logique
	 * @param lecteur, le fichier qui contient la base de connaissances
	 */
	public BuilderLorraine(LineNumberReader lecteur) {
		this.lexical = new Lexical(lecteur);
		this.baseDeRegle = null;
	}
	
	public Lexical getlexical(){
		return lexical;
	}
	
	/**
	 * Permet de construire la base de regles
	 * @throws IOException 
	 */
	@Override
	public void buildPart() throws IOException {
		// Pre-chargement du premier jeton.
		precharge = lexical.suivant();
		estBaseDeConnaissance();
		
	}
	
	/**
	 * Creer une regle sans premisse
	 */
	public void nouvelleRegleSansPremisse(){
		if(conclusionBoolAffirmation != null){
			RegleAbstraite nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionBoolAffirmation);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
		}
		if(conclusionBoolNegation != null){
			RegleAbstraite nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionBoolNegation);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
		}
		if(conclusionEntExp != null){
			RegleAbstraite nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionEntExp);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
		}
		if(conclusionEntFait != null){
			RegleAbstraite nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionEntFait);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
		}
		if(conclusionSymboConst != null){
			RegleAbstraite nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionSymboConst);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
		}
		if(conclusionSymboFait != null){
			RegleAbstraite nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionSymboFait);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
		}
		numeroRegle++;
	}
	
	/**
	 * Creer une regle avec premisse
	 */
	public void nouvelleRegleAvecPremisse(){
		LinkedList<Forme> clone = (LinkedList<Forme>) condition.clone();
		condition.clear();
		
		if(conclusionBoolAffirmation != null){
			RegleAbstraite nouvelleRegle = new RegleAvecPremisse(numeroRegle, clone, conclusionBoolAffirmation);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
			
		}
		if(conclusionBoolNegation != null){
			RegleAbstraite nouvelleRegle = new RegleAvecPremisse(numeroRegle, clone, conclusionBoolNegation);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
		}
		if(conclusionEntExp != null){
			RegleAbstraite nouvelleRegle = new RegleAvecPremisse(numeroRegle, clone, conclusionEntExp);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
		}
		if(conclusionEntFait != null){
			RegleAbstraite nouvelleRegle = new RegleAvecPremisse(numeroRegle, clone, conclusionEntFait);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
		}
		if(conclusionSymboConst != null){
			RegleAbstraite nouvelleRegle = new RegleAvecPremisse(numeroRegle, clone, conclusionSymboConst);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
		}
		if(conclusionSymboFait != null){
			RegleAbstraite nouvelleRegle = new RegleAvecPremisse(numeroRegle, clone, conclusionSymboFait);
			nouvelleRegle.ecrireSuccesseur(baseDeRegle);
			baseDeRegle = nouvelleRegle;
		}
		numeroRegle++;
	}
	
	/**
	 * Remet les variables à null
	 * et vide la HashMap qui contient les Premisses.
	 * Cette méthode est executer après l'ajout d'une regle.
	 */
	public void viderVariable() {
	/*	if(!condition.isEmpty()){
		condition.remove();
		}
		 for (int i = 0; i < condition.size(); i++){
			 condition.get(i);
		 }*/
		       
	   
		conclusionBoolAffirmation = null;
		conclusionBoolNegation = null;
		conclusionEntExp = null;
		conclusionEntFait = null;
		conclusionSymboConst = null;
		conclusionSymboFait = null;
	}
	
	/** 
	 * Accesseur.
	 * @return Le produit fini, soit la base de regles
	 */
	public  RegleAbstraite getBaseDeRegles(){
		return baseDeRegle;
	}
	
//Methodes liés a la construction de la base de régles : --------------------------------------------------
	/**
	 * Test si c'est une base de connaissances ou non
	 * @return true, si c'est bien une base de connaissance dans la grammaire Lorraine,
	 * false sinon.
	 * @throws IOException
	 */
	public boolean estBaseDeConnaissance() throws IOException{
		// Appel de la methode associee aux déclarations.
		while(!precharge.estFinExpression()){
				if (!estDeclaration() && !estRegles()) {
				    return test = false;
				}
		}
				// Il faut verifier que nous avons atteint la fin du texte.
				return test = precharge.estFinExpression();
	}

//Vérification syntaxique et sémantique des déclarations :
	/**
	 * Test si c'est une déclaration de faits
	 * @return <b>true</b> : c'est une déclaration.  <br/>
	 * <b>false</b> : ce n'est pas une déclaration.
	 * @throws IOException
	 */
	protected boolean estDeclaration() throws IOException {
		// C'est une déclaration si c'est
		// une declaration_booleene ou une declatation_symbolique ou une declaration_entiere
		if (estDeclarationEntiere() || estDeclarationBooleene() || estDeclarationSymbolique()) {
			return true;
		}
		// Sinon ce n'est pas une déclaration
		return false;
	}
	/**
	 * Test si c'est une déclaration de faits booleens
	 * @return <b>true</b> : c'est une déclaration de faits booleens.  <br/>
	 * <b>false</b> : ce n'est pas une déclaration de faits booleens.
	 * @throws IOException
	 */
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
		return true;//tout les tests ont réussis
	}
	
	/**
	 * Test si c'est une liste de faits booleens.
	 * @return <b>true</b> : c'est une liste de faits booleens, syntaxiquement correcte. </br>
	 * <b>false</b> : ce n'est pas une liste de faits booleens, ou elle contient une erreur de syntaxe.
	 * @throws IOException
	 */
	protected boolean estFaitsBooleens() throws IOException {// Test si l'on a bien une
																// liste de faits booleens
		// Fait_Booleen
		if (!precharge.estFait()) {// Le premier doit etre un fait 
			return false;
		}
		else{
			if(faitsDeclaresBooleen.containsKey(precharge.lireRepresentation())){
				return false;
			}
			//faitsDeclares.put(precharge.lireRepresentation(),new FaitBooleen(precharge.lireRepresentation()));
			FaitBooleen fait = new FaitBooleen(precharge.lireRepresentation());
			faitsDeclaresBooleen.put(precharge.lireRepresentation(),fait);
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
			//	faitsDeclares.put(precharge.lireRepresentation(),new FaitBooleen(precharge.lireRepresentation()));
				faitsDeclaresBooleen.put(precharge.lireRepresentation(),new FaitBooleen(precharge.lireRepresentation()));
				precharge = lexical.suivant();
			}
		}
		return true;
	}
	/**
	 * Test si c'est une déclaration de faits symboliques.
	 * @return <b>true</b> : c'est une déclaration de faits symboliques.  <br/>
	 * <b>false</b> : ce n'est pas une déclaration de faits symboliques.
	 * @throws IOException
	 */
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
	
	/**
	 * Test si c'est une liste de faits symboliques.
	 * @return <b>true</b> : c'est une liste de faits symboliques, syntaxiquement correcte. </br>
	 * <b>false</b> : ce n'est pas une liste de faits symboliques, ou elle contient une erreur de syntaxe.
	 * @throws IOException
	 */
	protected boolean estFaitsSymboliques() throws IOException{
		//Fait_Symbolique
		if(!precharge.estFait()){
			return false;
		}
		else{
			if(faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())){
				return false;
			}
			//faitsDeclares.put(precharge.lireRepresentation(),new FaitSymbolique(precharge.lireRepresentation()));
			faitsDeclaresSymbolique.put(precharge.lireRepresentation(),new FaitSymbolique(precharge.lireRepresentation()));
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
				//faitsDeclares.put(precharge.lireRepresentation(),new FaitSymbolique(precharge.lireRepresentation()));
				faitsDeclaresSymbolique.put(precharge.lireRepresentation(),new FaitSymbolique(precharge.lireRepresentation()));
				precharge = lexical.suivant();
			}
		}
		return true;
	}
	
	/**
	 * Test si c'est une déclaration de faits entiers.
	 * @return <b>true</b> : c'est une déclaration de faits entiers.  <br/>
	 * <b>false</b> : ce n'est pas une déclaration de faits entiers.
	 * @throws IOException
	 */
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
	/**
	 * Test si c'est une liste de faits entiers.
	 * @return <b>true</b> : c'est une liste de faits entiers, syntaxiquement correcte. </br>
	 * <b>false</b> : ce n'est pas une liste de faits entiers, ou elle contient une erreur de syntaxe.
	 * @throws IOException
	 */
	protected boolean estFaitsEntiers() throws IOException{
		//Fait_Entier
		if(!precharge.estFait()){
			return false;
		}
		else{
			if(faitsDeclaresEntier.containsKey(precharge.lireRepresentation())){
				return false;
			}
			//faitsDeclares.put(precharge.lireRepresentation(),new FaitEntier(precharge.lireRepresentation()));
			faitsDeclaresEntier.put(precharge.lireRepresentation(),new FaitEntier(precharge.lireRepresentation()));
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
			//	faitsDeclares.put(precharge.lireRepresentation(),new FaitEntier(precharge.lireRepresentation()));
				faitsDeclaresEntier.put(precharge.lireRepresentation(),new FaitEntier(precharge.lireRepresentation()));
				precharge = lexical.suivant();
			}
		}
		return true;
	}
	
//Vérification syntaxique et sémantique des règles : 
	
	/**
	 * Test si l'on est face à une liste de regles
	 * @return <b>true</b> : c'est une liste de regles </br>
	 * <b>false</b> : ce n'est pas une liste de regles
	 * @throws IOException
	 */
	protected boolean estRegles() throws IOException{
		//{ Regle ';' }
		if (estRegle()){
			// il doit obligatoirement y avoir un point virgule derriere une Regle
			if(precharge.estPointVirgule()){
				precharge = lexical.suivant();
			}else{
				return false;
			}
			return true;
		}
		else{
			return false;
		}
	}
	

	/**
	 * Test si l'on est face à une regle
	 * @return <b>true</b> : c'est une regle </br>
	 * <b>false</b> : ce n'est pas une regle
	 * @throws IOException
	 */
	protected boolean estRegle() throws IOException{
		if(estRegleSansPremisse() || estRegleAvecPremisses()){
			return true;
		}
		return false;
	}

	/**
	 * Test si l'on est face à une regle sans premisse
	 * @return <b>true</b> : c'est une regle sans premisse </br>
	 * <b>false</b> : ce n'est pas une regle sans premisse
	 * @throws IOException
	 */
	protected boolean estRegleSansPremisse() throws IOException{
		if(estConclusion()){
			nouvelleRegleSansPremisse();
			viderVariable();
			return true;
		}
		return false;
	}
	
	/**
	 * Test si l'on est face à une regle avec premisse
	 * @return <b>true</b> : c'est une regle avec premisse </br>
	 * <b>false</b> : ce n'est pas une regle avec premisse
	 * @throws IOException
	 */
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
			return false;
		}
		nouvelleRegleAvecPremisse();
		viderVariable();
		return true;
	}
	
	/**
	 * Test si c'est une condition, soit une liste de prémisse.
	 * @return <b>true</b> : c'est une condition. </br>
	 * <b>false</b> : ce n'est pas une condition.
	 * @throws IOException
	 */
	protected boolean estCondition() throws IOException{
		//Premisse
		if(!estPremisse()){
			return false;
		}
		
		//{ et Premisse } 
		while(precharge.estEt()){
			precharge = lexical.suivant();
			if(!estPremisse()){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Test si c'est une conclusion.
	 * @return <b>true</b> : c'est une conclusion. </br>
	 * <b>false</b> : ce n'est pas une conclusion.
	 * @throws IOException
	 */
	protected boolean estConclusion() throws IOException{
		if(estConclusionBooleene() || estConclusionSymbolique() || estConclusionEntiere()){
			return true;
		}
		return false;
	}
	
	/**
	 * Test si c'est une conclusion booleene.
	 * @return <b>true</b> : c'est une conclusion booleene. </br>
	 * <b>false</b> : ce n'est pas une conclusion booleene.
	 * @throws IOException
	 */
	protected boolean estConclusionBooleene() throws IOException{
		if(estConclusionBooleeneAffirmation() || estConclusionBooleeneNegation()){
			return true;
		}
		return false;
	}
	
	/**
	 * Test si c'est une conclusion booleene de la forme 'fait_booleen'.
	 * @return <b>true</b> : c'est une conclusion booleene. </br>
	 * <b>false</b> : ce n'est pas une conclusion booleene.
	 * @throws IOException
	 */
	protected boolean estConclusionBooleeneAffirmation() throws IOException{
		//Fait_booleen 
		if(faitsDeclaresBooleen.containsKey(precharge.lireRepresentation())){
			conclusionBoolAffirmation = new ConclusionBooleeneAffirmation(new FaitBooleen(precharge.lireRepresentation()));
			precharge = lexical.suivant();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Test si c'est une conclusion booleene de la forme 'non fait_booleen'.
	 * @return <b>true</b> : c'est une conclusion booleene. </br>
	 * <b>false</b> : ce n'est pas une conclusion booleene.
	 * @throws IOException
	 */
	protected boolean estConclusionBooleeneNegation() throws IOException{
		//'non' Fait_Booleen
		if(!precharge.estNon()){
			return false;
		}
		else{
			precharge = lexical.suivant();
			if(!faitsDeclaresBooleen.containsKey(precharge.lireRepresentation())){
				return false;
			}
			else{
				conclusionBoolNegation = new ConclusionBooleeneNegation(new FaitBooleen(precharge.lireRepresentation()));
				precharge = lexical.suivant();
			}
		}
		return true;
	}
	
	/**
	 * Test si c'est une conclusion symbolique.
	 * @return <b>true</b> : c'est une conclusion symbolique. </br>
	 * <b>false</b> : ce n'est pas une conclusion symbolique.
	 * @throws IOException
	 */
	protected boolean estConclusionSymbolique() throws IOException{
		if(estConclusionSymboliqueConstante() || estConclusionSymboliqueNomFait()){
			return true;
		}
		return false;
	}
	
	/**
	 * Test si c'est une conclusion symbolique de la forme 'fait_symbolique = constante'.
	 * @return <b>true</b> : c'est une conclusion symbolique. </br>
	 * <b>false</b> : ce n'est pas une conclusion symbolique.
	 * @throws IOException
	 */
	protected boolean estConclusionSymboliqueConstante() throws IOException{
		//Fait_symbolique, fait, contenu dans la hashMap
		if(precharge.estFait()){
			if(!faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())){
				return false;
			}
			faitAbs = new FaitSymbolique(precharge.lireRepresentation());
			precharge = lexical.suivant();
		}
		
		// '=' 
		if(precharge.estComparateurEgal()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		//Constante symbolique : jeton fait mais n'est dans aucune hashMap
		if(precharge.estFait()){
			if(
				faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())
				|| faitsDeclaresEntier.containsKey(precharge.lireRepresentation())
				|| faitsDeclaresBooleen.containsKey(precharge.lireRepresentation())
			){
				return false;
			}
			//c'est bien une constante
			conclusionSymboConst = new ConclusionSymboliqueConstante((FaitSymbolique)faitAbs,"=", precharge.lireRepresentation());
			precharge = lexical.suivant();
		}
		return true;
	}
	
	/**
	 * Test si c'est une conclusion symbolique de la forme 'fait_symbolique = fait'.
	 * @return <b>true</b> : c'est une conclusion symbolique. </br>
	 * <b>false</b> : ce n'est pas une conclusion symbolique.
	 * @throws IOException
	 */
	protected boolean estConclusionSymboliqueNomFait() throws IOException{
		//Fait_symbolique
		if(precharge.estFait()){
			if(!faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())){
				return false;
			}
			faitAbs = new FaitSymbolique(precharge.lireRepresentation());
			precharge = lexical.suivant();
		}//c'est bien un fait symbolique
				
		// '=' 
		if(precharge.estComparateurEgal()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		//Fait_symbolique
		if(precharge.estFait()){
			if(!faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())){
				return false;
			}
			
			conclusionSymboFait = new ConclusionSymboliqueNomFait((FaitSymbolique) faitAbs,"=",new FaitSymbolique(precharge.lireRepresentation()));
			precharge = lexical.suivant();
		}
		return true;
	}
	
	/**
	 * Test si c'est une conclusion entiere.
	 * @return <b>true</b> : c'est une conclusion entiere. </br>
	 * <b>false</b> : ce n'est pas une conclusion entiere.
	 * @throws IOException
	 */
	protected boolean estConclusionEntiere() throws IOException{
		if(estConclusionEntiereExpression() || estConclusionEntiereNomFait()){
			return true;
		}
		return false;
	}
	
	/**
	 * Test si c'est une conclusion entiere de la forme 'fait_entier = expression'.
	 * @return <b>true</b> : c'est une conclusion entiere. </br>
	 * <b>false</b> : ce n'est pas une conclusion entiere.
	 * @throws IOException
	 */
	protected boolean estConclusionEntiereExpression() throws IOException{
		//Fait_entier
		if(faitsDeclaresEntier.containsKey(precharge.lireRepresentation())){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		// '='
		if(precharge.estComparateurEgal()){
			precharge = lexical.suivant();
		}
		else{
			return false;
		}
		
		//Expression_Entiere
		if(!estExpressionEntiere()){
			return false;
		}
		return true;
	}
	
	/**
	 * Test si c'est une conclusion entiere de la forme 'fait_entier = fait'.
	 * @return <b>true</b> : c'est une conclusion entiere. </br>
	 * <b>false</b> : ce n'est pas une conclusion entiere.
	 * @throws IOException
	 */
	protected boolean estConclusionEntiereNomFait() throws IOException{
		//Fait_entier
		if(faitsDeclaresEntier.containsKey(precharge.lireRepresentation())){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		// '='
		if(precharge.estComparateurEgal()){
			precharge = lexical.suivant();
		}
		else{
			return false;
		}
		
		//Fait_entier
		if(faitsDeclaresEntier.containsKey(precharge.lireRepresentation())){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		return true;
	}
	
	protected boolean estPremisse() throws IOException{
		if(estPremisseBooleene() || estPremisseSymbolique() || estPremisseEntiere()){
			return true;
		}
		return false;
	}
	
	protected boolean estPremisseBooleene() throws IOException{
		if(estPremisseBooleeneAffirmation() || estPremisseBooleeneNegation()){
			return true;
		}
		else{
			return false;
		}
	}
	
	protected boolean estPremisseBooleeneAffirmation() throws IOException{
		//Fait_booleen
		if(faitsDeclaresBooleen.containsKey(precharge.lireRepresentation())){ // on vérifie que la chaine de caractere contenu 
			premisseBoolAffirmation = new PremisseBooleeneAffirmation(new FaitBooleen(precharge.lireRepresentation()));
			condition.add(premisseBoolAffirmation);
			precharge = lexical.suivant();
			return true;
		}
		else{
			return false;
		}
	}
	
	protected boolean estPremisseBooleeneNegation() throws IOException{
		// 'non' Fait_Booleen
		if(!precharge.estNon()){
			return false;
		}
		
		while(precharge.estNon()){
			precharge = lexical.suivant();
			if(!faitsDeclaresBooleen.containsKey(precharge.lireRepresentation())){
				return false;
			}
			else{
				premisseBoolNegation = new PremisseBooleeneNegation(new FaitBooleen(precharge.lireRepresentation()));
				condition.add(premisseBoolNegation);	
				precharge = lexical.suivant();
			}
		}
		return true;
	}
	
	protected boolean estPremisseSymbolique() throws IOException{
		if(estPremisseSymboliqueConstante() || estPremisseSymboliqueNomFait()){
			return true;
		}
		else{
			return false;
		}
	}
	
	protected boolean estPremisseSymboliqueConstante() throws IOException{
		//Fait_symbolique, fait, contenu dans la hashMap
		if(precharge.estFait()){
			if(!faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())){
				return false;
			}
			faitAbs = new FaitSymbolique(precharge.lireRepresentation());
			precharge = lexical.suivant();
		}
		
		// '=' 
		if(precharge.estComparateurEgal()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		//Constante symbolique : jeton fait mais n'est dans aucune hashMap
		if(precharge.estFait()){
			if(
				faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())
				|| faitsDeclaresEntier.containsKey(precharge.lireRepresentation())
				|| faitsDeclaresBooleen.containsKey(precharge.lireRepresentation())
			 ){
				return false;
			}
			//c'est bien une constante
			premisseSymboConst = new PremisseSymboliqueConstante((FaitSymbolique) faitAbs, "=", precharge.lireRepresentation());
			condition.add(premisseSymboConst); // on ajoute la premisse a la liste
			precharge = lexical.suivant();
		}
		return true;
	}
	
	protected boolean estPremisseSymboliqueNomFait() throws IOException{
		//Fait_symbolique
		if(precharge.estFait()){
			if(!faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())){
				return false;
			}
			faitAbs = new FaitSymbolique(precharge.lireRepresentation());
			precharge = lexical.suivant();
		}//c'est bien un fait symbolique
				
		// '/=' 
		if(precharge.estComparateurDifferent()){
			precharge = lexical.suivant();
		}
		else {// ce n'est pas '/='
			return false;
		}
		
		//Fait_symbolique
		if(precharge.estFait()){
			if(!faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())){
				return false;
			}
			
			condition.add(new PremisseSymboliqueNomFait((FaitSymbolique) faitAbs, "/=", new FaitSymbolique(precharge.lireRepresentation())));
			precharge = lexical.suivant();
		}
		
		return true;
	}
	
	protected boolean estPremisseEntiere() throws IOException{
		if(estPremisseEntiereExpression() || estPremisseEntiereNomFait()){
			return true;
		}
		return false;
	}
	
	protected boolean estPremisseEntiereExpression() throws IOException{
		//Fait_entier
		if(faitsDeclaresEntier.containsKey(precharge.lireRepresentation())){
			//on passe le fait
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		// Comparateur
		if(estComparateur()){
			//on passe le comparateur
			precharge = lexical.suivant();
		}
		else{
			return false;
		}
		
		//Expression_Entiere
		if(!estExpressionEntiere()){
			return false;
		}
		return true;
	}
	
	protected boolean estPremisseEntiereNomFait() throws IOException{
		//Fait_entier
		if(faitsDeclaresEntier.containsKey(precharge.lireRepresentation())){
			//on passe le fait
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		// Comparateur
		if(estComparateur()){
			//on passe le comparateur
			precharge = lexical.suivant();
		}
		else{
			return false;
		}
		
		//Fait_entier
		if(faitsDeclaresEntier.containsKey(precharge.lireRepresentation())){
			//on passe le fait
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		return true;
	}
	/**
	 * Test si le jeton est un comparateur
	 * @return
	 * @throws IOException
	 */
	protected boolean estComparateur() throws IOException{
		if(!precharge.estComparateurEgal() && !precharge.estComparateurDifferent() &&
		   !precharge.estComparateurInferieur() && !precharge.estComparateurInferieurOuEgal() &&
		   !precharge.estComparateurSuperieur() && !precharge.estComparateurSuperieurOuEgal()
		   ){
			 return false;
		}
		return true;
	}
	
	/**
	 * Methode associee a la regle "ExpressionEntiere".
	 *
	 * @return true si la regle est satisfaite sinon false.
	 * @throw IOException si l'analyseur lexical ne parvient pas a lire
	   l'expression.
     */
	protected boolean estExpressionEntiere() throws IOException{
		// [Additif]
		if(precharge.estOperateurPlus() || precharge.estOperateurMoins()){
			// L'operateur est present : il faut passer au jeton suivant.
			precharge = lexical.suivant();
		}
		// Terme
		if(!estTerme()){
			return false;
		}
		//{Additif Terme}
		while(precharge.estOperateurPlus() || precharge.estOperateurMoins()){
			// Passe l'operateur
			precharge = lexical.suivant();
			if(!estTerme()){
				return false;
			}
		}
		// La regle expressionEntiere est satisfaite
		return true;
	}
	
	/**
     * Methode associee a la regle "Terme".
     *
     * @return true si la regle est satisfaite sinon false.
     * @throw IOException si l'analyseur lexical ne parvient pas a lire
     *   l'expression.
     */
	protected boolean estTerme() throws IOException{
		//Facteur
		if(!estFacteur()){
			return false;
		}
		//{Multiplicatif Facteur }
		while(precharge.estOperateurMultiplie() || precharge.estOperateurDivise()){
			// Passer l'operateur
			precharge = lexical.suivant();
			if(!estFacteur()){
				return false;
			}
		}
		//La regle est satisfaite
		return true;
	}
	
	 /**
     * Methode associee a la regle "Facteur".
     *
     * @return true si la regle est satisfaite sinon false.
     * @throw IOException si l'analyseur lexical ne parvient pas a lire
     *   l'expression.
     */
	  protected boolean estFacteur() throws IOException {
		// Si le jeton precharge est une parenthese ouvrante, il s'agit d'une
		// nouvelle expression parenthesee.
		if (precharge.estParentheseOuvrante()) {
			// Passer la parenthese.
			precharge = lexical.suivant();

			 // Appel de la methode associee a la regle "Expression".
			if (!estExpressionEntiere()) {
				return false;
			}

			// Le jeton precharge doit etre une parenthese fermante.
			if (! precharge.estParentheseFermante()) {
				return false;
			}

			// Passer la parenthese fermante.
			precharge = lexical.suivant();
			// La regle est satisfaite.
			return true;
		}
		
		// Le jeton precharge est un entier.
		if (precharge.estEntier()) {
			    // Passer l'entier.
			    precharge = lexical.suivant();
			    // La regle est satisfaite.
			    return true;
		}
		
		if (faitsDeclaresEntier.containsKey(precharge.lireRepresentation())) {
		    // Passer le fait
		    precharge = lexical.suivant();
		    // La regle est satisfaite.
		    return true;
		}

		// Le jeton est inconnu.
		return false;

	  }
}
