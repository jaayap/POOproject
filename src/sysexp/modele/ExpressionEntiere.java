package sysexp.modele;

import java.util.HashMap;

public interface ExpressionEntiere {
	
	public long interpret(HashMap<String,FaitAbstrait> baseDeFaits) throws NoContainsKeyException;
}
