package sysexp;


/**
 * Classe representant un jeton de la grammaire.
 */
public class Jeton {

    /**
     * Type enumere fortement type representant les differentes types de
     * jetons.
     */
    public enum Type {
    	faits_booleens,
    	faits_symboliques,
    	faits_entiers,
    	ParentheseOuvrante,
    	ParentheseFermante,
    	OperateurPlus,
    	OperateurMoins,
    	OperateurMultiplie,
    	OperateurDivise,
    	Entier,
    	Underscore,
    	Non,
    	Si,
    	Alors,
    	Et,
    	ComparateurEgal,
    	ComparateurInferieur,
    	ComparateurSuperieur,
    	ComparateurInferieurOuEgal,
    	ComparateurSuperieurOuEgal,
    	ComparateurDifferent,//correspond a /= 
    	Virgule,
    	PointVirgule,
    	Lettre,
    	FinExpression,
    	Inconnu
    }

   /**
    * Accesseur.
    *
    * @return la valeur de {@link Jeton#type}.
    */
   public Type lireType() {
	   return type;
   }

   /**
    * Accesseur.
    *
    * @return la valeur de {@link Jeton#representation}.
    */
   public String lireRepresentation() {
	   return representation;
   }
   /**
    * Indique si ce jeton est associe a la chaine de caractere 'faits_booleens'.
    *
    * @return true si ce jeton est associe a la parenthese ouvrante sinon 
    *   false.
    */
   public boolean estFaits_Booleens() {
	return type == Jeton.Type.faits_booleens;
   }
   /**
    * Indique si ce jeton est associe a la chaine de caractere 'fait_symbolique'
    *
    * @return true si ce jeton est associe a la parenthese ouvrante sinon 
    *   false.
    */
   public boolean estFaits_Symboliques() {
	return type == Jeton.Type.faits_symboliques;
   }
   /**
    * Indique si ce jeton est associe a la chaine de caractere 'fait_entier'.
    *
    * @return true si ce jeton est associe a la parenthese ouvrante sinon 
    *   false.
    */
   public boolean estFaits_Entiers() {
	return type == Jeton.Type.faits_entiers;
   }
   /**
    * Indique si ce jeton est associe a la parenthese ouvrante.
    *
    * @return true si ce jeton est associe a la parenthese ouvrante sinon 
    *   false.
    */
   public boolean estParentheseOuvrante() {
	return type == Jeton.Type.ParentheseOuvrante;
   }

   /**
    * Indique si ce jeton est associe a la parenthese fermante.
    *
    * @return true si ce jeton est associe a la parenthese fermante sinon 
    *   false.
    */
   public boolean estParentheseFermante() {
	return type == Jeton.Type.ParentheseFermante;
   }
   
   /**
    * Indique si ce jeton est associe a l'operateur d'addition.
    *
    * @return true si ce jeton est associe a l'operateur d'addition sinon 
    *   false.
    */
   public boolean estOperateurPlus() {
	 return type == Jeton.Type.OperateurPlus;
   }

   /**
    * Indique si ce jeton est associe a l'operateur de soustraction.
    *
    * @return true si ce jeton est associe a l'operateur de soustraction sinon
    *   false.
    */
   public boolean estOperateurMoins() {
	return type == Jeton.Type.OperateurMoins;
   }

   /**
    * Indique si ce jeton est associe à l'operateur de multiplication.
    *
    * @return true si ce jeton est associe a l'operateur de multiplication 
    *   sinon false.
    */
   public boolean estOperateurMultiplie() {
	return type == Jeton.Type.OperateurMultiplie;
   }

   /**
    * Indique si ce jeton est associe a l'operateur de division.
    *
    * @return true si ce jeton est associe a l'operateur de division sinon 
    *   false.
    */
   public boolean estOperateurDivise() {
	return type == Jeton.Type.OperateurDivise;
   }
   
   /**
    * Indique si ce jeton est associe a l'operateur d'egalite.
    *
    * @return true si ce jeton est associe a l'operateur d'egalite sinon 
    *   false.
    */
   public boolean estComparateurEgal() {
	   return type == Jeton.Type.ComparateurEgal;
   }
   /**
    * Indique si ce jeton est associe a un entier.
    *
    * @return true si ce jeton est associe a un entier sinon false.
   */
   public boolean estEntier() {
	return type == Jeton.Type.Entier;
   }
   /**
    * Indique si ce jeton est associe au caractere _.
    *
    * @return true si ce jeton est associe au caractere _ sinon 
    *   false.
    */
   public boolean estUnderscore() {
	   return type == Jeton.Type.Underscore;
   }
   
   public boolean estSi() {
	   return type == Jeton.Type.Si;
   }
   
   public boolean estAlors() {
	   return type == Jeton.Type.Alors;
   }
   public boolean estEt() {
	   return type == Jeton.Type.Et;
   }
   
   public boolean estComparateurInferieur() {
	   return type == Jeton.Type.ComparateurInferieur;
   }
   
   public boolean estComparateurSuperieur() {
	   return type == Jeton.Type.ComparateurSuperieur;
   }
   
   public boolean estComparateurSuperieurOuEgal() {
	   return type == Jeton.Type.ComparateurInferieurOuEgal;
   }
   
   public boolean estComparateurInferieurOuEgal() {
	   return type == Jeton.Type.ComparateurSuperieurOuEgal;
   }
   
   public boolean estComparateurDifferent() {
	   return type == Jeton.Type.ComparateurDifferent;
   }
   public boolean estNon() {
	   return type == Jeton.Type.Non;
   }
   
   public boolean estVirgule() {
	   return type == Jeton.Type.Virgule;
	}
   
   public boolean estPointVirgule() {
	   return type == Jeton.Type.PointVirgule;
   }
   public boolean estLettre() {
	   return type == Jeton.Type.Lettre;
   }
   
   public boolean estInconnu() {
		return type == Jeton.Type.Inconnu;
   }
   /**
    * Indique si ce jeton est associe a la fin d'expression.
    *
    * @return true si ce jeton est associe a la fin d'expression sinon false.
    */
   public boolean estFinExpression() {
	return type == Jeton.Type.FinExpression;
   }
   
   /**
    * Constructeur logique.
	*
	* @param type la valeur de {@link Jeton#type}.
	* @param representation la valeur de {@link Jeton#representation}.
	*/
   	protected Jeton(Type type, String representation) {
	this.type = type;
	this.representation = representation;
	    }
	    
	   

	    /**
	     * Type de ce jeton.
	     */
	    protected final Type type;

	    /**
	     * Representation de ce jeton sous forme d'une chaine de caractere.
	     */
	    protected final String representation;

	
	    
}