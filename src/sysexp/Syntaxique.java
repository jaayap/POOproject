package sysexp;
import java.io.IOException;

/**
 * Classe representant un analyseur syntaxique de la grammaire.
 */

public class Syntaxique {
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
	public Syntaxique(Lexical lexical){
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
		if(!precharge.estEntier() && !precharge.estLettre() && !precharge.estInconnu()){
			return false;
		}
		return true;
	}
	
	// Methodes liées aux regles :---------------------------------------------
	protected boolean estRegles() throws IOException{
			//{ Regle ';' }
			while (estRegle()){
				System.out.println(" c'est une regle \n");
				//precharge = lexical.suivant();
				// il doit obligatoirement y avoir un point virgule derriere une Regle
				if(precharge.estPointVirgule()){
					precharge = lexical.suivant();
					System.out.println("point virgule, fin d'une regle");
				//	return true;
				}else{
					System.out.println("erreur point virgule manquant \n");
					return false;
				}
			}
			System.out.println("c'est pas une regle\n");
			return false;
	}
	
	protected boolean estRegle() throws IOException{
		System.out.println("estRegle\n");
		if(estRegleSansPremisse() || estRegleAvecPremisses()){
			System.out.println("estRegle = true \n");
			return true;
		}
		System.out.println("regle FALSE \n");
		return false;
	}
	
	protected boolean estRegleSansPremisse() throws IOException{
		System.out.println("regle sans premisse \n");
		if(precharge.estSi()){
			System.out.println("precharge = 'SI' sans premisse =faux\n");
			return false;
		}
		if(estConclusion()){
			System.out.println("une conclusion / est regle sans premisse true\n");
			return true;
		}
		System.out.println("regle sans premisse FAUX \n");
		return false;
	}
	
	protected boolean estRegleAvecPremisses() throws IOException{
		System.out.println("regle avec premisse \n");
		//'si'
		if(precharge.estSi()){
			System.out.println(" precharge = SI\n");
			precharge = lexical.suivant();
		}
		else{
			return false;
		}
		//Condition
		if(!estCondition()){
			System.out.println("RETURN FALSE pas de condition\n");
			return false;
		}
		//'alors'
		if(precharge.estAlors()){
			System.out.println(" precharge = ALORS\n");
			precharge = lexical.suivant();
		}
		else{
			System.out.println(" precharge /= ALORS\n");
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
		System.out.println("estConclusion \n");
		if(estConclusionBooleene() || estConclusionSymbolique() || estConclusionEntiere()){
			System.out.println("conclusion correct \n");
			return true;
		}
		return false;
	}
	protected boolean estConclusionBooleene() throws IOException{
		System.out.println("estConclusionBooleene \n");
		//Fait_booleen ou 'non' Fait_Booleen
		//while(!precharge.estNon()){
	
		if(!precharge.estNon()  && !estFaitBooleen()){
			System.out.println("RETURN FALSE Ni fait booleen ni jeton 'non' \n");
			return false;
		}
		if(estFaitBooleen()){
			System.out.println("FAIT_BOOLEEN\n");
			//On verifie qu'il ny a pas de egal derriere car si il y a un signe egal derriere 
			//c'est une conclusion symbolique ou entiere 
			//On retourne donc faux
			if(precharge.estComparateurEgal()){
				System.out.println("RETURN FALSE precharge '='\n");
				return false;
			}
		}
		while(precharge.estNon()){
			System.out.println("Non quelquechose\n");
			precharge = lexical.suivant();
			if(!estFaitBooleen()){
				return false;
			}
			if(precharge.estComparateurEgal()){
				System.out.println("RETURN FALSE precharge '='\n");
				return false;
			}
		}
	
		return true;
	}
	
	protected boolean estConclusionSymbolique() throws IOException{
		System.out.println("estConclusionSymbolique");
		//Fait_symbolique
		if(!estFaitSymbolique()){
			System.out.println("je trouve pas le fait symbolique\n");
			return false;
		}
		// '=' ou '/='
		if(precharge.estComparateurEgal() || precharge.estComparateurDifferent()){
			System.out.println("je suis déja ici au signe égal\n");
			precharge = lexical.suivant();
		}else{
			return false;
		}
		//Constante symbolique(=Identificateur) ou Fait_symbolique
		if(!estIdentificateur() || !estFaitSymbolique()){
			System.out.println("NON IDENTIFICATEUR\n");
			return false;
		}
		return true;
	}
	
	protected boolean estConclusionEntiere() throws IOException{
		System.out.println("conclusion Entiere");
		//Fait_entier
		if(!estFaitEntier()){
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
		
		if(!precharge.estParentheseOuvrante()){
			// Passer la parenthese.
			precharge = lexical.suivant();
			// Appel de la methode associee a la regle "Declaration".
			if(!estDeclaration()){
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
			System.out.println("RETURN FALSE estPremisse\n");
			return false;
		}
		System.out.println("RETURN TRUE estPremisse\n");
		return true;
	}

}//Fin de la classe Syntaxique

