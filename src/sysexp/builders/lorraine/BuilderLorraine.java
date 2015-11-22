package sysexp.builders.lorraine;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.LinkedList;

import sysexp.builders.Builder;
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
 * Cette classe represente le ConcreteBuilder
 * Cette classe doit assurer l'analyse Syntaxique ET sémantique de la base de règles
 * Ainsi que la construction de la base de règles
 * @author Jasmine
 *
 */
public class BuilderLorraine implements Builder{
	protected Lexical lexical;
	protected Jeton precharge;
	//protected HashMap<String, FaitAbstrait> faitsDeclares = new HashMap<String, FaitAbstrait>();
	protected HashMap<String, FaitEntier> faitsDeclaresEntier = new HashMap<String, FaitEntier>();
	protected HashMap<String, FaitBooleen> faitsDeclaresBooleen = new HashMap<String, FaitBooleen>();
	protected HashMap<String, FaitSymbolique> faitsDeclaresSymbolique = new HashMap<String, FaitSymbolique>();
	
	protected HashMap<String, RegleAbstraite> baseDeRegles = new HashMap<String, RegleAbstraite>();
	
	//protected TreeSet<Forme> condition;
	protected LinkedList<Forme> condition = new LinkedList<Forme>();
	
	protected PremisseBooleeneAffirmation premisseBoolAffirmation;
	protected PremisseBooleeneNegation premisseBoolNegation;
	protected PremisseEntiereExpression premisseEntExp;
	protected PremisseEntiereNomFait premisseEntFait;
	protected PremisseSymboliqueConstante premisseSymboConst;
	protected PremisseSymboliqueNomFait premisseSymboFait;
	protected String premisse;
	protected String conclusion;
	protected String expressionEntiere;
	
	protected ConclusionBooleeneAffirmation conclusionBoolAffirmation = null;
	protected ConclusionBooleeneNegation conclusionBoolNegation = null;
	protected ConclusionEntiereExpression conclusionEntExp = null;
	protected ConclusionEntiereNomFait conclusionEntFait = null;
	protected ConclusionSymboliqueConstante conclusionSymboConst = null;
	protected ConclusionSymboliqueNomFait conclusionSymboFait = null;
	
	protected RegleAbstraite nouvelleRegle;
	protected long numeroRegle;
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
	
	public void nouvelleRegleSansPremisse(){
		if(conclusionBoolAffirmation != null){
			nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionBoolAffirmation);
		}
		if(conclusionBoolNegation != null){
			nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionBoolNegation);
		}
		if(conclusionEntExp != null){
			nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionEntExp);
		}
		if(conclusionEntFait != null){
			nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionEntFait);
		}
		if(conclusionSymboConst != null){
			nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionSymboConst);
		}
		if(conclusionSymboFait != null){
			nouvelleRegle = new RegleSansPremisse(numeroRegle, conclusionSymboFait);
		}
		numeroRegle++;
	}
	
	public void nouvelleRegleAvecPremisse(){
		if(conclusionBoolAffirmation != null){
			nouvelleRegle = new RegleAvecPremisse(numeroRegle, condition, conclusionBoolAffirmation);
		}
		if(conclusionBoolNegation != null){
			nouvelleRegle = new RegleAvecPremisse(numeroRegle, condition, conclusionBoolNegation);
		}
		if(conclusionEntExp != null){
			nouvelleRegle = new RegleAvecPremisse(numeroRegle, condition, conclusionEntExp);
		}
		if(conclusionEntFait != null){
			nouvelleRegle = new RegleAvecPremisse(numeroRegle, condition, conclusionEntFait);
		}
		if(conclusionSymboConst != null){
			nouvelleRegle = new RegleAvecPremisse(numeroRegle, condition, conclusionSymboConst);
		}
		if(conclusionSymboFait != null){
			nouvelleRegle = new RegleAvecPremisse(numeroRegle, condition, conclusionSymboFait);
		}
		numeroRegle++;
	}
	
	public void viderVariable() {
		condition.clear();
		conclusionBoolAffirmation = null;
		conclusionBoolNegation = null;
		conclusionEntExp = null;
		conclusionEntFait = null;
		conclusionSymboConst = null;
		conclusionSymboFait = null;
	}
	
	/**
	 * retourne les faits déclarés
	 */
	public HashMap<String,FaitEntier> getFaitsDeclaresEntier(){
		return faitsDeclaresEntier;
	}
	
	/**
	 * 
	 * @return Le produit fini soit la base de regles
	 */
	public HashMap<String, RegleAbstraite> getBaseDeRegles(){
		return baseDeRegles;
	}
	
