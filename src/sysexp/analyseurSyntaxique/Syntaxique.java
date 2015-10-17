package sysexp.analyseurSyntaxique;
import java.io.IOException;

public class Syntaxique {
	protected Lexical lexical;
	protected Jeton precharge;
	
	public Syntaxique(Lexical lexical){
		this.lexical = lexical;
	}
	
	
	public boolean verifier() throws IOException{
		precharge = lexical.suivant();
		return estDeclaration() && precharge.estFinExpression();
		
	}
	
	//méthodes liées aux Déclarations
	protected boolean estDeclaration() throws IOException{
		
		// est declaration_booleene ou declatation_symbolique ou declaration_entiere
		if(!estDeclarationBooleene() || !estDeclarationSymbolique() || !estDeclarationEntiere()){
			return false;
		}
	
		return true;
	}
	
	protected boolean estDeclarationBooleene() throws IOException{
		//'faits_booleens' : mot clé
		if(!estFaitBooleen()){	
			return false;
		}
	/*'faits_booleens' est-il un jeton?
	 * if(precharge.estFaitBooleen()){
			precharge= lexical.suivant();
		}
	*/
		//'='
		if(!precharge.estComparateurEgal()){
			return false;
		}
		//'Faits_Booleens' : liste de fait   _______________HashMap<Fait, listeFaits>
		//if() precharge = lexical.suivant();
		
		if(!estFaitsBooleens()){
			return false;
		}
	
		// ';'
		if(!precharge.estPointVirgule()){
			return false;
		}
		return true;
		
	}

	
	protected boolean estDeclarationSymbolique() throws IOException{
		//faits_symboliques
		if(!estFaitSymbolique()){
			return false;
		}
		//'='
		if(!precharge.estComparateurEgal()){
			return false;
		}
		//Faits_Symboliques
		if(!estFaitsSymboliques()){
			return false;
		}
	
		// ';'
		if(!precharge.estPointVirgule()){
			return false;
		}
		return true;
		
	}
	protected boolean estDeclarationEntiere() throws IOException{
		//faits_entiers
		if(!estFaitEntier()){
			return false;
		}
		//'='
		if(!precharge.estComparateurEgal()){
			return false;
		}
		// Faits_Entier
		if(!estFaitsEntiers()){
			return false;
		}
	
		// ';'
		if(!precharge.estPointVirgule()){
			return false;
		}
		
		return true;
		
	}
	protected boolean estFaitsBooleens() throws IOException{
		//Fait_Booleen
		if(!estFaitBooleen()){
			return false;
		}
		// { ',' Fait_Booleen }
		while(precharge.estVirgule()){
			//on passe au suivant
			precharge = lexical.suivant();
			//Deriere une virgule il doit y avoir un fait_booleen
			if(!estFaitBooleen()){
				return false;
			}
		}
		return true;
	}
	
	protected boolean estFaitBooleen() throws IOException{
		if(!estIdentificateur()){
			return false;
		}
		return true;
		
	}
	protected boolean estFaitsSymboliques() throws IOException{
		//Fait_Symbolique
		if(!estFaitSymbolique()){
			return false;
		}
		// { ',' Fait_Symbolique }
		while(precharge.estVirgule()){
			//on passe au suivant
			precharge = lexical.suivant();
			//Deriere une virgule il doit y avoir un Fait_Symbolique
			if(!estFaitSymbolique()){
				return false;
			}
		}
		return true;
		
	}
	protected boolean estFaitSymbolique() throws IOException{
		if(!estIdentificateur()){
			return false;
		}
		return true;
		
	}
	protected boolean estFaitsEntiers() throws IOException{
		//Fait_Entier
		if(!estFaitEntier()){
			return false;
		}
		// { ',' Fait_Entier }
		while(precharge.estVirgule()){
			//on passe au suivant
			precharge = lexical.suivant();
			//Deriere une virgule il doit y avoir un fait_Entier
			if(!estFaitEntier()){
				return false;
			}
		}
		return true;
		
	}
	protected boolean estFaitEntier() throws IOException{
		if(!estIdentificateur()){
			return false;
		}
		return true;
		
	}
	protected boolean estIdentificateur() throws IOException{
		
		//Le premier caractere doit etre une lettre
		if(!estAlphanumerique()){
			return false;
		}
		//{['_'] Alphanumerique }
		while(precharge.estUnderscore()){
			precharge = lexical.suivant();
			if(!estAlphanumerique()){
				return false;
			}
		}
		return true;
	}
	
	protected boolean estAlphanumerique() throws IOException{
		if(!precharge.estEntier() || !precharge.estLettre()){
			return false;
		}
		return true;
	}
	// Methodes liées aux regles :
	protected boolean estRegles() throws IOException{
		
			//{ Regle ';' }
			while (estRegle()){
				precharge = lexical.suivant();
				// il doit obligatoirement y avoir un point virgule derriere une Regle
				if(!precharge.estPointVirgule()){
					return false;
				}
			}
			return true;
	}
	protected boolean estRegle() throws IOException{
		if(!estRegleSansPremisse() && !estRegleAvecPremisses()){
			return false;
		}
		return true;
	}
	
