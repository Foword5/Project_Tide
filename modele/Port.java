package modele;

import java.io.Serializable;

public class Port implements Comparable <Port>, Serializable{
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nom du port
	 */
	private String nom;

	/**
	 * Tableau à deux dimmensions des MareeJour
	 */
	private MareeJour[][] marees = new MareeJour[12][31];

	/**
	 * Constructeur de la classe Port
	 * 
	 * @param parNom nom du port
	 */
	public Port(String parNom) {
		nom = parNom;
		for(int i=0;i<marees.length;i++) {
			for(int j=0;j<marees[i].length;j++) {
				marees[i][j] = new MareeJour(new String[] {"--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--"});
			}
		}
	}

	/**
	 * Methode toString de la classe
	 * 
	 * @return le nom du port
	 */
	public String toString(){
		return nom;
	}

	/**
	 * Methode pour définir une valeurs du tableau
	 */
	public void setJour(int jour, int mois, MareeJour mareeJour) {
		marees[mois-1][jour-1] = mareeJour;
	}

	/**
	 * Methode pour définir une ligne du tableau
	 */
	public void setMois(int mois, MareeJour[] mareeJour) {
		marees[mois-1] = mareeJour;
	}

	/**
	 * Methode pour definir la hauteur d'une heure précise
	 * 
	 * @param jour le jour
	 * @param mois le mois
	 * @param heure l'heure
	 * @param hauteur la hauteur
	 */
	public void setHauteur(int jour, int mois, int heure, String hauteur) {
		marees[mois-1][jour-1].setHauteur(heure,hauteur);
	}

	/**
	 * Methode pour obtenir une valeurs du tableau
	 * 
	 * @param jour le jour
	 * @param mois le mois
	 * @return la MareeJour
	 */
	public MareeJour getValue(int jour,int mois){
		return marees[mois-1][jour-1];
	}
	
	/**
	 * Methode pour comparer deux ports, la comparaison se fait alphabétiquement
	 * 
	 * 1 : supérieure
	 * -1 : inférieure
	 * 0 : égaux
	 */
	@Override
	public int compareTo(Port o) {
		return nom.compareTo(o.toString());
	}
}
