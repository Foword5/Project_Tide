package vu;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class FenetreMere extends JFrame{

	/**
	 *Serial Id 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * contentPane
	 */
	static PanelFils contentPane;
	/**
	 * PanelChargement
	 */
	static PanelChargement contentPaneChargement;

	
	/**
	 * FenetreMere()
	 * @throws InterruptedException
	 * @throws IOException
	 */
	//Fenetre de chargement
	public FenetreMere() throws InterruptedException, IOException {
		super("chargement ...");
		Image fondPanel = ImageIO.read(new File("image/fond.png")); 
		contentPaneChargement = new PanelChargement(this,fondPanel);  
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setSize (534,320); 
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/load.jpg"));
		setContentPane(contentPaneChargement);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	/**
	 * fenetre Horaires Marées
	 * @param i
	 * @throws MalformedURLException
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public FenetreMere(int i) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException{
		super("Horaires Marées");
		contentPane = new PanelFils(PanelChargement.ports);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setSize (1010,655); 
		setContentPane(contentPane);
		setJMenuBar(new MenuBar(this,PanelChargement.ports));
		contentPane.setBackground(null);       //Couleur entre les deux panel
		setVisible(true);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/icone.png"));
		setResizable(false);
		revalidate();
		repaint();
        //feremeture de la fenetre avec la croit en haut a droite
        this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent et) {
	        	try {
	        		setVisible(false);
					playSound("/son/Arrêt.wav");
					Thread.sleep(1000);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
					e.printStackTrace();
				}
	        }
	        
	    });
	}
	
	/**
	 * son 
	 * @param soundFile String pour indiquer quelle fichier on va lire pour jouer un son 
	 * @throws MalformedURLException
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public static void playSound(String soundFile) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException{
	    File f = new File("./" + soundFile);
	    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
	    Clip clip = AudioSystem.getClip();
	    clip.open(audioIn);
	    clip.start();
	}
	
	/**main
	 * @param args
	 * @throws MalformedURLException
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 */
	public static void main (String [] args) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		new FenetreMere();
		//appelle directe sans le chargement :
		//new FenetreMere(1);
	}
	
}
/*\ 		   /*\
 * JAVA PROJECT *
/*\			   /*\
/*"------------------------------------------------------------------------------------------------"
	"------------------------------------------------------------------------------------------------"
	"-------------------oooooo-----------o--------oooooo-----oooo--------o---------------------------"
	"---------------------`888----------888--------`888-------8'--------888--------------------------"
	"----------------------888---------8`888--------`888-----8'--------8`888-------------------------"
	"----------------------888--------8'-`888--------`888---8'--------8'-`888------------------------"
	"----------------------888-------88ooo8888--------`888-8'--------88ooo8888-----------------------"
	"----------------------888------8'-----`888--------`888'--------8'-----`888----------------------"
	"-------------------o--88P----o88o-----o8888o-------`8'-------o88o-----o8888o--------------------"
	"------------------`Y888P------------------------------------------------------------------------"
	"------------------------------------------------------------------------------------------------"
	"------------------------------------------------------------------------------------------------"
	"-----oooooooooo----ooooooooo------oooooo-----oooooo-oooooooooooo----oooooo----ooooooooooooo-----"
	"-----`888---`Y88--`888---`Y88---d8P'--`Y8b-----`888-`888'-----`8--d8P'--`Y8b--8'---888---`8-----"
	"------888----d88'--888----d88'-888------888-----888--888---------888---------------888----------"
	"------888ooo88P'---888ooo88P'--888------888-----888--888oooo8----888---------------888----------"
	"------888----------888`88b-----888------888-----888--888----'----888---------------888----------"
	"------888----------888--`88b---`88b----d88'-----888--888-------o-`88b----ooo-------888----------"
	"-----o888o--------o888o--o888o--`Y8bood8P'--o---88P-o888ooooood8--`Y8bood8P'------o888o---------"
	"-------------------------------------------`Y8888P----------------------------------------------"
	"------------------------------------------------------------------------------------------------"*/
//////////////////////////////////////////////////////////////////////////////////////////////////////
										//© Projet JAVA TUTORE 2021\\