	protected boolean estRegleSansPremisse() throws IOException{
		if(!estConclusion()){
			return false;
		}
		return true;
	}
	protected boolean estRegleAvecPremisses() throws IOException{
		
		//'si'
		if(!precharge.estSi()){
			return false;
		}
		//Condition
		if(!estCondition()){
			return false;
		}
		//'alors'
		if(!precharge.estAlors()){
			return false;
		}
		//Conclusion
		if(!estConclusion()){
			return false;
		}
		return true;
	}
	// Méthodes liées aux Conclusions
	protected boolean estConclusion() throws IOException{
		if(!estConclusionBooleene() && !estConclusionSymbolique() && !estConclusionEntiere()){
			return false;
		}
		return true;
	}
	protected boolean estConclusionBooleene() throws IOException{
		
		//Fait_booleen ou 'non' Fait_Booleen
		while(!precharge.estNon()){
			if(!estFaitBooleen()){
				return false;
			}
		}
		while(precharge.estNon()){
			precharge = lexical.suivant();
			if(!estFaitBooleen()){
				return false;
			}
		}
		
		return true;
	}
	protected boolean estConclusionSymbolique() throws IOException{
		//Fait_symbolique
		if(!estFaitSymbolique()){
			return false;
		}
		//=
		if(!precharge.estComparateurEgal() && !precharge.estComparateurDifferent()){
			return false;
		}
		//Constante symbolique(=Identificateur) ou Fait_symbolique
		if(!estIdentificateur() && !estFaitSymbolique()){
			return false;
		}
		return true;
	
	}
	protected boolean estConclusionEntiere() throws IOException{
		//Fait_entier
		if(!estFaitEntier()){
			return false;
		}
		//Comparateur
		if(!estComparateur()){
			return false;
		}
		//Expression_Entiere
		if(!estExpressionEntiere()){
			return false;
		}
		return true;
	}
	
	protected boolean estComparateur() throws IOException{
		if(!precharge.estComparateurEgal() && !precharge.estComparateurDifferent() &&
		   !precharge.estComparateurInferieur() && !precharge.estComparateurInferieurOuEgal() &&
		   !precharge.estComparateurSuperieur() && !precharge.estComparateurSuperieurOuEgal()){
			
			return false;
		}
		return true;
	}
/*	protected boolean estConstanteSymbolique() throws IOException{
		return false;
	}*/
/*	protected boolean estConstanteEntiere() throws IOException{
		return false;
	}*/
	protected boolean estExpressionEntiere() throws IOException{
		
		// [Additif]
		if(precharge.estOperateurPlus() || precharge.estOperateurMoins()){
			precharge = lexical.suivant();
		}
		// Terme
		if(!estTerme()){
			return false;
		}
		//{Additif Terme}
		while(precharge.estOperateurPlus() || precharge.estOperateurMoins()){
			precharge = lexical.suivant();
			if(!estTerme()){
				return false;
			}
		}
		return true;
	}
	protected boolean estTerme() throws IOException{
		//Facteur
		if(!estFacteur()){
			return false;
		}
		//{Multiplicatif Facteur }
		while(precharge.estOperateurMultiplie() || precharge.estOperateurDivise()){
			precharge = lexical.suivant();
			if(!estFacteur()){
				return false;
			}
		}
		return true;
	}
	
	protected boolean estFacteur() throws IOException {
		//Entier
		if(precharge.estEntier()){
			precharge = lexical.suivant();
			return true;
		}
		if(!precharge.estParentheseOuvrante()){
			return false;
		}
		// (Expression)
		precharge = lexical.suivant();
		if(!estDeclaration()){
			return false;
		}
		if(precharge.estParentheseFermante()){
			precharge = lexical.suivant();
			return true;
		}
		return false;
	}
	
	// Methodes liées aux conditions
	protected boolean estCondition() throws IOException{
		//Premisse
		if(!estPremisse()){
			return false;
		}
		//{ et Premisse }
		while(precharge.estEt()){
			precharge = lexical.suivant();
			if(!estPremisse()){
				return false;
			}
		}
		return true;
	}
	
	protected boolean estPremisse() throws IOException{
		//Au niveau Semantique :
		//PremisseBooleene = ConclusionBooleene
		//PremisseSymbolique = ConclusionSymbolique
		//PremisseEntiere = ConclusionEntiere
		// Syntaxiquement on peut donc faire appel a ces methodes :
		if(!estConclusionBooleene() || !estConclusionSymbolique() || !estConclusionEntiere()){
			return false;
		}
		return true;
	}
	

}//Fin de la classe Syntaxique

