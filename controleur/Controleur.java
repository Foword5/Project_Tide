package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modele.*;
import vu.*;

public class Controleur implements ActionListener {

	/**
	 * Le panelAffichage
	 */
	PanelAffichage panelAffichage;

	/**
	 * le panelSelection
	 */
	PanelSelection panelSelection;

	/**
	 * La date sélectionné
	 */
	Date date = null;

	/**
	 * Le port sélectionné
	 */
	Port port = null;

	/**
	 * Constructeur de la classe
	 * 
	 * @param parPanelSelection le panelSelection
	 * @param parPanelAffichage le panelAffichage
	 */
	public Controleur(PanelSelection parPanelSelection, PanelAffichage parPanelAffichage) {
		panelAffichage = parPanelAffichage;
		panelSelection = parPanelSelection;

		panelSelection.enregistreEcouteur(this);
		panelAffichage.getPanelGraphique().enregistreEcouteur(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "jour") {
			date = ((BoutonDate)e.getSource()).getDate();
			if(port == null) {
				panelAffichage.getPanelPleineMer().getJourMois().setText("Choisisser un port");
				panelAffichage.getPanelHeureParHeure().getJourMois().setText("Choisisser un port");
				return;
			}else {
				MareeJour maree = port.getValue(date.getJour(), date.getMois());
				panelAffichage.getPanelPleineMer().setValue(maree);
				panelAffichage.getPanelHeureParHeure().setValue(maree);

				panelAffichage.getPanelPleineMer().getJourMois().setText(port.toString() + " : " + date.toString());
				panelAffichage.getPanelHeureParHeure().getJourMois().setText(port.toString() + " : " + date.toString());
			}
			return;
		}
		else if(e.getActionCommand() == "port") {
			port = panelSelection.getSelectedPort(); 
			if(date == null) {
				panelAffichage.getPanelPleineMer().getJourMois().setText("Choisisser un jour");
				panelAffichage.getPanelHeureParHeure().getJourMois().setText("Choisisser un jour");
				return;
			}else {
				MareeJour maree = port.getValue(date.getJour(), date.getMois());
				panelAffichage.getPanelPleineMer().setValue(maree);
				panelAffichage.getPanelHeureParHeure().setValue(maree);

				panelAffichage.getPanelPleineMer().getJourMois().setText(port.toString() + " : " + date.toString());
				panelAffichage.getPanelHeureParHeure().getJourMois().setText(port.toString() + " : " + date.toString());
			}
			return;
		}
		else if(e.getActionCommand() == "ouvrir") {
			if(port == null && date == null) {
				JOptionPane.showMessageDialog(null,"Veuillez choisir un port et un jour","Sélection manquante",JOptionPane.INFORMATION_MESSAGE,null);
				return;
			}else if(port == null) {
				JOptionPane.showMessageDialog(null,"Veuillez choisir un port","Sélection manquante",JOptionPane.INFORMATION_MESSAGE,null);
				return;
			}else if(date == null) {
				JOptionPane.showMessageDialog(null,"Veuillez choisir un jour","Sélection manquante",JOptionPane.INFORMATION_MESSAGE,null);
				return;
			}

			List<Float> donnees = new ArrayList<Float>();
			List<String> l1 = new ArrayList<String>();
			List<String> l2 = new ArrayList<String>();
			l2.add("0");
			l1.add("0");l1.add("1");l1.add("2");l1.add("3");l1.add("4");l1.add("5");l1.add("6");l1.add("7");l1.add("8");l1.add("9");l1.add("10");l1.add("11");l1.add("12");l1.add("13");l1.add("14");l1.add("15");l1.add("16");l1.add("17");l1.add("18");l1.add("19");l1.add("20");l1.add("21");l1.add("22");l1.add("23");

			String[] hauteurs = port.getValue(date.getJour(), date.getMois()).getAllHauteurs();

			for(String hauteur : hauteurs) {
				try {
					donnees.add(Float.parseFloat(hauteur));
				} catch (final NumberFormatException error) {
					donnees.add(0f);
				}
			}

			JFrame f = new JFrame("Diagramme des Marées heure par heure");
			f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			f.setBounds(10,10,700,500);
			
			FreeChart g = new FreeChart("Diagramme Marées Heure par Heure","Heure", "Hauteur en M", donnees, Color.white, l2, l1, false);
			f.add(g);
			f.setVisible(true);
			f.setLocationRelativeTo(null);
		}
	}
	/**
	 * 
	 */
}
