package sysexp;

import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Classe representant un analyseur lexical de la grammaire.
 */
public class Lexical {
    /**
     * Lecteur de cet analyseur.
     */
    protected LineNumberReader lecteur;
    /**
     * Ligne courante.
     */
    protected String ligne;
    /**
     * Position courante dans la ligne courante.
     */
    protected int position;
    
    
    /**
     * Constructeur logique.
     *
     * @param lecteur la valeur de {@link Lexical#lecteur}.
     */
    public Lexical(LineNumberReader lecteur) {
		this.lecteur = lecteur;
		ligne = "";
		position = 0;
    }
    
    /**
     * Accesseur.
     *
     * @return la valeur de {@link Lexical#lecteur}.
     */
    public LineNumberReader lireLecteur() {
    	return lecteur;
    }

    /**
     * Accesseur.
     *
     * @return la valeur de {@link Lexical#ligne}.
     */
    public String lireLigne() {
    	return ligne;
    }

    /**
     * Accesseur.
     *
     * @return la valeur de {@link Lexical#position}.
     */
    public int lirePosition() {
    	return position;
    }

    /**
     * Extrait puis retourne le jeton suivant.
     *
     * @return le jeton suivant.
     * @throw IOException sur le flot d'entree ne peut etre lu.
     */
    public Jeton suivant() throws IOException {
		// Si nous sommes a la fin du flot, il faut retourner le jeton associe
		// a la fin d'expression.
		if (!avancer()) {
		    return FabriqueJeton.finExpression();
		}
		// Caractere correspondant a la position courante.
		char caractere = ligne.charAt(position);
		//String utilis�e pour les tests suivants sur des cha�nes de caractere
		String suite="";
		String suite_bool="";
		String suite_synt = "";
		String suite_ent ="";
		// Il faut identifier le jeton.
		switch(caractere) {   
		
/*
		Le souci c'est qu'on test qu'un caractere.
		case :'/=': // Comparateur Different.
		    position ++;
		    return FabriqueJeton.comparateurDifferent();
		    
		    on ne peut donc pas rajouter une condition dans le case si simplement
		    
		    
		    ce qu'il faut faire c'est case : '/'
		    		if caractere suivant egal '='
		    			alors jeton différents
		    			
		    pour faire sa, tu va surement avoir besoin d'utiliser des substrings pour recuperer des caracteres
		    apres la position
		    
		    Il faut tester TOUS les jetons qu'on a definit !
		    
		    bon courage :)
		    */
			case '(': // Parenthese ouvrante.
			    position ++;
			    return FabriqueJeton.parentheseOuvrante();
	
			case ')': // Parenthese fermante.
			    position ++;
			    return FabriqueJeton.parentheseFermante();
	
			case '+': // Operateur d'addition.
			    position ++;
			    return FabriqueJeton.operateurPlus();
	
			case '-': // Operateur de soustraction.
			    position ++;
			    return FabriqueJeton.operateurMoins();
	
			case '*': // Operateur de multiplication.
			    position ++;
			    return FabriqueJeton.operateurMultiplie();
	
			case '/': // Operateur de division.
			    position ++;
			    caractere=ligne.charAt(position);
			    if (caractere=='=') {
			    	position ++;
			    	return FabriqueJeton.different();		    		
			    }
			    return FabriqueJeton.operateurDivise();
			 
			case '=': // Comparateur Egal
			    position ++;
			    return FabriqueJeton.comparateurEgal();
			    
			case '<': // Comparateur Inferieur a
			    position ++;
			    caractere=ligne.charAt(position);
			    if (caractere=='=') {
			    	position ++;
			    	return FabriqueJeton.inferieurOuEgal();
			    }
			    return FabriqueJeton.inferieur();
			    
			case '>': // Comparateur Superieur a
			    position ++;
			    caractere=ligne.charAt(position);
			    if (caractere=='=') {
			    	position ++;
			    	return FabriqueJeton.superieurOuEgal();
			    }
			    return FabriqueJeton.superieur();
			    
			case 'n': // Lettre n (jeton non)
				position ++;
				suite=ligne.substring(position, position+3);
				if (suite=="on "){
					position=position+3;
					return FabriqueJeton.non();
				}
				
			case 's': // Lettre s (jeton si)
				position ++;
				suite=ligne.substring(position, position+2);
				if (suite=="i "){
					position=position+2;
					return FabriqueJeton.si();
				}
				
			case 'a': // Lettre a (jeton alors)
				position ++;
				suite=ligne.substring(position, position+5);
				if (suite=="lors "){
					position=position+5;
					return FabriqueJeton.alors();
				}
				
			case 'e': // Lettre e (jeton et)
				position ++;
				suite=ligne.substring(position, position+2);
				if (suite=="t "){
					position=position+2;
					return FabriqueJeton.et();
				}
			
			case 'f' : // Lettre f
				position ++;
				suite_bool=ligne.substring(position, position+14);
				if (suite_bool=="aits_booleens "){
					position=position+13;
					return FabriqueJeton.faitsBooleens();
				}
				suite_synt = ligne.substring(position, position+16);
				if (suite=="aits_syntaxiques "){
					position=position+16;
					return FabriqueJeton.faitsSymboliques();
				}
				suite_ent = ligne.substring(position, position+13);
				if (suite=="aits_entiers "){
					position=position+13;
					return FabriqueJeton.faitsEntiers();
				}
				
			    
		default: // Chiffre ou bien representation inconnue.
			if (Character.isDigit(caractere)) {
				return extraireEntier();
			}
			// C'est la representation inconnue.
			 position ++;
			 return FabriqueJeton.inconnu(ligne.substring(position - 1, 
									 position));
		}
    }

    /**
     * Tente d'avancer dans le flot.
     *
     * @return true si nous avons pu avancer dans le flot sinon false.
     */
    protected boolean avancer() throws IOException {

		// Tant que nous sommes sur une ligne vide, il faut en lire d'autres
		// jusqu'à obtenir une ligne non vide ou bien atteindre la fin du flot.
		while (true) {
	
		    // Tant que nous ne sommes pas à la fin de la ligne et que le 
		    // caractere courant est un separateur, nous avancons dans la ligne.
		    while (position < ligne.length() && 
		    		Character.isWhitespace(ligne.charAt(position))){
		    		
		    	position ++;
		    }
	
		    // Nous sommes a la fin de la ligne : il faut en lire une autre.
		    if (position == ligne.length()) {
				// Lecture d'une nouvelle ligne.
				ligne = lecteur.readLine();
				// La ligne est vide : nous sommes a la fin du flot.
				if (ligne == null) {
				    return false;
				}
				// Repositionnement au debut de la nouvelle ligne.
				position = 0;
		    }
		    // Nous sommes sur le premier caractere d'un jeton : il est temps
		    // de sortie de la boucle.
		    else {
		    	return true;
		    }
		}
    }
    /**
     * Extrait un entier du flot d'entree.
     */
    protected Jeton extraireEntier() {

	// Nous nous placons juste derriere la position courante.
	int fin = position + 1;

	// Nous avons dans la ligne jusqu'a en attendre la fin ou bien tomber
	// sur un caractere qui n'est plus un chiffre.
	while (fin < ligne.length() && Character.isDigit(ligne.charAt(fin))) {
	    fin ++;
	}

	// Memorisation de la position du premier chiffre dans la ligne.
	int debut = position;

	// Positionnement sur le premier caractere situe derriere le dernier
	// chiffre.
	position = fin;

	// Extraction.
	return FabriqueJeton.entier(ligne.substring(debut, fin));

    }
}