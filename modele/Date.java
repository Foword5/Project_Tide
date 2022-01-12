package modele;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import constantes.*;

public class Date implements Comparable <Date>,Constantes,Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Le jour
	 */
	private int jour;
	/**
	 * Le mois
	 */
	private int mois;
	/**
	 * L'année
	 */
	private int annee;
	/**
	 * Le jour de la semaine
	 */
	private int jourSemaine ;
	
	/**
	 * Constructeur de la classe Date
	 * 
	 * @param parJour le jour
	 * @param parMois le mois 
	 * @param parAnnee l'annee
	 */
	public Date(int parJour, int parMois, int parAnnee) {
		jour = parJour;
		mois = parMois;
		annee = parAnnee;
		
		GregorianCalendar date = new GregorianCalendar (annee,mois-1,jour);
		jourSemaine = date.get (Calendar.DAY_OF_WEEK);		
		if (jourSemaine == 1)
			jourSemaine = 7;
		else jourSemaine -= 1; 
	}
	
	/**
	 * Constructeur sans parametre, qui réfère à la date d'aujourd'hui
	 */
	public Date() { 
		GregorianCalendar dateAuj = new GregorianCalendar ();
		annee = dateAuj.get (Calendar.YEAR);
		mois = dateAuj.get (Calendar.MONTH) + 1; 
		jour = dateAuj.get (Calendar.DAY_OF_MONTH);
		jourSemaine = dateAuj.get (Calendar.DAY_OF_WEEK);
		if (jourSemaine == 1)
			jourSemaine = 7;
		else jourSemaine -= 1; 
	}
	
	/**
	 * Methode de comparaison
	 * retourne 0 si this et parDate sont égales, 
	 * -1 si this précède parDate,
	 *  1 si parDate précède this
	 */
	public int compareTo (Date parDate) {
		if (annee < parDate.annee)
			return -1;
		if (annee > parDate.annee)
			return 1;
		// les années sont =
		if (mois < parDate.mois)
			return -1;
		if (mois > parDate.mois)
			return 1;
		// les mois sont =
		if (jour < parDate.jour)
			return -1;
		if (jour > parDate.jour)
			return 1;
		return 0;	
	}
	
	/**
	 * Methode pour savoir si une date est aujord'hui
	 * 
	 * @return true si oui et false si non
	 */
	public boolean isToday() {
		if(this.compareTo(new Date()) == 0) return true;
		else return false;
	}
	
	/**
	 * Methode pour obtenir la date du lendemain
	 * 
	 * @return la date du lendemain
	 */
	public Date dateDuLendemain ()   {	
		if (jour < dernierJourDuMois(mois,annee))
			return  new Date (jour+1,mois,annee);
		else if (mois < 12)
			return new Date (1,mois+1,annee);
		else return new Date (1,1,annee+1);	
	}  

	/**
	 * Methode pour obtenir la date de la veille
	 * 
	 * @return la date de la veille
	 */
	public Date dateDeLaVeille () {    
		if (jour > 1)
			return  new Date (jour-1,mois,annee);
		else if (mois > 1)
			return new Date (Date.dernierJourDuMois(mois-1, annee),mois-1,annee);
		else return  new Date (31,12,annee-1);
	}

	/**
	 * Methode pour avoir le dernier jour d'un mois
	 * 
	 * @param parMois le mois
	 * @param parAnnee l'année
	 * @return le jour
	 */
	public static int dernierJourDuMois (int parMois, int parAnnee) {
		switch (parMois) {
		case 2 : if (estBissextile (parAnnee))  return 29 ; else return 28 ;  
		case 4 : 	 case 6 : 	 case 9 : 	 case 11 : return 30 ;
		default : return 31 ;
		}  // switch
	} 

	/**
	 * Methode pour savoir si une année est bissextile
	 * 
	 * @param parAnnee l'année
	 * @return un boolean
	 */
	private static boolean estBissextile(int parAnnee) {
		return parAnnee % 4 == 0 && (parAnnee % 100 != 0 || parAnnee % 400 == 0);
	}
	
	/**
	 * Methode toString
	 */
	public String toString() {
		String[] nomMois = new String[] {"janvier", "février","mars","avril","mai","juin","juillet", "août","septembre","octobre","novembre","décembre"};
		String [] nomJour = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
		
		
		return nomJour[jourSemaine-1] + " " + jour + " " + nomMois[mois-1] + " " + annee;
	}
	
	/**
	 * Methode pour obtenir l'année
	 * 
	 * @return l'année
	 */
	public int getAnnee() { 
		return annee;
	}
	
	/**
	 * Methode pour obtenir le jour
	 * 
	 * @return le jour
	 */
	public int getJour() { 
		return jour;
	}

	/**
	 * Methode pour obtenir le mois
	 * 
	 * @return le mois
	 */
	public int getMois() { 
		return mois;
	}
	
	/**
	 * Methode pour obtenir le jour de la semaine
	 * 
	 * @return le jour de la semaine
	 */
	public int getJourSemaine () {
		return jourSemaine;
	}
}