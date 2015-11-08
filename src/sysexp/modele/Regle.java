package sysexp.modele;

public interface Regle {
	/**
	 * la classe mère des règles devra définir 
	 * un attribut numero (de type long puisque le nombre de règles peut être très important)
	 * dont la valeur sera donnée par le builder de la base de règles.
	 */
	public long numero = 0;
}
