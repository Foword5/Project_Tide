package vu;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelAffichage extends JPanel{
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *GridBagConstraints gridbag 
	 */
	private GridBagConstraints gridbag = new GridBagConstraints();
	/**
	 *Image image 
	 */
	private Image image;
	/**
	 *JTabbedPane onglets 
	 */
	private JTabbedPane onglets;
	/**
	 *PanelPleineMer panelPleineMer 
	 */
	private PanelPleineMer panelPleineMer;
	/**
	 *PanelHeureParHeure panelHeureParHeure 
	 */
	private PanelHeureParHeure panelHeureParHeure;
	/**
	 *PanelGraphique panelGraphique 
	 */
	private PanelGraphique panelGraphique;
	/**
	 * PanelAffichage qui est le panel ou il y'a l'affichage des donnes dans 2 onglets differents avec une image en fonde mer
	 * @param image
	 */
	public PanelAffichage(Image image){

		this.image = image;
		setLayout(new GridBagLayout());
		gridbag.insets = new Insets(10,0,0,0);
		//Créer le conteneur des onglets
		onglets = new JTabbedPane();
		//Définir la position de conteneur d'onglets
		onglets.setPreferredSize (new Dimension(390,460));
		onglets.setBounds(600,600,600,600);
		//Associer chaque panneau à l'onglet correspondant
		//onglet 1
		panelPleineMer = new PanelPleineMer();
		onglets.add("Pleine et base mer",panelPleineMer);
		//onglet 2
		panelHeureParHeure = new PanelHeureParHeure();
		onglets.add("Heure par heure",panelHeureParHeure);
		//onglet 3
		panelGraphique = new PanelGraphique();
		onglets.add("Graphique",panelGraphique);
		//ajouter tout les onglets
		add(onglets,gridbag);

	}

	public Insets getInsets (){
		return new Insets (20,20,20,20);     //Bords sur la fenetre (N,O,S,E) = (haut,gauche,bas,droite)
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 5, null);
	}

	/**
	 * Methode pour obtenir le PanelPleineMer
	 * 
	 * @return le PanelPleineMer
	 */
	public PanelPleineMer getPanelPleineMer() {
		return panelPleineMer;
	}

	/**
	 * Methode pour obtenir le PanelHeureParHeure
	 * 
	 * @return le PanelHeureParHeure
	 */
	public PanelHeureParHeure getPanelHeureParHeure() {
		return panelHeureParHeure;
	}

	/**
	 * Methode pour obtenir le PanelGraphique
	 * 
	 * @return le PanelGraphique
	 */
	public PanelGraphique getPanelGraphique() {
		return panelGraphique;
	}
}
