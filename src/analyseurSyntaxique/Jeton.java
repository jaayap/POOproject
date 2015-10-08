package analyseurSyntaxique;



/**
 * Classe representant un jeton de la grammaire.
 */
public class Jeton {

    /**
     * Type enumere fortement type representant les differentes types de
     * jetons.
     */
    public enum Type {
    	OperateurPlus,
    	OperateurMoins,
    	OperateurMultiplie,
    	OperateurDivise,
    	OperateurEgal,
    	ChiffrePositif,
    	Zero,
    	Lettre,
    	Underscore,
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
   public boolean estOperateurEgal() {
	   return type == Jeton.Type.OperateurEgal;
   }
   
   /**
    * Indique si ce jeton est associe a un chiffre positif.
    *
    * @return true si ce jeton est associe a un chiffre positif sinon 
    *   false.
    */
   public boolean estChiffrePositif() {
	   return type == Jeton.Type.ChiffrePositif;
   }
   
   /**
    * Indique si ce jeton est associe au chiffre zero.
    *
    * @return true si ce jeton est associe au chiffre zero sinon 
    *   false.
    */
   public boolean estChiffreZero() {
	   return type == Jeton.Type.Zero;
   }
   
   /**
    * Indique si ce jeton est associe a une lettre.
    *
    * @return true si ce jeton est associe a une lettre sinon 
    *   false.
    */
   public boolean estLettre() {
	   return type == Jeton.Type.Lettre;
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
   
   
   public boolean estInconnu() {
		return type == Jeton.Type.Inconnu;
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
	     * Indique si ce jeton est associe a la fin d'expression.
	     *
	     * @return true si ce jeton est associe a la fin d'expression sinon false.
	     */
	    public boolean estFinExpression() {
		return type == Jeton.Type.FinExpression;
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