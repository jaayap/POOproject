package sysexp;
import java.io.IOException;

/**
 * Classe representant un analyseur syntaxique de la grammaire.
 */

public class Syntaxique_old {
	/**
	 * Analyseur lexical.
     */
    protected Lexical lexical;

    /**
     * Dernier jeton precharge.
     */
    protected Jeton precharge;
    
	 /**
     * Constructeur logique.
     *
     * @param lecteur la valeur de {@link Syntaxique#lexical}.
     */
	public Syntaxique_old(Lexical lexical){
		this.lexical = lexical;
	}
	
	/**
     * Accesseur.
     *
     * @return la valeur de {@link Syntaxique#lexical}.
     */
    public Lexical lireLexical() {
	return lexical;
    }
    
    /**
     * Analyse l'expression connectee a l'analyseur lexical.
     *
     * @return true si l'expression est syntaxiquement correcte sinon false.
     * @throw IOException si l'analyseur lexical ne parvient pas a lire
     *   l'expression.
     */
	public boolean verifier() throws IOException{
		// Pre-chargement du premier jeton.
		precharge = lexical.suivant();
	
		// Appel de la methode associee a la regle "Declaration".
		// Appel de la methode associee a la regle "Regle".
		while(!precharge.estFinExpression()){
			if (!estDeclaration() && !estRegles()) {
					 return false;
			}
		}
		// Il faut verifier que nous avons atteint la fin du texte.
		return precharge.estFinExpression();

		//return estDeclaration() && precharge.estFinExpression();
	}
	
