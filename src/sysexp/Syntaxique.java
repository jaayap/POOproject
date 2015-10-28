package sysexp;

import java.io.IOException;

public class Syntaxique {
	protected Lexical lexical;
	protected Jeton precharge;

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

	/*
	 * Methodes liés aux déclarations
	 */
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
		if (precharge.estFaits_Booleens()) { // On test si c'est bien le jeton 'fait_booleen'
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
		if (!estIdentificateur()) {// Le premier doit etre un fait booleen soit un Identificateur
			return false;
		}
		// { ',' Fait_Booleen }
		while (precharge.estVirgule()) {// suivi d'une virgule eventuellement
			// on passe au suivant
			precharge = lexical.suivant();
			// Deriere une virgule il doit y avoir un fait_booleen
			if (!estIdentificateur()) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean estDeclarationSymbolique() throws IOException{
		// 'faits_symboliques'  : mot clé
		if(precharge.estFaits_Symboliques()){//jeton 'fait_symbolique'
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
		if(!estIdentificateur()){//Un fait symbolique = un identificateur
			return false;
		}
		// { ',' Fait_Symbolique }
		while(precharge.estVirgule()){
			//on passe au suivant
			precharge = lexical.suivant();
			//Deriere une virgule il doit y avoir un Fait_Symbolique
			if(!estIdentificateur()){
				return false;
			}
		}
		return true;
	}
	protected boolean estDeclarationEntiere() throws IOException{
		// 'faits_entiers' : mot clé
		if(precharge.estFaits_Entiers()){
			precharge = lexical.suivant();//jeton 'fait_entier'
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
		if(!estIdentificateur()){
			return false;
		}
		// { ',' Fait_Entier }
		while(precharge.estVirgule()){
			//on passe au suivant
			precharge = lexical.suivant();
			//Deriere une virgule il doit y avoir un fait_Entier
			if(!estIdentificateur()){
				return false;
			}
		}
		return true;
	}
	protected boolean estIdentificateur() throws IOException {
		// Le premier caractere doit etre une lettre
		if (!estLettre()) { //jeton = lettre
			return false;
		}
		// On continue de parcourrir le mot :
		while (estLettre()) {
			precharge = lexical.suivant();
		}
		// {['_'] Alphanumerique }
		while (precharge.estUnderscore()) {
			// On passe au suivant
			precharge = lexical.suivant();

			while (estAlphanumerique()) {
				precharge = lexical.suivant();
			}
		}
		return true;
	}

	protected boolean estAlphanumerique() throws IOException {
		// ! A MODIFIER : ESTINCONNU() a enlevé
		// JETON LETTRE A RAJOUTER
		if (precharge.estEntier() || precharge.estLettre()|| precharge.estInconnu()) {
			// precharge = lexical.suivant();
			return true;
		}
		return false;
	}
	protected boolean estLettre() throws IOException {
		// ! A MODIFIER : ESTINCONNU() a enlevé
		// JETON LETTRE A RAJOUTER
		if (precharge.estLettre()|| precharge.estInconnu()) {
			// precharge = lexical.suivant();
			return true;
		}
		return false;
	}
//-----------------------------------------------------------------------------------------------------------------
	//LES REGLES :
	protected boolean estRegles() throws IOException{ // On regarde s'il y a une liste de regles
		//{ Regle ';' }
		// 0, 1, ou plusieurs regles
		//une regle doit se finir par un point virgule meme si c'est une regle 'vide'
		if(!estRegle()){ // 0
			if(precharge.estPointVirgule()){
				precharge = lexical.suivant();
			}else{
				return false;
			}
		}
		//Tant que c'est une regle elle se finit par un point virgule
		while (estRegle()){ // une ou plusieurs
			// il doit obligatoirement y avoir un point virgule derriere une Regle
			if(precharge.estPointVirgule()){
				precharge = lexical.suivant();
			}else{
				
				return false;
			}
		}
		return true;
}
	protected boolean estRegle() throws IOException{
		if(estRegleSansPremisse() || estRegleAvecPremisses()){//IL existe 2 types de regles 
			return true;
		}
		return false;
	}
	protected boolean estRegleSansPremisse() throws IOException{
		//Une regle sans prémisse ne possède qu'une conclusion
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
	protected boolean estConclusion() throws IOException{
		// si precharge = non, on sais que c'est soit une conclusion booleene soit rien
		if(precharge.estNon()){
			if(!estConclusionBooleene()){
				return false;
			}
			else{
				return true;
			}
		}
		
		//	Une conclusion commence toujours par un identificateur
		if(!estIdentificateur()){
			return false;
		}
		
		if(precharge.estComparateurEgal() || precharge.estComparateurDifferent()){
			precharge = lexical.suivant();
			if(!estConclusionSymbolique() && !estConclusionEntiere()){
				return false;
			}
		}
			
		if(estComparateur()){
			precharge = lexical.suivant(); 
			if(!estConclusionEntiere()){
				return false;
			}
		}
		return true;
		/*if(estConclusionBooleene() || estConclusionSymbolique()){// 3 type de conclusion
			return true;
		}
		return false;*/
	}
	protected boolean estConclusionBooleene() throws IOException{
		//Fait_booleen ou 'non' Fait_Booleen
		if(!precharge.estNon() && !estIdentificateur()){//Doit commencer par non ou fait_booleen
			return false;	
		}
		while(precharge.estNon()){
			precharge = lexical.suivant();
			if(!estIdentificateur()){
				return false;
			}
		}
		if(precharge.estComparateurEgal()){
			return false;
		}
		return true;
	}
	protected boolean estConclusionSymbolique() throws IOException{
		//Fait_symbolique soit un identificateur
	/*	if(!estIdentificateur()){
			return false;
		}
		// '=' ou '/='
		if(precharge.estComparateurEgal() || precharge.estComparateurDifferent()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		*/
		//Constante symbolique(=Identificateur) ou Fait_symbolique(=Identificateur)
		if(!estIdentificateur()){
			return false;
		}
		return true;
	}
	protected boolean estConclusionEntiere() throws IOException{
		//Fait_entier
	/*	if(!estFaitEntier()){
			return false;
		}
		
		//Comparateur
		if(estComparateur()){
			System.out.println("estComparateur?"+precharge.lireRepresentation());
			precharge = lexical.suivant();
			System.out.println("estComparateur?"+precharge.lireRepresentation());
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
			if(!estExpressionEntiere() && !estIdentificateur()){//Rajout de est identificateur pour le cas : 
																//fortune = (2 * fortune_parents ) - 1
				return false;
			}
			// Le jeton precharge doit etre une parenthese fermante.
			if(precharge.estParentheseFermante()){
				// Passer la parenthese fermante.
				precharge = lexical.suivant();
				//La regle est satisfaite.
				return true;
			}
			else{
				return false;
			}
		}
		// Le jeton est inconnu.
		return false;
	}
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
		/*if(!estPremisse()){
			return false;
		}
		if(estConditionComparaisonEntiere() || estConditionSymboliqueOuBooleene()){
			return true;
		}
		return false;*/
	}
	protected boolean estConditionSymboliqueOuBooleene() throws IOException{
	
		//{ et Premisse } // FAUX DANS 'fortune > 10000 ' car c'est une conclusion entiere
		//					la premisse est égal a fortune seulement :/
		//					PAS de souci avec les profession = medecin
		if(!precharge.estEt()){
			return false;
		}
		while(precharge.estEt()){
			precharge = lexical.suivant();
			if(!estPremisse()){
				return false;
			}
		}
		
		return true;
	}
	protected boolean estConditionComparaisonEntiere() throws IOException{
		//{ et Premisse } // FAUX DANS 'fortune > 10000 ' car c'est une conclusion entiere
		//					la premisse est égal a fortune seulement :/
		//					PAS de souci avec les profession = medecin
		
		if(estComparateur()){
			precharge = lexical.suivant();
			if(!estExpressionEntiere()){
				return false;
			}
			else{
				return true;
			}
		}else{
			return false;
		}

	}
	protected boolean estConditionComparaisonFaits() throws IOException{
		//{ et Premisse } // FAUX DANS 'fortune > 10000 ' car c'est une conclusion entiere
		//					la premisse est égal a fortune seulement :/
		//					PAS de souci avec les profession = medecin
		
		if(estComparateur()){
			precharge = lexical.suivant();
			
			if(!precharge.estEntier()){
				return false;
			}
			else{
				precharge = lexical.suivant();
				return true;
			}
		}else{
			return false;
		}

	}
	protected boolean estPremisse() throws IOException{
		//Au niveau Semantique :
		//PremisseBooleene = ConclusionBooleene
		//PremisseSymbolique = ConclusionSymbolique
		//PremisseEntiere = ConclusionEntiere
		//Syntaxiquement on peut donc faire appel a ces methodes :
		/*if(!estConclusionBooleene() && !estConclusionSymbolique() && !estConclusionEntiere()){
			System.out.println(precharge.lireRepresentation()+"premisse false?");
			
			return false;
		}*/
		//On peut donc plutot faire 
		if(!estConclusion()){
			return false;
		}
		return true;
	}

	//Petite methode sympa, pour aller plus vite
	protected boolean estComparateur() throws IOException{//Pas super joli :
		if(!precharge.estComparateurEgal() && !precharge.estComparateurDifferent() &&
		   !precharge.estComparateurInferieur() && !precharge.estComparateurInferieurOuEgal() &&
		   !precharge.estComparateurSuperieur() && !precharge.estComparateurSuperieurOuEgal()
		   ){
			 return false;
		}
		return true;
	}
	
}

