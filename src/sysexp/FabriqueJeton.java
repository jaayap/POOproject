package sysexp;

/**
 * Classe representant une fabrique de jetons de la grammaire. 
 *
 * @note Cette classe etant a usage interne, elle est invisible depuis 
 *   l'exterieur du paquetage.
 */
class FabriqueJeton {
	 /**
     * Retourne le jeton associe a la "faits booleens".
     *
     * @return le jeton associe a la"faits booleens".
     */
    public static Jeton faitsBooleens() {
	return faits_booleens;
    }

    /**
     * Retourne le jeton associe a "faits symboliques".
     *
     * @return le jeton associe a "faits symboliques".
     */
    public static Jeton faitsSymboliques() {
	return faits_symboliques;
    }

    /**
     * Retourne le jeton associe a "faits entiers".
     *
     * @return le jeton associe a "faits_entiers".
     */
    public static Jeton faitsEntiers() {
	return faits_entiers;
    }
    

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
    public static Jeton comparateurEgal() {
    	return comparateurEgal;
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
     * Retourne le jeton associe a un fait.
     *
     * @param representation la representation d'un fait
     * @return le jeton associe a un fait.
     */
    public static Jeton fait(String representation) {
    	return new Jeton(Jeton.Type.fait, representation);
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
     * Retourne le jeton associe à 'et'.
     *
     * @return le jeton associe à 'et'.
     */
    public static Jeton et() {
    	return et;
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
     * Retourne le jeton associe à ','.
     *
     * @return le jeton associe à ','.
     */
    public static Jeton virgule() {
    	return virgule;
    }
    
    /**
     * Retourne le jeton associe à ';'.
     *
     * @return le jeton associe à ';'.
     */
    public static Jeton pointVirgule() {
    	return pointVirgule;
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
     * Jeton associe aux faits booleens.
     */
    protected static final Jeton faits_booleens = new Jeton(Jeton.Type.faits_booleens, "faits_booleens");
    /**
     * Jeton associe aux faits symboliques.
     */
    protected static final Jeton faits_symboliques = new Jeton(Jeton.Type.faits_symboliques, "faits_symboliques");
    /**
     * Jeton associe aux faits entiers.
     */
    protected static final Jeton faits_entiers = new Jeton(Jeton.Type.faits_entiers, "faits_entiers");

    
  
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
    protected static final Jeton comparateurEgal = new Jeton(Jeton.Type.ComparateurEgal, "=");
    
    
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
     * Jeton associe a la chaine 'et
     */
    protected static final Jeton et = new Jeton(Jeton.Type.Et, "et");
   
    /**
     * Jeton associe au caractere <.
     */
    protected static final Jeton inferieur = new Jeton(Jeton.Type.ComparateurInferieur, "<");
    
    /**
     * Jeton associe au caractere >.
     */
    protected static final Jeton superieur = new Jeton(Jeton.Type.ComparateurSuperieur, ">");
    
    /**
     * Jeton associe au caractere <=.
     */
    protected static final Jeton inferieurOuEgal = new Jeton(Jeton.Type.ComparateurInferieurOuEgal, "<=");
    
    /**
     * Jeton associe au caractere >=.
     */
    protected static final Jeton superieurOuEgal = new Jeton(Jeton.Type.ComparateurSuperieurOuEgal, ">=");
    
    /**
     * Jeton associe au caractere /=.
     */
    protected static final Jeton different = new Jeton(Jeton.Type.ComparateurDifferent, "/=");
    
    /**
     * Jeton associe au caractere ,.
     */
    protected static final Jeton virgule = new Jeton(Jeton.Type.Virgule, ",");
    
    /**
     * Jeton associe au caractere ;.
     */
    protected static final Jeton pointVirgule = new Jeton(Jeton.Type.PointVirgule, ";");
    
    /**
     * Jeton assoie à la fin d'expression.
     */
    protected static final Jeton finExpression = new Jeton(Jeton.Type.FinExpression, "");    

}