package analyseurSyntaxique;

import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Classe representant un analyseur lexical de la grammaire.
 */
public class Lexical {

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
    /*public Jeton suivant() throws IOException {

	// Si nous sommes a la fin du flot, il faut retourner le jeton associe
	// a la fin d'expression.
	if (! avancer()) {
	    return FabriqueJeton.finExpression();
	}

	// Caractere correspondant a la position courante.
	char caractere = ligne.charAt(position);
	

	// Il faut identifier le jeton.
	//switch(caractere) {   
 
	//case '(': // Parenthese ouvrante.
	
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
*/
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
		   Character.isWhitespace(ligne.charAt(position))) {
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

}