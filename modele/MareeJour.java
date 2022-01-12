package modele;

import java.io.Serializable;

/**
 * Classe réunissant les inforamtions d'une marée lors d'une journée
 *
 */
public class MareeJour implements Serializable {
	
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Liste des heures des deux possibles pleine mer de la journée
	 */
	private String[] heuresPleineMer = new String[2];
	/**
	 * Liste des hauteur de mer des deux possibles pleine mer de la journée
	 */
	private String[] hauteursPleineMer = new String[2];

	/**
	 * Liste des heures des deux possibles basse mer de la journée
	 */
	private String[] heuresBasseMer = new String[2];
	/**
	 * Liste des hauteur de mer des deux possibles basse mer de la journée
	 */
	private String[] hauteursBasseMer = new String[2];

	/**
	 * Liste des deux possibles coefficient de la journée
	 */
	private String[] coefficient = new String[2];

	/**
	 * Liste des hauteurs de mer pour chaques heures
	 */
	private String[] hauteursMer = new String[24];

	/**
	 * Constructeur prenant tout les attributs
	 * 
	 * @param parHeuresPleineMer Liste des heures des deux possibles pleine mer de la journée
	 * @param parHauteursPleineMer Liste des hauteur de mer des deux possibles pleine mer de la journée
	 * @param parHeuresBasseMer Liste des heures des deux possibles basse mer de la journée
	 * @param parHauteursBasseMer Liste des hauteur de mer des deux possibles basse mer de la journée
	 * @param parCoefficient Liste des deux possibles coefficient de la journée
	 * @param parHauteursMer Liste des hauteurs de mer pour chaques heures
	 */
	public MareeJour(String[] parHeuresPleineMer,String[] parHauteursPleineMer, String[] parHeuresBasseMer, String[] parHauteursBasseMer, String[] parCoefficient, String[] parHauteursMer){
		heuresPleineMer = parHeuresPleineMer;
		hauteursPleineMer = parHauteursPleineMer;
		heuresBasseMer = parHeuresBasseMer;
		hauteursBasseMer = parHauteursBasseMer;
		coefficient = parCoefficient;
		hauteursMer = parHauteursMer;
	}

	/**
	 * Constructeur ne prenant pas la liste des hauteurs de mer
	 * 
	 * @param parHeuresPleineMer Liste des heures des deux possibles pleine mer de la journée
	 * @param parHauteursPleineMer Liste des hauteur de mer des deux possibles pleine mer de la journée
	 * @param parHeuresBasseMer Liste des heures des deux possibles basse mer de la journée
	 * @param parHauteursBasseMer Liste des hauteur de mer des deux possibles basse mer de la journée
	 * @param parCoefficient Liste des deux possibles coefficient de la journée
	 */
	public MareeJour(String[] parHeuresPleineMer,String[] parHauteursPleineMer, String[] parHeuresBasseMer, String[] parHauteursBasseMer, String[] parCoefficient) {
		heuresPleineMer = parHeuresPleineMer;
		hauteursPleineMer = parHauteursPleineMer;
		heuresBasseMer = parHeuresBasseMer;
		hauteursBasseMer = parHauteursBasseMer;
		coefficient = parCoefficient;

		hauteursMer = new String[] {"--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--"};
	}

	/**
	 * Constructeur ne prenant que la liste des hauteurs de mer
	 * 
	 * @param parHauteursMer
	 */
	public MareeJour(String[] parHauteursMer){
		heuresPleineMer = new String[]{"--:--","--:--"};
		hauteursPleineMer = new String[]{"--","--"};
		heuresBasseMer = new String[]{"--:--","--:--"};
		hauteursBasseMer = new String[]{"--","--"};
		coefficient = new String[]{"--","--"};

		hauteursMer = parHauteursMer;
	}

	/**
	 * Constructeur ne prenant aucun argument, qui seront set ensuite.
	 */
	public MareeJour(){
		heuresPleineMer = new String[]{"--:--","--:--"};
		hauteursPleineMer = new String[]{"--","--"};
		heuresBasseMer = new String[]{"--:--","--:--"};
		hauteursBasseMer = new String[]{"--","--"};
		coefficient = new String[]{"--","--"};

		hauteursMer = new String[] {"--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--","--"};
	}

