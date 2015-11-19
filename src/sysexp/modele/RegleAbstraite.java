package sysexp.modele;

import java.util.HashMap;

public interface RegleAbstraite {
	/**
	 * la classe mère des règles devra définir 
	 * un attribut numero (de type long puisque le nombre de règles peut être très important)
	 * dont la valeur sera donnée par le builder de la base de règles.
	 */
	public final long numero = 0;
	
	public boolean iterer(HashMap<String, FaitAbstrait> BaseDeFait);
}
