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
		//'='
		if(!precharge.estOperateurEgal()){
			return false;
		}
		//'Faits_Booleens' : liste de fait   _______________HashMap<Fait, listeFaits>
		//if() precharge = lexical.suivant();
		
		// ';'
		if(!precharge.estPointVirgule()){
			return false;
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
		return false;
		
	}
	protected boolean estFaitSymbolique() throws IOException{
		return false;
		
	}
	protected boolean estFaitEntier() throws IOException{
		return false;
		
	}
	protected boolean estIdentificateur() throws IOException{
		return false;
		
	}
	protected boolean estListeFait() throws IOException{
		return false;
		
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

