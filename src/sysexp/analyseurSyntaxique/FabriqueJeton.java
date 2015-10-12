package sysexp.analyseurSyntaxique;



/**
 * Classe representant une fabrique de jetons de la grammaire. 
 *
 * @note Cette classe etant a usage interne, elle est invisible depuis 
 *   l'exterieur du paquetage.
 */
class FabriqueJeton {

    /**
     * Retourne le jeton associe a la parenthese ouvrante.
     *
     * @return le jeton associe a la parenthese ouvrante.
     */
    public static Jeton parentheseOuvrante() {
	return parentheseOuvrante;
    }

    /**
     * Retourne le jeton associe a la parenthese fermante.
     *
     * @return le jeton associe a la parenthese fermante.
     */
    public static Jeton parentheseFermante() {
	return parentheseFermante;
    }
    
	/**
     * Retourne le jeton associe a l'operateur d'addition.
     *
     * @return le jeton associe a l'operateur d'addition.
     */
    public static Jeton operateurPlus() {
    	return operateurPlus;
    }

    /**
     * Retourne le jeton associe a l'operateur de soustraction.
     *
     * @return le jeton associe a l'operateur de soustraction.
     */
    public static Jeton operateurMoins() {
    	return operateurMoins;
    }

    /**
     * Retourne le jeton associe a l'operateur de multiplication.
     *
     * @return le jeton associe a l'operateur de multiplication.
     */
    public static Jeton operateurMultiplie() {
    	return operateurMultiplie;
    }

    /**
     * Retourne le jeton associe a l'operateur de division.
     *
     * @return le jeton associe a l'operateur de division.
     */
    public static Jeton operateurDivise() {
    	return operateurDivise;
    }
    
    /**
     * Retourne le jeton associe a l'operateur d'egalite.
     *
     * @return le jeton associe a l'operateur d'egalite.
     */
    public static Jeton operateurEgal() {
    	return operateurEgal;
    }
    
    /**
     * Retourne le jeton associe a un entier.
     *
     * @param representation la representation de l'entier.
     * @return le jeton associe a un entier.
     */
    public static Jeton entier(String representation) {
    	return new Jeton(Jeton.Type.Entier, representation);
    }

    /**
     * Retourne le jeton associe au caractere '_' .
     *
     * @return le jeton associe au caractere '_'.
     */
    public static Jeton underscore() {
    	return underscore;
    }
    
    /**
     * Retourne le jeton associe à 'non'.
     *
     * @return le jeton associe à 'non'.
     */
    public static Jeton non() {
    	return non;
    }
    /**
     * Retourne le jeton associe à 'si'.
     *
     * @return le jeton associe à 'si'.
     */
    public static Jeton si() {
    	return si;
    }
    /**
     * Retourne le jeton associe à 'alors'.
     *
     * @return le jeton associe à 'alors'.
     */
    public static Jeton alors() {
    	return alors;
    }
    /**
     * Retourne le jeton associe à '<'.
     *
     * @return le jeton associe à '<'.
     */
    public static Jeton inferieur() {
    	return inferieur;
    }
    /**
     * Retourne le jeton associe à '>'.
     *
     * @return le jeton associe à '>'.
     */
    public static Jeton superieur() {
    	return superieur;
    }
    /**
     * Retourne le jeton associe à '<='.
     *
     * @return le jeton associe à '<='.
     */
    public static Jeton inferieurOuEgal() {
    	return inferieurOuEgal;
    }
    
    /**
     * Retourne le jeton associe à '>='.
     *
     * @return le jeton associe à '>='.
     */
    public static Jeton superieurOuEgal() {
    	return superieurOuEgal;
    }
    
    /**
     * Retourne le jeton associe à '/='.
     *
     * @return le jeton associe à '/='.
     */
    public static Jeton different() {
    	return different;
    }

	/**
     * Retourne le jeton associe a une representation inconnue.
     *
     * @param representation la representation inconnue.
     * @return le jeton associe a une representation inconnue.
     */
    public static Jeton inconnu(String representation) {
    	return new Jeton(Jeton.Type.Inconnu, representation);
    }
    
    /**
     * Retourne le jeton associe a la fin d'expression.
     *
     * @return le jeton associe a la fin d'expression.
     */
    public static Jeton finExpression() {
    	return finExpression;
    }
    /**
     * Jeton associe a la parenthese ouvrante.
     */
    protected static final Jeton parentheseOuvrante = new Jeton(Jeton.Type.ParentheseOuvrante, "(");

    /**
     * Jeton associe a la parenthese fermante.
     */
    protected static final Jeton parentheseFermante = new Jeton(Jeton.Type.ParentheseFermante, ")");

    /**
     * Jeton associe a l'operateur d'addition.
     */
    protected static final Jeton operateurPlus = new Jeton(Jeton.Type.OperateurPlus, "+");

    /**
     * Jeton associe a l'operateur de soustraction.
     */
    protected static final Jeton operateurMoins = new Jeton(Jeton.Type.OperateurMoins, "-");

    /**
     * Jeton associe a l'operateur de multiplication.
     */
    protected static final Jeton operateurMultiplie = new Jeton(Jeton.Type.OperateurMultiplie, "*");

    /**
     * Jeton associe a l'operateur de division.
     */
    protected static final Jeton operateurDivise = new Jeton(Jeton.Type.OperateurDivise, "/");
    
    /**
     * Jeton associe a l'operateur d'�galit�.
     */
    protected static final Jeton operateurEgal = new Jeton(Jeton.Type.OperateurEgal, "=");
    
    
    /**
     * Jeton associe au caractere _.
     */
    protected static final Jeton underscore = new Jeton(Jeton.Type.Underscore, "_");
    
    /**
     * Jeton associe a la chaine 'non'
     */
    protected static final Jeton non = new Jeton(Jeton.Type.Non, "non");
   
	/**
     * Jeton associe a la chaine 'si'
     */
    protected static final Jeton si = new Jeton(Jeton.Type.Si, "si");
    
    /**
     * Jeton associe a la chaine 'alors
     */
    protected static final Jeton alors = new Jeton(Jeton.Type.Alors, "alors");
   
    /**
     * Jeton associe au caractere <.
     */
    protected static final Jeton inferieur = new Jeton(Jeton.Type.Inferieur, "<");
    
    /**
     * Jeton associe au caractere >.
     */
    protected static final Jeton superieur = new Jeton(Jeton.Type.Superieur, ">");
    
    /**
     * Jeton associe au caractere <=.
     */
    protected static final Jeton inferieurOuEgal = new Jeton(Jeton.Type.InferieurOuEgal, "<=");
    
    /**
     * Jeton associe au caractere >=.
     */
    protected static final Jeton superieurOuEgal = new Jeton(Jeton.Type.SuperieurOuEgal, ">=");
    
    /**
     * Jeton associe au caractere /=.
     */
    protected static final Jeton different = new Jeton(Jeton.Type.Different, "/=");
    
    /**
     * Jeton assoie à la fin d'expression.
     */
    protected static final Jeton finExpression = new Jeton(Jeton.Type.FinExpression, "");    

}