package constantes;

import java.util.TreeMap;

public interface Constantes {

	final String [] NOMS_PORT = {"Choisisser un port","test","test 2","A remplir"};

	final String [] JOURS_SEMAINE = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"} ; 

	final String [] JOURS_SEMAINE_ABR = {"lu","ma","me","je","ve","sa","di"} ;  

	final  String [] MOIS = {"Janvier", "Février","Mars","Avril","Mai","Juin","Juillet", "Août","Septembre","Octobre","Novembre","Décembre"};

	final String INTIT_PREMIER = "<<";
	final String INTIT_PRECEDENT = "<";
	final String INTIT_SUIVANT = ">";
	final String INTIT_DERNIER = ">>";
	final String [] INTITULES_BOUTONS = {INTIT_PREMIER,INTIT_PRECEDENT, INTIT_SUIVANT,INTIT_DERNIER};

	public final  String [] HEURES = {"00", "01", "02", "03", "04", "05", "06","07", "08", "09", "10", "11", "12", "13","14", "15", "16", "17", "18", "19", "20","21", "22", "23"};
	public final  String [] MINUTES = {"00","15","30","45"};
	
	final String lienFichier = "Data/Save/Ports";	
	
	/**
	 * Les ports ainsi que le liens pour y accéder (uniquement les données payantes)
	 */
	@SuppressWarnings("serial")
	public final TreeMap<String, String> portPayant = new TreeMap<String, String>() {{
		put("Audierne", "AUDIERNE_2021_legal");
		put("Boucan-Bayonne", "BOUCAU-BAYONNE_2021_legal");
		put("Brest", "BREST_2021_legal");
		put("Dunkerque", "DUNKERQUE_2021_legal");
		put("Entrée de la baie de Somme", "ENTREE_BAIE_DE_SOMME_2021_legal");
		put("Fort de France", "FORT-DE-FRANCE_2021_local");
		put("Granville,	 le Cocaleu", "GRANVILLE_LE_COCALEU_2021_legal");
		put("Ile Miquelon", "ILE_MIQUELON_2021_local");
		put("Le Palais", "LE_PALAIS_2021_legal");
		put("Paimpol", "PAIMPOL_2021_legal");
		put("Papeet, Fare Ute", "PAPEETE_FARE-UTE_2021_local");
		put("Pointe-à-pitre", "POINTE-A-PITRE_2021_local");
		put("Roscoff", "ROSCOFF_2021_legal");
		put("Saint-Malo", "SAINT-MALO_2021_legal");
		put("Saint-Martin", "SAINT-MARTIN_DE_RE_2021_legal");
		put("Saint-Nazaire", "SAINT-NAZAIRE_2021_legal");
		put("Toulon", "TOULON_2021_local");
	}};
	
	@SuppressWarnings("serial")
	public final TreeMap<String, String> portGratuit = new TreeMap<String, String>() {{
		put("Saint-Nazaire", "saint-nazaire-2021");
		put("Herbaudière","herbaudiere-2021");
	}};
}