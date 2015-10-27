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
			if (!estRegles()) {
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
		if(estRegleSansPremisse()){//IL existe 2 types de regles 
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
	
	protected boolean estConclusion() throws IOException{
		if(estConclusionBooleene()){// 3 type de conclusion
			return true;
		}
		return false;
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
		System.out.println(precharge.lireRepresentation()+"coucou 2");
		//Fait_symbolique soit un identificateur
		if(!estIdentificateur()){
			return false;
		}
		// '=' ou '/='
		if(precharge.estComparateurEgal() || precharge.estComparateurDifferent()){
			precharge = lexical.suivant();
		}else{
			return false;
		}
		//Constante symbolique(=Identificateur) ou Fait_symbolique(=Identificateur)
		if(!estIdentificateur()){
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

