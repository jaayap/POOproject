package sysexp.modele;

import java.util.HashMap;

public class ConstanteEntiere implements ExpressionEntiere{
	protected long valeur;
	
	public ConstanteEntiere(long valeur){
		this.valeur = valeur;
	}

	@Override
	public long interpret(HashMap<String, FaitAbstrait> baseDeFaits) throws NoContainsKeyException {
		return valeur;
	}
}
