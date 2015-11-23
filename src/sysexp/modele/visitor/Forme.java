package sysexp.modele.visitor;

public interface Forme {
	public void accept(VisiteurForme visiteur);
	
	public String getNomFait();
}
