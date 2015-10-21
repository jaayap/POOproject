package sysexp;

import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Classe representant le programme de demonstration de la classe Lexical.
 */
public class DemoLexical {

    /**
     * Point d'entree de la JVM.
     *
     * @param args les parametres de la ligne de commandes.
     */
    public static void main(String[] args) {

	// La ligne de commandes est vide : l'utilisateur demande de l'aide.
	if (args.length == 0) {
	    System.out.println("Usage: java DemoLexical nomdefichier");
	    return;
	}

	// Plus d'un argument sur la ligne de commandes : l'utilisateur fait
	// n'importe quoi.
	if (args.length != 1) {
	    System.err.println("Nombre d'arguments incorrect.");
	    return;
	}

	// Tentative de connexion d'un flot d'entree au fichier.
	LineNumberReader lecteur = null;
	try {
	    lecteur = new LineNumberReader(new FileReader(args[0]));
	}
	catch(FileNotFoundException e) {
	    System.err.println("Le fichier [" + args[0] + "] n'existe pas.");
	    return;
	}

	// Instanciation de l'analyseur lexical.
	Lexical lexical = new Lexical(lecteur);

	// Tentative d'extraction de tous les jetons. Chaque jeton extrait est
	// affiche sur la sortie standard.
	try {
	   
	    // Une boucle for est elegante.
	    for (Jeton jeton = lexical.suivant();
		 ! jeton.estFinExpression();
		 jeton = lexical.suivant()) {
		System.out.println("{ " +
				   jeton.lireType() +
				   ", " +
				   jeton.lireRepresentation() +
				   " }");
	    }

	}
	catch(IOException e) {
	    System.err.println("Impossible de lire [" + args[0] + "]");
	    return;
	}

    }
    
}