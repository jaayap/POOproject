package analyseurSyntaxique;


/**
 * Classe representant une fabrique de jetons de la grammaire. 
 *
 * @note Cette classe etant a usage interne, elle est invisible depuis 
 *   l'exterieur du paquetage.
 */
class FabriqueJeton {

    
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
     * Retourne le jeton associe a un chiffre positif.
     *
     * @return le jeton associe a un chiffre positif.
     */
    public static Jeton chiffrePositif() {
	return chiffrePositif;
    }
    
    /**
     * Retourne le jeton associe au chiffre zero.
     *
     * @return le jeton associe au chiffre zero.
     */
    public static Jeton zero() {
	return zero;
    }
    
    /**
     * Retourne le jeton associe a une lettre.
     *
     * @return le jeton associe a une lettre.
     */
    public static Jeton lettre() {
	return operateurDivise;
    }
    
    /**
     * Retourne le jeton associe au caractere _.
     *
     * @return le jeton associe au caractere _.
     */
    public static Jeton underscore() {
	return underscore;
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
     * Jeton associe a l'operateur d'addition.
     */
    protected static final Jeton operateurPlus = 
	new Jeton(Jeton.Type.OperateurPlus, "+");

    /**
     * Jeton associe a l'operateur de soustraction.
     */
    protected static final Jeton operateurMoins = 
	new Jeton(Jeton.Type.OperateurMoins, "-");

    /**
     * Jeton associe a l'operateur de multiplication.
     */
    protected static final Jeton operateurMultiplie = 
	new Jeton(Jeton.Type.OperateurMultiplie, "*");

    /**
     * Jeton associe a l'operateur de division.
     */
    protected static final Jeton operateurDivise = 
	new Jeton(Jeton.Type.OperateurDivise, "/");
    
    /**
     * Jeton associe a l'operateur d'égalité.
     */
    protected static final Jeton operateurEgal = 
	new Jeton(Jeton.Type.OperateurEgal, "/");
    
    /**
     * Jeton associe a un chiffre positif.
     */
    protected static final Jeton chiffrePositif = 
	new Jeton(Jeton.Type.ChiffrePositif, "/");
    
    /**
     * Jeton associe au chiffre zero.
     */
    protected static final Jeton zero = 
	new Jeton(Jeton.Type.Zero, "/");
    
    /**
     * Jeton associe a une lettre.
     */
    protected static final Jeton lettre = 
	new Jeton(Jeton.Type.Lettre, "/");
    
    /**
     * Jeton associe a une lettre.
     */
    protected static final Jeton underscore = 
	new Jeton(Jeton.Type.Underscore, "/");
    
    
    /**
     * Jeton assoie Ã  la fin d'expression.
     */
    protected static final Jeton finExpression = 
	new Jeton(Jeton.Type.FinExpression, "");    

}