//Methodes liés a la construction de la base de régles : --------------------------------------------------
	
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
	
	protected boolean estDeclaration() throws IOException {
		// C'est une déclaration si c'est
		// une declaration_booleene ou une declatation_symbolique ou une declaration_entiere
		if (estDeclarationEntiere() || estDeclarationBooleene() || estDeclarationSymbolique()) {
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
		return true;//tout les tests ont réussis
	}
	
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
	
	protected boolean estRegle() throws IOException{
		if(estRegleSansPremisse() || estRegleAvecPremisses()){
			return true;
		}
		return false;
	}
	
	protected boolean estRegleSansPremisse() throws IOException{
		if(estConclusion()){
			nouvelleRegleSansPremisse();
			viderVariable();
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
			return false;
		}
		nouvelleRegleAvecPremisse();
		viderVariable();
		return true;
	}
	
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
	
	protected boolean estConclusion() throws IOException{
		if(estConclusionBooleene() || estConclusionSymbolique() || estConclusionEntiere()){
			return true;
		}
		return false;
	}
	
	protected boolean estConclusionBooleene() throws IOException{
		if(estConclusionBooleeneAffirmation() || estConclusionBooleeneNegation()){
			return true;
		}
		return false;
	}
	
	protected boolean estConclusionBooleeneAffirmation() throws IOException{
		//Fait_booleen 
		if(faitsDeclaresBooleen.containsKey(precharge.lireRepresentation())){
			conclusionBoolAffirmation = new ConclusionBooleeneAffirmation(precharge.lireRepresentation());
			precharge = lexical.suivant();
			return true;
		}else{
			return false;
		}
	}
	
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
				conclusionBoolNegation = new ConclusionBooleeneNegation(precharge.lireRepresentation());
				precharge = lexical.suivant();
			}
		}
		return true;
	}
	
	protected boolean estConclusionSymbolique() throws IOException{
		if(estConclusionSymboliqueConstante() || estConclusionSymboliqueNomFait()){
			return true;
		}
		return false;
	}
	
	protected boolean estConclusionSymboliqueConstante() throws IOException{
		//Fait_symbolique, fait, contenu dans la hashMap
		if(precharge.estFait()){
			if(!faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())){
				return false;
			}
			conclusion = precharge.lireRepresentation();
			precharge = lexical.suivant();
		}
		
		// '=' 
		if(precharge.estComparateurEgal()){
			conclusion = " "+precharge.lireRepresentation();
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
			conclusion = " "+precharge.lireRepresentation();
			conclusionSymboConst = new ConclusionSymboliqueConstante(conclusion);
			//Verifier s'il faut vider la chaine condition ou pas; je ne pense pas
			precharge = lexical.suivant();
		}
		return true;
	}
	
	protected boolean estConclusionSymboliqueNomFait() throws IOException{
		//Fait_symbolique
		if(precharge.estFait()){
			if(!faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())){
				return false;
			}
			conclusion = precharge.lireRepresentation();
			precharge = lexical.suivant();
		}//c'est bien un fait symbolique
				
		// '=' 
		if(precharge.estComparateurEgal()){
			conclusion = " "+precharge.lireRepresentation();
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		//Fait_symbolique
		if(precharge.estFait()){
			if(!faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())){
				return false;
			}
			conclusion = " "+precharge.lireRepresentation();
			conclusionSymboFait = new ConclusionSymboliqueNomFait(conclusion);
			precharge = lexical.suivant();
		}
		return true;
	}
	
	protected boolean estConclusionEntiere() throws IOException{
		if(estConclusionEntiereExpression() || estConclusionEntiereNomFait()){
			return true;
		}
		return false;
	}
	
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
			premisseBoolAffirmation = new PremisseBooleeneAffirmation(precharge.lireRepresentation());
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
				premisseBoolNegation = new PremisseBooleeneNegation(precharge.lireRepresentation());
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
			premisse = precharge.lireRepresentation();
			precharge = lexical.suivant();
		}
		
		// '=' 
		if(precharge.estComparateurEgal()){
			premisse += " "+precharge.lireRepresentation();
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
			premisse += " "+precharge.lireRepresentation();
			premisseSymboConst = new PremisseSymboliqueConstante(premisse);
			condition.add(premisseSymboFait); // on ajoute la premisse a la liste
			premisse = "";// on vide la chaine 
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
			premisse = precharge.lireRepresentation();
			precharge = lexical.suivant();
		}//c'est bien un fait symbolique
				
		// '/=' 
		if(precharge.estComparateurDifferent()){
			premisse+= " "+precharge.lireRepresentation();
			precharge = lexical.suivant();
		}
		else {// ce n'est pzs '/='
			return false;
		}
		
		//Fait_symbolique
		if(precharge.estFait()){
			if(!faitsDeclaresSymbolique.containsKey(precharge.lireRepresentation())){
				return false;
			}
			premisse+= " "+precharge.lireRepresentation();
			premisseSymboFait = new PremisseSymboliqueNomFait(premisse);
			condition.add(premisseSymboFait);
			premisse = "";// on vide la chaine 
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
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		// Comparateur
		if(estComparateur()){
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
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		// Comparateur
		if(estComparateur()){
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
