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
			precharge= lexical.suivant();
		}
	/*'faits_booleens' est-il un jeton?
	 * if(precharge.estFaitBooleen()){
			precharge= lexical.suivant();
		}
	*/
		//'='
		if(!precharge.estOperateurEgal()){
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
	
	protected boolean estDeclarationSymbolique() throws IOException{
		return false;
		
	}
	protected boolean estDeclarationEntiere() throws IOException{
		return false;
		
	}
	protected boolean estFaitBooleen() throws IOException{
		if(!estIdentificateur()){
		return false;
		}
		return true;
		
	}
	protected boolean estFaitSymbolique() throws IOException{
		return false;
		
	}
	protected boolean estFaitEntier() throws IOException{
		return false;
		
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
/*	protected boolean estExpression() throws IOException{
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
	}*/
	
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
	

}//Fin de la classe