	/**
	 * Method toString de la classe
	 * 
	 * @return le string
	 */
	public String toString(){
		return "Heure (PM) : " + heuresPleineMer[0] + " ; hauteur (PM) : " + hauteursPleineMer[0] + 
				" ; coef. (PM) : " + coefficient[0] + " ; Heure (PM) : " + heuresPleineMer[1]+ " ; hauteur (PM) : " + hauteursPleineMer[1] +
				" ; coef (PM) : " + coefficient[1] + " ; Heure (BM) : " + heuresBasseMer[0] + " ; hauteur (BM) : " + hauteursBasseMer[0] +
				 " ; Heure (BM) : " + heuresBasseMer[1] + " ; hauteur (BM) : " + hauteursBasseMer[1];
	}
	
	/**
	 * Methode pour obtenir une heure de pleine mer de son choix
	 * 
	 * @param nbr numéro de la pleine mer
	 * @return l'heure de la pleine mer
	 */
	public String getHeurePleineMer(int nbr) {
		return heuresPleineMer[nbr-1];
	}

	/**
	 * Methode pour obtenir la hauteur de la pleine mer de son choix
	 * 
	 * @param nbr numéro de la basse mer
	 * @return hauteur de la basse mer
	 */
	public String getHauteurPleineMer(int nbr) {
		return hauteursPleineMer[nbr-1];
	}

	/**
	 * Methode pour obtenir une heure de basse mer de son choix
	 * 
	 * @param nbr numéro de la basse mer
	 * @return l'heure de la basse mer
	 */
	public String getHeureBasseMer(int nbr) {
		return heuresBasseMer[nbr-1];
	}

	/**
	 * Methode pour obtenir la hauteur de la basse mer de son choix
	 * 
	 * @param nbr numéro de la pleine mer
	 * @return hauteur de la pleine mer
	 */
	public String getHauteurBasseMer(int nbr) {
		return hauteursBasseMer[nbr-1];
	}
	/**
	 * Methode pour obtenir un coeficient de son choix
	 * 
	 * @param nbr numéro du coeficient
	 * @return le coeficient demandé
	 */
	public String getCoef(int nbr) {
		return coefficient[nbr-1];
	}

	/**
	 * Metode pour obtenir une hauteur de mer à l'heure de son choix
	 * 
	 * @param heure l'heure
	 * @return la hauteur
	 */
	public String getHauteur(int heure) {
		return hauteursMer[heure];
	}

	/**
	 * Methode pour définir les heures de pleine mer
	 * 
	 * @param heure les heures à définir
	 */
	public void setHeurePleineMer(String[] heure) {
		heuresPleineMer = heure;
	}

	/**
	 * Methode pour définir les hauteurs de la pleine mer
	 * 
	 * @param hauteurs les hauteurs à définir
	 */
	public void setHauteurPleineMer(String[] hauteurs) {
		hauteursPleineMer = hauteurs;
	}

	/**
	 * Methode pour définir les heures de basse mer
	 * 
	 * @param heure les heures à défnir
	 */
	public void setHeureBasseMer(String[] heure) {
		heuresBasseMer = heure;
	}

	/**
	 * Methode pour définir la hauteur de la pleine mer de son choix
	 * 
	 * @param hauteurs les hauteurs à définir
	 */
	public void setHauteurBasseMer(String[] hauteurs) {
		hauteursBasseMer = hauteurs;
	}
	/**
	 * Methode pour définir les coeficients
	 * 
	 * @param coef numéro du coeficient
	 */
	public void setCoef(String[] coef) {
		coefficient = coef;
	}

	/**
	 * Metode pour définir les hauteurs de mer
	 * 
	 * @param hauteurs les hauteurs
	 */
	public void setAllHauteur(String[] hauteurs) {
		hauteursMer = hauteurs;
	}
	
	/**
	 * Metode pour définir une hauteur de mer
	 * 
	 * @param heure l'heure
	 * @param hauteur l'hauteur
	 */
	public void setHauteur(int heure, String hauteur) {
		hauteursMer[heure] = hauteur;
	}

	/**
	 * Methode pour obtenir toute les hauteurs
	 * 
	 * @return
	 */
	public String[] getAllHauteurs() {
		return hauteursMer;
	}
}
