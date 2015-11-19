package sysexp.builders;

import java.io.IOException;

public interface Builder {
	/**
	 * Permet de fabriquer un produit pas à pas.
	 * Si le produit est un assemblage de n sous-systèmes alors 
	 * la premiere invocation = ajout du premier sous-système
	 * @return 
	 * @throws IOException 
	 */
	public void buildPart() throws IOException;
}
