package sysexp.modele;

import java.util.HashMap;

public interface OperateurBinaire extends ExpressionEntiere{
	
	public int add(ExpressionEntiere expression);
	public void remove(int which);
	public ExpressionEntiere getChild(int which);
	public long interpret(HashMap<String,FaitAbstrait> baseDeFaits) throws NoContainsKeyException;
}
