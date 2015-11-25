package sysexp.client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;

import sysexp.builders.Director;
import sysexp.builders.lorraine.BuilderLorraine;
import sysexp.modele.FaitAbstrait;
import sysexp.modele.FaitBooleen;
import sysexp.modele.RegleAbstraite;

/**
 * Classe principale de l'application :
 * sa vocation est de tester le modele 
 * et vérifier que le fichier passer en argument ne contient pas d'erreur
 * @author Jasmine
 *
 */

public class Client { 
	
	protected static RegleAbstraite baseDeRegles;
	protected static HashMap<String, FaitAbstrait> baseDeFaits = new HashMap<String,FaitAbstrait>();
	
	/**
    * Point d'entree de la JVM.
    *
    * @param args - les arguments de la ligne de commandes.
    * @throws IOException 
    */
	public static void main(String[] args) throws IOException{
		
	        // La ligne de commandes est vide : l'utilisateur demande de l'aide.
	        if (args.length == 0) {
	            System.out.println("Usage: java DemoLexical nomdefichier");
	            return;
	        }

	        // Plus d'un argument sur la ligne de commandes : 
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
	        BuilderLorraine builder = new BuilderLorraine(lecteur); // le builder instancie Lexical
	        Director aDirector = new Director(builder);
	        aDirector.construct();
	        // Verification.
			if (builder.test) {
			    System.out.println("Expression correcte.");
			    //Etape 1 fini :
			    //Etape 2. moteur d'inférence
			    baseDeRegles = builder.getBaseDeRegles();//on recupere la base de regle
			    if(baseDeRegles != null){
			    	while(baseDeRegles.iterer(baseDeFaits));
			    }
			    //Etape 3. Ecrire le contenu de la base de fait sur la sortie standart
			    System.out.println("Base de Fait : \n"+ baseDeFaits.toString());
			 
			    return;
			}else{
				System.out.println("expression incorrect");
			}

			// L'expression est incorrecte : il faut afficher ce qui pose
			// probleme. Il faut se méfier du cas particulier ou le jeton
			// inattendu est la fin d'expression auquel cas il n'y a plus de
			// ligne.
			final String ligne = builder.getlexical().lireLigne();
			System.err.println("Erreur (entre crochets) en ligne : " +
			                   lecteur.getLineNumber());
			if (ligne == null) {
			    System.err.println("[]");
			    return;
			}
			final int position = builder.getlexical().lirePosition();
			final String message =
			    ligne.substring(0, position - 1) +
			    "[" +
			    ligne.charAt(position - 1) +
			    "]" +
			    ligne.substring(position, ligne.length()) +
			    "\n";
			System.err.println(message);
	}
}
