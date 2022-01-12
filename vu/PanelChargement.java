package vu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import constantes.Constantes;
import modele.LectureEcriture;
import modele.LectureFichierTxt;
import modele.Port;

public class PanelChargement extends JPanel{
	
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * gridbag pour agencer les composant dans la fenetre
	 */
	GridBagConstraints gridbag = new GridBagConstraints();
	/**
	 * image de fond de chargement
	 */
	Image image;
	/**
	 * Jlabel titre,chargement
	 */
	JLabel titre,chargement = new JLabel(new ImageIcon());
	 /**
     * fenetre qui sert a fermer FenetreMere
     */
    FenetreMere fenetre; 
	/**
	 * Liste des ports avec les informations
	 */
    static Port[] ports;
    /**
     * boolean pour verifier si la fenetre chargement a ete fermer pendant son chargement
     */
    boolean vrai;
    /**
     * methode qui fait un chargement
     * @param fenetre pour pouvoir fermer FenetreMere
     * @param image pour avoir une image en fond 
     */
    @SuppressWarnings("static-access")
	public PanelChargement(FenetreMere fenetre,Image image){
    	this.fenetre = fenetre;
		this.image = image;
		//si la fenetre et deja decorer
		if (fenetre.isDefaultLookAndFeelDecorated() == true) {
			setBackground(new Color (0,0,0)); 
		}
		else {
			setBackground(null); 
		}
		setLayout(new GridBagLayout());
		gridbag.insets = new Insets(0, 0, 0, 0);
		
		//chargement
		gridbag.insets = new Insets(210, 0, 0, 0);
		chargement.setIcon(new ImageIcon("image/chargement.gif"));
		add(chargement, gridbag);
		chargement.repaint();
		
		//feremeture de la fenetre avec la croit en haut a droite pendant le chargement
		fenetre.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent et) {
	        	vrai=true;
	        	try {
	        		setVisible(false);
		            try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}
		            UIManager.put("OptionPane.background", Color.BLACK);
		            UIManager.put("Panel.background", Color.BLACK);
		            UIManager.put("OptionPane.messageForeground", Color.WHITE);
	        		Thread.sleep(500);
	        		FenetreMere.playSound("/son/Stop.wav");
	        		Object[] message = {"ERREUR CHARGEMENT INTERROMPU"};
	        		JOptionPane.showMessageDialog(null,message,"ERREUR",JOptionPane.ERROR_MESSAGE);
					FenetreMere.playSound("/son/Arrêt.wav");
					Thread.sleep(500);
					fenetre.dispose();
					System.exit(0);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
					e.printStackTrace();
				}
	        }
	    });
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask(){
		            public void run(){
		            	//ecriture/lecture fichier
		        		////////////////////////////////////////////////////////////////////////
		        		File fichier = new File(Constantes.lienFichier);
		        		if (fichier.length() != 0) {
		        			ports = (Port[]) LectureEcriture.lecture(fichier);
		        		}else {
		        			ports = LectureFichierTxt.getPorts().toArray(new Port[0]);
		        			LectureEcriture.ecriture(fichier,ports);
		        		}
		        		Arrays.sort(ports);
		        		/////////////////////////////////////////////////////////////////////////
		            	chargement.setIcon(null);
		            	fenetre.dispose();
		            	try {
		            		if (vrai == false) {
		            			FenetreMere.playSound("son/DémarrageWindows11.wav");
		            			new FenetreMere(1);
		            		}
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
							e.printStackTrace();
						}
		            }
		        },10
		);
	 }
	/**
	 * afficher l'image dans la fenetre
	 */
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 10, 5, null);
    }
	 
}




