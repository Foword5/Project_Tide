package vu;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import constantes.Constantes;
import controleur.Controleur;
import modele.CalendrierDuMois;
import modele.Date;

public class PanelMois extends JPanel implements ActionListener,Constantes{
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *BoutonDate boutonSelectionne
	 */
	BoutonDate boutonSelectionne = new BoutonDate(new Date()) ;	
	/**
	 *ArrayList <BoutonDate> 
	 */
	ArrayList <BoutonDate> listeBoutons = new ArrayList<BoutonDate>();
	/**
	 *boutonDate boutonJour
	 */
	BoutonDate boutonJour;
	
	
	/**
	 * PanelMois qui affiche les mois dans un un panel avec tout les boutons du jour
	 * @param mois
	 */
	public PanelMois(int mois)  {
		Date today = new Date();

		Collection <Date> datesDuMois = new CalendrierDuMois(mois,today.getAnnee()).getDates();
		//System.out.println(datesDuMois);		
		this.setLayout(new GridLayout (0,7,8,8));
		for (int i= 0; i < 7 ; i++) {
			JLabel labeljour = new  JLabel (Constantes.JOURS_SEMAINE_ABR[i],JLabel.CENTER);
			this.add(labeljour);			
		}

		

		Iterator <Date> iterateur = datesDuMois.iterator();
		while (iterateur.hasNext()) {
			Date date = iterateur.next() ;
			boutonJour = new  BoutonDate (date);
			boutonJour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(94,212,245)));
			boutonJour.setFocusPainted(false);
			boutonJour.addActionListener(this); 
			this.add(boutonJour);
			listeBoutons.add(boutonJour);
			boutonJour.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent evt){
					/*
					if (SwingUtilities.isMiddleMouseButton (evt )) {
			            try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
							e.printStackTrace();
						}
			            UIManager.put("OptionPane.background", Color.BLACK);
			            UIManager.put("Panel.background", Color.BLACK);
			            UIManager.put("OptionPane.messageForeground", Color.WHITE);
						JOptionPane.showMessageDialog(null,date,"Date",JOptionPane.INFORMATION_MESSAGE,null);
					}*/
					if (SwingUtilities.isRightMouseButton (evt) ) {
						JOptionPane.showMessageDialog(null,"   "+date,"Date",JOptionPane.INFORMATION_MESSAGE,null);
					}
					UIManager.put("OptionPane.background",null);
					UIManager.put("Panel.background",null);
					UIManager.put("OptionPane.messageForeground",null);
				}
			});
			if (date.isToday()) {
				boutonJour.setBackground(Color.ORANGE);
			}	
			if (date.getMois()!=mois) {
				boutonJour.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(210,210,210)));
				boutonJour.setEnabled(false);	
			}
		}
	}

	/**
	 * actionPerformed
	 */
	public void actionPerformed(ActionEvent evt) {

		if (boutonSelectionne != null && !boutonSelectionne.getDate().isToday()) {
			boutonSelectionne.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,new Color(94,212,245)));
		}
		boutonSelectionne = (BoutonDate) evt.getSource();
		try {
			FenetreMere.playSound("son/Démarrage.wav");
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		boutonSelectionne.setFocusPainted(false);
		boutonSelectionne.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(200,00,0)));		
	}
	/**
	 * Collection<String>
	 * @return
	 */
	public Collection<String> getCollection() {
		return getCollection();
	}
	/**
	 * enregistreEcouteur
	 * @param controleur
	 */
	public void enregistreEcouteur(Controleur controleur) {
		for(BoutonDate bouton : listeBoutons) {
			bouton.setActionCommand("jour");
			bouton.addActionListener(controleur);
		}
	}
}
