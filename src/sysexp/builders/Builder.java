package sysexp.builders;

public interface Builder {
	/**
	 * Permet de fabriquer un produit Pas à pas
	 * Si le produit est un assemblage de n sous-systèmes alors 
	 * la premiere invocation = ajout du premier sous-système
	 */
	public void buildPart();
}
