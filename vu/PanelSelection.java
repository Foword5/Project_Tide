package vu;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import constantes.Constantes;
import controleur.Controleur;
import modele.*;

public class PanelSelection extends JPanel implements ActionListener{
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = -5058587555326374258L;
	/**
	 * GridBagConstraints gridbag
	 */
	GridBagConstraints gridbag = new GridBagConstraints();
	/**
	 * JPanel panelPort
	 */
	JPanel panelPort  = new JPanel();
	/**
	 * JPanel panelCalendrier
	 */
	JPanel panelCalendrier = new JPanel();
	/**
	 *JLabel titre,port
	 */
	JLabel titre,port;
	/**
	 *JComboBox<Port> choixPort
	 */
	JComboBox<Port> choixPort;
	/**
	 *Port[] ports
	 */
	private Port[] ports;
	/**
	 *JPanel panelNord
	 */
	private JPanel panelNord = new JPanel();       //Panel Centre
	/**
	 *JPanel panelCentre 
	 */
	private JPanel panelCentre = new JPanel();     //Panel Centre
	/**
	 * JPanel panelSud
	 */
	private JPanel panelSud = new JPanel();       //Panel Centre
	/**
	 *JLabel nameMois 
	 */
	private JLabel nameMois;
	/**
	 *JButton []boutons 
	 */
	private JButton []boutons = new JButton[Constantes.INTITULES_BOUTONS.length];								//creations de boutton depuis le talbeau de bouttons
	/**
	 *indiceMois 
	 */
	int indiceMois = 0;  
	/**
	 *int Index 
	 */
	static int Index = new Date().getMois()-1;
	/**
	 *int Annee 
	 */
	private int Annee = new Date().getAnnee();
	/**
	 * CardLayout card 
	 */
	CardLayout card = (new CardLayout (1,1)); 
	/**
	 * PanelMois[]
	 */
	private PanelMois[] panelMois;

	
	/**
	 * PanelSelection ou on selectionne une date et un port
	 * il y'a trois panel : 
	 * un haut qui contient le chois du port JComboBox
	 * un centre qui contient les jours
	 * et un bas qui contient les boutons pour changer de mois
	 * @param parPorts
	 */
	public PanelSelection(Port[] parPorts){
		ports = parPorts;
		panelPort.setLayout(new GridBagLayout());

		/********************************	
		* 
		//PANEL DU HAUT AVEC LE JCOMBOBOX
		* 
		********************************/
		//titre
		gridbag.gridy = 1;
		gridbag.insets = new Insets(10,20,0,0);
		titre = new JLabel("Horaires des Marées");
		titre.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 26));
		panelPort.add(titre,gridbag);

		//port
		gridbag.gridy = 3;
		gridbag.insets = new Insets(25,-150,0,20);
		port = new JLabel("Port : ");
		port.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 15));
		panelPort.add(port,gridbag);

		//choix du port
		gridbag.insets = new Insets(25,-190,0,0);
		//Integer[] array = numbersList.toArray(new Integer[0]); ----------Here
		choixPort = new JComboBox<Port>(ports);
		choixPort.setSelectedIndex(-1);
		choixPort.addActionListener(this);
		panelPort.add(choixPort,gridbag);
		
		/**********************************************
		* 
		//PANEL DU BAS CALENDRIER avec le choix du JOUR
		*
		**********************************************/

		panelCalendrier.setLayout(new BorderLayout(1,5));
		// Panel Nord
		nameMois = new JLabel(Constantes.MOIS[Index]+" "+Annee);
		nameMois.setFont(new Font("Arial",Font.BOLD,15));
		panelNord.add(nameMois);

		Date today = new Date();

		// Panel Centre
		panelCentre.setLayout(card); 		//ajoute le panelCentre au Card layout appeller card avec setLayout

		this.add (panelCentre, BorderLayout.CENTER);
		panelMois  = new PanelMois [Constantes.MOIS.length];

		panelCentre.setLayout (card);
		for (int indiceMois = 0 ; indiceMois< Constantes.MOIS.length ; indiceMois++){

			panelMois[indiceMois] = new PanelMois (indiceMois+1);	 
			panelCentre.add (panelMois[indiceMois], Constantes.MOIS[indiceMois]);

		}
		card.show(panelCentre,Constantes.MOIS[Index+1]);


		// placer le mois courant en haut de la pile
		card.show (panelCentre, Constantes.MOIS[today.getMois()-1]); 		
		indiceMois = today.getMois()-1;



		panelSud.setLayout (new FlowLayout ( FlowLayout.CENTER, 4,4));	
		for (int i = 0 ; i< Constantes.INTITULES_BOUTONS.length ; i++)		{
			boutons[i] = new JButton (Constantes.INTITULES_BOUTONS[i]);  
			boutons[i].setActionCommand(Constantes.INTITULES_BOUTONS[i]) ; 
			boutons[i].addActionListener (this);  
			boutons[i].setFont (new Font ("Verdana",Font.PLAIN,16));
			boutons[i].setFocusPainted(false);
			panelSud.add (boutons[i]) ;
		}
		//ajout du panel dans le panel calendrier
		setLayout(new BorderLayout(2,2));
		panelCalendrier.add(panelNord,BorderLayout.NORTH); 					//calendrier
		panelCalendrier.add(panelCentre,BorderLayout.CENTER); 
		panelCalendrier.add(panelSud,BorderLayout.SOUTH); 


		//ajout des panels sur le panel actuel
		setLayout (new BorderLayout (5,20)) ;
		add (panelPort,BorderLayout.NORTH);
		add (panelCalendrier,BorderLayout.CENTER);
	}

	/**
	 * actionPerformed
	 */
	public void actionPerformed(ActionEvent evt) {
		
		if(evt.getSource() == choixPort) {
			try {FenetreMere.playSound("son/Démarrage.wav");
			}
			catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
				}
		}
		try {
			switch (evt.getActionCommand()) {
			case Constantes.INTIT_PREMIER :{
				FenetreMere.playSound("son/slide-fin.wav");
				card.first (panelCentre);
				Index=0; 			
				break;
			}
			case Constantes.INTIT_PRECEDENT :{
				FenetreMere.playSound("son/slide.wav");
				card.previous (panelCentre);		 
				if (Index==0) Index=Constantes.MOIS.length-1;
				else --Index;

				break ;
			}
			case Constantes.INTIT_SUIVANT :{
				FenetreMere.playSound("son/slide.wav");
				card.next (panelCentre);		
				if (Index==Constantes.MOIS.length-1) Index=0;
				else ++Index;

				break;
			}
			case Constantes.INTIT_DERNIER : {
				FenetreMere.playSound("son/slide-fin.wav");
				card.last (panelCentre);
				Index=Constantes.MOIS.length-1;
				break;			
			}

			}// switch
			nameMois.setText(Constantes.MOIS[Index]+" "+Annee);	
		}catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
			System.out.println(e);
		}
	}
	/**
	 * Insets
	 */
	public Insets getInsets (){
		return new Insets (0,0,0,20);    //Bords sur la fenetre (N,O,S,E) = (haut,gauche,bas,droite)
	}
	/**
	 * getSelectedPort
	 * @return
	 */
	public Port getSelectedPort() {
		return (Port)choixPort.getSelectedItem();
	}
	/**
	 * enregistreurEcouteur
	 * @param controleur
	 */
	public void enregistreEcouteur(Controleur controleur) {
		choixPort.setActionCommand("port");
		choixPort.addActionListener(controleur);
		for(PanelMois panel : panelMois) {
			panel.enregistreEcouteur(controleur);
		}
	}
	
}