package vu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import controleur.Controleur;
import modele.*;

public class PanelFils extends JPanel{
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *PanelAffichage panelDroite 
	 */
	PanelAffichage panelDroite;
	/**
	 *PanelSelection panelGauche 
	 */
	PanelSelection panelGauche;
	/**
	 *Controleur controleur 
	 */
	Controleur controleur;
	/**
	 *Liste des ports avec les informations 
	 */
	private Port[] ports;
	
	/**
	 * PanelFils ou il y'a les 2 panels celui de droite ou il y'a la selection de la date et du port et celui de gauche ou il y'a l'affichage
	 * @param parPorts
	 * @throws IOException
	 */
	public PanelFils(Port[] parPorts) throws IOException {
		
		ports = parPorts;
		setBackground(new Color (0,0,0));
		setLayout(new GridBagLayout());
		Image fondPanel = ImageIO.read(new File("image/mer-et-horizon.png"));
		panelDroite = new PanelAffichage(fondPanel);  
		panelGauche = new PanelSelection(ports);
		controleur = new Controleur(panelGauche,panelDroite);
		
		panelGauche.setPreferredSize (new Dimension(500,0));
		panelDroite.setPreferredSize (new Dimension(450,0));
		setLayout (new BorderLayout (50,50));
		
		add(panelDroite,BorderLayout.EAST);
		add(panelGauche, BorderLayout.WEST);
	}
	
	/**
	 * Insets
	 */
	public Insets getInsets (){
		return new Insets (5,20,20,20);    //Bords sur la fenetre (N,O,S,E) = (haut,gauche,bas,droite)
	}
}
