package sysexp.modele;

public class NoContainsKeyException extends Exception{

	public NoContainsKeyException(String nom){
		System.out.println("le fait "+nom+" n'a pas été trouvé dans la base de faits");
	}


}