	//méthodes liées aux Déclarations
	/**
	 * Methode associee a la regle "Declaration".
	 *
	 * @return true si la regle est satisfaite sinon false.
	 * @throw IOException si l'analyseur lexical ne parvient pas a lire
	   l'expression.
     */
	protected boolean estDeclaration() throws IOException{
		
		// est declaration_booleene ou declatation_symbolique ou declaration_entiere
		if(estDeclarationBooleene() || estDeclarationSymbolique() || estDeclarationEntiere()){
			return true;
		}
		return false;
	}
	/**
	 * Methode associee a la regle "Declaration Booleene".
	 *
	 * @return true si la regle est satisfaite sinon false.
	 * @throw IOException si l'analyseur lexical ne parvient pas a lire
	   l'expression.
     */
	protected boolean estDeclarationBooleene() throws IOException{

		//'faits_booleens' : mot clé
		if(precharge.estFaits_Booleens()){	
			precharge = lexical.suivant();
		}else{
			return false;
		}
		//'='
		if(precharge.estComparateurEgal()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		
		//'Faits_Booleens' : liste de faits   _______________HashMap<Fait, listeFaits>
		if(!estFaitsBooleens()){
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
	 * Methode associee a la regle "Declaration Symbolique".
	 *
	 * @return true si la regle est satisfaite sinon false.
	 * @throw IOException si l'analyseur lexical ne parvient pas a lire
	   l'expression.
     */
	protected boolean estDeclarationSymbolique() throws IOException{
		// 'faits_symboliques'  : mot clé
		if(precharge.estFaits_Symboliques()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		//'='
		if(precharge.estComparateurEgal()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		//Faits_Symboliques
		if(!estFaitsSymboliques()){
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
	 * Methode associee a la regle "Declaration Entiere".
	 *
	 * @return true si la regle est satisfaite sinon false.
	 * @throw IOException si l'analyseur lexical ne parvient pas a lire
	   l'expression.
     */
	protected boolean estDeclarationEntiere() throws IOException{
		// 'faits_entiers' : mot clé
		if(precharge.estFaits_Entiers()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		//'='
		if(precharge.estComparateurEgal()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		// Faits_Entiers
		if(!estFaitsEntiers()){
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
	 * Methode associee a la regle "Faits Booleens".
	 *
	 * @return true si la regle est satisfaite sinon false.
	 * @throw IOException si l'analyseur lexical ne parvient pas a lire
	   l'expression.
     */
	protected boolean estFaitsBooleens() throws IOException{
		//Fait_Booleen
		if(!estFaitBooleen()){
			return false;
		}
		// { ',' Fait_Booleen }
		while(precharge.estVirgule()){
			//on passe au suivant
			precharge = lexical.suivant();
			//Deriere une virgule il doit y avoir un fait_booleen
			if(!estFaitBooleen()){
				return false;
			}
		}
		return true;
	}
	/**
	 * Methode associee a la regle "Fait Booleen".
	 *
	 * @return true si la regle est satisfaite sinon false.
	 * @throw IOException si l'analyseur lexical ne parvient pas a lire
	   l'expression.
     */
	protected boolean estFaitBooleen() throws IOException{
		if(!estIdentificateur()){
			return false;
		}
		return true;
		
	}
	
	protected boolean estFaitsSymboliques() throws IOException{
		//Fait_Symbolique
		if(!estFaitSymbolique()){
			return false;
		}
		// { ',' Fait_Symbolique }
		while(precharge.estVirgule()){
			//on passe au suivant
			precharge = lexical.suivant();
			//Deriere une virgule il doit y avoir un Fait_Symbolique
			if(!estFaitSymbolique()){
				return false;
			}
		}
		return true;
	}
	
	protected boolean estFaitSymbolique() throws IOException{
		if(!estIdentificateur()){
			return false;
		}
		return true;
		
	}
	
	protected boolean estFaitsEntiers() throws IOException{
		//Fait_Entier
		if(!estFaitEntier()){
			return false;
		}
		// { ',' Fait_Entier }
		while(precharge.estVirgule()){
			//on passe au suivant
			precharge = lexical.suivant();
			//Deriere une virgule il doit y avoir un fait_Entier
			if(!estFaitEntier()){
				return false;
			}
		}
		return true;
	}
	
	protected boolean estFaitEntier() throws IOException{
		if(!estIdentificateur()){
			return false;
		}
		return true;
	}
	
	protected boolean estIdentificateur() throws IOException{
		//Le premier caractere doit etre une lettre
	/*	if(!estAlphanumerique()){
			return false;
		}*/
		//On continue de parcourrir le mot :
		while(estAlphanumerique()){
			precharge = lexical.suivant();
		}
		//{['_'] Alphanumerique }
		while(precharge.estUnderscore()){
			//On passe au suivant
			precharge = lexical.suivant();
			
				while(estAlphanumerique()){
					precharge = lexical.suivant();
				}
		}
		return true;
	}
	
	protected boolean estAlphanumerique() throws IOException{
		// ! A MODIFIER : ESTINCONNU() a enlevé 
		// JETON LETTRE A RAJOUTER
		if(precharge.estEntier() ||precharge.estLettre() ||precharge.estInconnu()){
			//precharge = lexical.suivant();
			return true;
		}
		return false;
	}
//---------------------------------------------------------------------------------------------	
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
			return false;
	}
	
	protected boolean estRegle() throws IOException{
		if(estRegleSansPremisse() || estRegleAvecPremisses()){
			return true;
		}
		return false;
	}
	
	protected boolean estRegleSansPremisse() throws IOException{
		//On sait que si l'expression commence par 'Si' alors c'est une expression AVEC premisses
		if(precharge.estSi()){
			return false;
		}
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
		//while(!precharge.estNon()){
		
		if(!precharge.estNon()  && !estFaitBooleen()){
			return false;
		}
		if(estFaitBooleen()){
			//On verifie qu'il ny a pas de egal derriere car si il y a un signe egal derriere 
			//c'est une conclusion symbolique ou entiere 
			//On retourne donc faux
			if(estComparateur()){
				return false;
			}
		}
		while(precharge.estNon()){
			precharge = lexical.suivant();
			if(!estFaitBooleen()){
				return false;
			}
			//Bidouillage
			if(precharge.estComparateurEgal()){
				return false;
			}
		}
		return true;
	}
	
	protected boolean estConclusionSymbolique() throws IOException{
		System.out.println(precharge.lireRepresentation()+"symbo");
		//Fait_symbolique
		if(!estFaitSymbolique()){
			System.out.println(precharge.lireRepresentation()+"symbo false");
			return false;
		}
		// '=' ou '/='
		System.out.println(precharge.lireRepresentation()+"symbo");
		
		if(precharge.estComparateurEgal() || precharge.estComparateurDifferent()){
			precharge = lexical.suivant();
		}else{
			System.out.println(precharge.lireRepresentation()+"symbo false");
			return false;
		}
		//Constante symbolique(=Identificateur) ou Fait_symbolique
		if(!estIdentificateur() && !estFaitSymbolique()){
			return false;
		}
		if(precharge.estParentheseOuvrante()){
			return false;
		}
	
		return true;
	}
	//LA OU IL Y A TOUT LES PROBLEMES :
	//Le probleme est que le jeton passe au suivant dans certains test lors de la conclusion booleen et symbolique
	//Et ne se remet pas a zero lors du false
	protected boolean estConclusionEntiere() throws IOException{
		//Fait_entier
	/*	if(!estFaitEntier()){
			return false;
		}
		
		//Comparateur
		if(estComparateur()){
			precharge = lexical.suivant();
		}
		else{
			return false;
		}*/
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
		
		if(precharge.estParentheseOuvrante()){
			// Passer la parenthese.
			precharge = lexical.suivant();
			// Appel de la methode associee a la regle "Declaration".
			if(!estExpressionEntiere()){
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
			System.out.println(precharge.lireRepresentation()+"premisse false?");
			
			return false;
		}
		return true;
	}

}//Fin de la classe Syntaxique

