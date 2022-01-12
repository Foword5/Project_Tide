package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import constantes.Constantes;


public class LectureFichierTxt implements Constantes {

	/**
	 * Methode pour lire les fichier du SHOM payant
	 * @param fichier le fichier à lire
	 * @param port le port à compléter
	 * @param mois le mois correspondant
	 * @return le port compléter
	 */
	public static Port lecturePayante (File fichier, Port port, int mois) {
		Port result = port;

		BufferedReader bufferedreader = null;
		FileReader filereader = null;

		try {
			filereader = new FileReader(fichier);
			bufferedreader = new BufferedReader(filereader);
			String strCurrentLine;

			for(int i=0;i<4 && (bufferedreader.readLine()) != null;i++);

			while ((strCurrentLine = bufferedreader.readLine()) != null) {
				String[] info = strCurrentLine.split("\t");

				MareeJour maree = new MareeJour();
				maree.setHeurePleineMer(new String[]{info[1],info[4]});
				maree.setHauteurPleineMer(new String[]{info[2],info[5]});
				maree.setCoef(new String[]{info[3],info[6]});
				maree.setHeureBasseMer(new String[]{info[7],info[9]});
				maree.setHauteurBasseMer(new String[]{info[8],info[10]});

				result.setJour(Integer.parseInt(info[0].split("-")[2]), mois, maree);
			}
			bufferedreader.close();
			filereader.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		return result;
	}

	/**
	 * Methode pour lire les fichiers du SHOM gratuit 
	 * @param fichier le fichier à lire
	 * @param port le port à compléter
	 * @return le port compléter
	 */
	public static Port lectureGratuite(File fichier,Port port) {

		Port result = port;
		
		BufferedReader bufferedreader = null;
		FileReader filereader = null;

		try {
			filereader = new FileReader(fichier);
			bufferedreader = new BufferedReader(filereader);
			String strCurrentLine;

			for(int i=0;i<14 && (bufferedreader.readLine()) != null;i++);

			while ((strCurrentLine = bufferedreader.readLine()) != null) {
				String[] info = strCurrentLine.split(";");

				if(Integer.parseInt(info[0].split(" ")[1].split(":")[1]) == 0) {
					String[] date = info[0].split(" ")[0].split("/");
					int heure = Integer.parseInt(info[0].split(" ")[1].split(":")[0]);

					result.setHauteur(Integer.parseInt(date[0]), Integer.parseInt(date[1]), heure, info[1]);
				}
			}
			bufferedreader.close();
			filereader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return result;
	}

	/**
	 * Methode qui lit tout les fichiers nécessaire pour créer la liste des ports
	 * @return la liste des ports
	 */
	public static ArrayList<Port> getPorts(){
		ArrayList<Port> ports = new ArrayList<Port>();

		//on créer des ports pour toute des données payantes
		Set<Map.Entry<String, String>> set = Constantes.portPayant.entrySet();
		Iterator<Map.Entry<String, String>> iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, String> portItr = (Map.Entry<String, String>)iterator.next();
			Port port = new Port(portItr.getKey());
			for(int i=6; i<=9;i++){
				File fichier = new File("data/Payante/0"+i+"-"+portItr.getValue()+"/0"+i+"-"+portItr.getValue()+".txt");
				//on obtient quelque chose de la forme "data/Payante/06-AUDIERNE_2021_legal/06-AUDIERNE_2021_legal.txt"
				port = lecturePayante(fichier,port,i);
			}
			ports.add(port);
		}

		//on ajoute les données gratuites ou on créer des nouveaux ports pour
		set = Constantes.portGratuit.entrySet();
		iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, String> portItr = (Map.Entry<String, String>)iterator.next();
			Port port = null;
			File fichier = new File("data/gratuite/"+portItr.getValue()+".txt");
			for(Port portPayant : ports) {
				if(portPayant.toString() == portItr.getKey()) {
					port = portPayant;
					port = lectureGratuite(fichier,port);
					break;
				}
			}
			if(port == null) {
				port =  new Port(portItr.getKey());
				port = lectureGratuite(fichier,port);
				ports.add(port);
			}
		}

		return ports;
	}
}
