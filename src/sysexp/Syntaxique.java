package sysexp;

import java.io.IOException;
import java.util.HashMap;

import sysexp.builders.lorraine.Jeton;
import sysexp.builders.lorraine.Lexical;

public class Syntaxique {
	protected Lexical lexical;
	protected Jeton precharge;
	public HashMap<String, String> base_de_faits = new HashMap<String, String>();
	//jeton : 'faits_booleens', 'faits_entiers' ou 'faits_symboliques'
	// String : 'faits'
	public Syntaxique(Lexical lexical) {
		this.lexical = lexical;
	}

	public Lexical lireLexical() {
		return lexical;
	}
	/*
	 * Methode qui vérifie si le fichier est écrit correctement :
	 */
	public boolean verifier() throws IOException {
		// Pre-chargement du premier jeton.
		precharge = lexical.suivant();

		// Appel de la methode associee a la regle "Declaration".
		// Appel de la methode associee a la regle "Regle".
		while (!precharge.estFinExpression()) {// Tant que l'on a pas atteint la
												// fin du fichiers
			if (!estDeclaration() && !estRegles()) {
				return false;
			}
		}
		// Il faut verifier que nous avons atteint la fin du texte.
		return precharge.estFinExpression();

		// return estDeclaration() && precharge.estFinExpression();
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
			base_de_faits.put(precharge.lireRepresentation(),"faits_booleens");
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
				base_de_faits.put(precharge.lireRepresentation(),"faits_booleens");
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
			base_de_faits.put(precharge.lireRepresentation(),"faits_symboliques");
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
				base_de_faits.put(precharge.lireRepresentation(),"faits_symboliques");
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
			base_de_faits.put(precharge.lireRepresentation(),"faits_entiers");
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
				base_de_faits.put(precharge.lireRepresentation(),"faits_entiers");
				precharge = lexical.suivant();
			}
		}
		return true;
	}

//-----------------------------------------------------------------------------------------------------------------------
		// Methodes liées aux regles :---------------------------------------------
		protected boolean estRegles() throws IOException{
				//{ Regle ';' }
				while (estRegle()){
					//precharge = lexical.suivant();
					// il doit obligatoirement y avoir un point virgule derriere une Regle
					if(precharge.estPointVirgule()){
						precharge = lexical.suivant();
					//	return true;
					}else{
						return false;
					}
				}
				
				return false; // devrait retourner true mais ne fonctionne plus lorsqu"on met true
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
				return false;
			}
			return true;
			
		}
		
		// Méthodes liées aux Conclusions
		protected boolean estConclusion() throws IOException{
			if(estConclusionBooleene() || estConclusionSymbolique() || estConclusionEntiere()){
				return true;
			}
			return false;
		}
		protected boolean estConclusionBooleene() throws IOException{
			//Fait_booleen ou 'non' Fait_Booleen
			if(!precharge.estNon()  && !precharge.estFait()){//estFait suffisant car si ce n'est pas un fait, on est sur 
				return false;
			}
			if(base_de_faits.containsKey(precharge.lireRepresentation())){ // on vérifie que la chaine de caractere contenu 
				if(base_de_faits.get(precharge.lireRepresentation()).equals("faits_booleens")){
					precharge = lexical.suivant();
				}
				else{
				return false;
				}
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
		
		protected boolean estConclusionSymbolique() throws IOException{
			//Fait_symbolique
			if(!precharge.estFait()){
				return false;
			}else if(base_de_faits.containsKey(precharge.lireRepresentation())) {
				if(base_de_faits.get(precharge.lireRepresentation()).equals("faits_symboliques")){
					precharge = lexical.suivant();
				}
				else{
				return false;
				}
			}
			// '=' ou '/='			
			if(precharge.estComparateurEgal() || precharge.estComparateurDifferent()){
				precharge = lexical.suivant();
			}else{
				return false;
			}
			//Constante symbolique = Fait_symbolique
			if(!precharge.estFait()){
				return false;
			}
			else{
				precharge = lexical.suivant();
			}
			return true;
		}
	
		protected boolean estConclusionEntiere() throws IOException{
			//Fait_entier
			if(!precharge.estFait()){
				return false;
			}else if(base_de_faits.containsKey(precharge.lireRepresentation())){
				precharge = lexical.suivant();
			}else{
				return false;
			}
			
			//Comparateur
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
		
		protected boolean estComparateur() throws IOException{//Pas super joli :
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
			// Le jeton precharge est un entier.
			if (precharge.estEntier()) {
			    // Passer l'entier.
			    precharge = lexical.suivant();
			    // La regle est satisfaite.
			    return true;
			}
			System.out.println(precharge.lireRepresentation());
			if(precharge.estFait()){
				 precharge = lexical.suivant();
				 System.out.println(precharge.lireRepresentation());
			}
			System.out.println("bouh2 "+ precharge.lireRepresentation());
			
			if(precharge.estParentheseOuvrante()){
				System.out.println("bouh 3"+ precharge.lireRepresentation());
				// Passer la parenthese.
				precharge = lexical.suivant();
				// Appel de la methode associee a la regle "Declaration".
				if(!estExpressionEntiere()){
					System.out.println("bouh "+ precharge.lireRepresentation());
					return false;
				}
				// Le jeton precharge doit etre une parenthese fermante.
				if(precharge.estParentheseFermante()){
					// Passer la parenthese fermante.
					precharge = lexical.suivant();
					//La regle est satisfaite.
					return true;
				}
			}
			// Le jeton est inconnu.
			return false;
		}
		
		// Methodes liées aux conditions
		protected boolean estCondition() throws IOException{
			//Premisse
			if(!estPremisse()){
				return false;
			}
			
			//{ et Premisse } // FAUX DANS 'fortune > 10000 ' car c'est une conclusion entiere
			//					la premisse est égal a fortune seulement :/
			//					PAS de souci avec les profession = medecin
			while(precharge.estEt()){
				precharge = lexical.suivant();
				if(!estPremisse()){
					return false;
				}
			}
			
			return true;
		}
		
		protected boolean estPremisse() throws IOException{
			//Au niveau Semantique :
			//PremisseBooleene = ConclusionBooleene
			//PremisseSymbolique = ConclusionSymbolique
			//PremisseEntiere = ConclusionEntiere
			//Syntaxiquement on peut donc faire appel a ces methodes :
			if(!estConclusionBooleene() && !estConclusionSymbolique() && !estConclusionEntiere()){
				
				return false;
			}
			return true;
		}
}
