package vu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import constantes.Constantes;
import modele.Port;


public class MenuBar extends JMenuBar{
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * gridbag pour agencer les composant dans la fenetre
	 */
	GridBagConstraints gridbag = new GridBagConstraints();
	/**
	 JMenuBar menuBar
	 */
	JMenuBar menuBar = new JMenuBar();
	/**
	 *JMenu menu
	 */
	JMenu menu = new JMenu("Menu");
	/**
	 *JMenuItem listePorts,sauvegarde,apparence,quitter
	 */
	JMenuItem listePorts,sauvegarde,apparence,quitter;
	/**
	 *JMenu contact
	 */
	JMenu contact = new JMenu("Contact");
	/**
	 *JMenu aide
	 */
	JMenu aide = new JMenu("Aide");
	/**
	 *FenetreMere fenetreMere
	 */
	FenetreMere fenetreMere;

	/**
	 * Liste des ports avec les informations
	 */
	private Port[] ports;

	/**
	 * JMenu Bar
	 * @param fenetreMere
	 * @param parPorts
	 */
	public MenuBar(FenetreMere fenetreMere, Port[] parPorts) {
		this.fenetreMere = fenetreMere;
		
		ports = parPorts;

		this.add(menu);
		menu.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt){
				menu.setForeground(new Color(94,212,245));
			}
			public void mouseExited(MouseEvent evt){
				menu.setForeground(null);
			}
			public void mousePressed(MouseEvent evt){
			}
		});
		this.add(contact);
		contact.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt){
				contact.setForeground( new Color(94,212,245));
			}
			public void mouseExited(MouseEvent evt){
				contact.setForeground(null);
			}
			public void mousePressed(MouseEvent evt){
				try {
					FenetreMere.playSound("son/Démarrage navigation.wav");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				}
				contact();
			}
		});
		this.add(aide);
		aide.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt){
				aide.setForeground( new Color(94,212,245));
			}
			public void mouseExited(MouseEvent evt){
				aide.setForeground(null);
			}
			public void mousePressed(MouseEvent evt){
				if ( SwingUtilities.isLeftMouseButton (evt) ) {
					try {
						FenetreMere.playSound("son/Démarrage navigation.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					}
					aide();
				}
				else {
            		JFrame frame;
            		JFrame.setDefaultLookAndFeelDecorated(true);
            		frame = new JFrame("Fenetre Aide secret 👻");
            		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            		frame.setSize(670,310);
            		frame.setLocationRelativeTo(null);
            		frame.setBackground(Color.ORANGE); 
            		ImageIcon icon = new ImageIcon("image/infoSecret.png");
            		frame.add(new JLabel(icon));
            		frame.setForeground(new Color(47,47,47));
            		frame.setVisible(true);
            		frame.setResizable(false);
            		try {
            			fenetreMere.dispose();
						FenetreMere.playSound("son/aide2.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						e.printStackTrace();
					}
            		
            		frame.addWindowListener(new WindowAdapter() {
            	        public void windowClosing(WindowEvent et) {
            	        	try {
								@SuppressWarnings("unused")
								FenetreMere test = new FenetreMere();
							} catch (InterruptedException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
            	        }
            	    });
				}
			}
		});
		listePorts=new JMenuItem("Liste des Ports");  
		listePorts.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt){
				try {
					FenetreMere.playSound("son/Démarrage navigation.wav");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				}
				listePorts();
			}
		});
		//aparence
		apparence=new JMenuItem("Apparence");  
        apparence.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt){
            	fenetreMere.dispose();
            	JFrame frame;
        		frame = new JFrame("Apparence");
        		frame.setLayout(new GridBagLayout());
        		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        		frame.setSize(260,140);
        		frame.setLocationRelativeTo(null);
        		frame.setBackground(Color.ORANGE); 
        		frame.setForeground(new Color(47,47,47));
        		frame.setVisible(true);
        		//frame.setResizable(false);
        		gridbag.insets = new Insets(-50,0,0,0);
        		JLabel titre = new JLabel("Changer l'aparence des fenetres ? ");
        		frame.add(titre,gridbag);
        		gridbag.insets = new Insets(40,-180,0,0);
        		JButton style = new JButton("Changer");
        		style.setFocusPainted(false);
        		frame.add(style,gridbag);
        		style.addMouseListener(new MouseAdapter(){
                    public void mouseEntered(MouseEvent e) {
                    	style.setBackground(Color.ORANGE);
                    }
                    public void mouseExited(MouseEvent e){
                		style.setBackground(null);
                	}
                    @SuppressWarnings("static-access")
					public void mousePressed(MouseEvent e){
                    	frame.dispose();
                		try {
                			if (frame.isDefaultLookAndFeelDecorated() == true) {
                				JFrame.setDefaultLookAndFeelDecorated(false);
    							@SuppressWarnings("unused")
    							FenetreMere test = new FenetreMere();
                			}
                			else{
                    			JFrame.setDefaultLookAndFeelDecorated(true);
    							@SuppressWarnings("unused")
    							FenetreMere test = new FenetreMere();
                			}

						}
                		catch (InterruptedException | IOException e1) {
						}	
                    }
                });
        		frame.addWindowListener(new WindowAdapter() {
        	        public void windowClosing(WindowEvent et) {
                		try {
							@SuppressWarnings("unused")
							FenetreMere test = new FenetreMere();
						}
                		catch (InterruptedException | IOException e1) {
						}
        	        }
        	    });
            }
        });
        //supprimer la sauvegarde
		sauvegarde=new JMenuItem("Supprimer la sauvegarde"); 
		sauvegarde.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt){
				try {
					FenetreMere.playSound("son/Démarrage navigation.wav");
				}
				catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					
				}
				File fichier = new File(Constantes.lienFichier);
				fichier.delete();
				JOptionPane.showMessageDialog(null,"Sauvegarde bien supprimer","Sauvegarde Supprimer",JOptionPane.INFORMATION_MESSAGE,null);
				
			}
		});
		//quitter
		quitter=new JMenuItem("Quitter");
		quitter.setBackground(Color.RED);
		quitter.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				try {
					FenetreMere.playSound("son/Arrêt.wav");
					fenetreMere.setVisible(false);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException i) {
				}
				fenetreMere.dispose();
			}
		});
		
	menu.add(listePorts); menu.add(sauvegarde); menu.add(apparence); menu.add(quitter);
	}
	
	/**
	 * aide
	 */
	public void aide() {
		Object[] message = {
				"Bienvenue dans les Horaires des Marées",
				"Choissiez un port et une date",
				"                                          ",
				"il y'a 2 panel un qui indique les Marées",
				"avec les Hautes et basses meres",
				"et un autre qui les donner heure par heure",
				"                                           ",
				""};
		JOptionPane.showMessageDialog(null,message,"Aide",JOptionPane.INFORMATION_MESSAGE,null);
	}
	
	//contact
	public void contact() { 
		JLabel C= new JLabel("     © Projet JAVA 2021");
		C.setFont(new java.awt.Font("ARIAL", 0, 14));
		Object[] message = {
				C,
				"                             ",		
				"Front End, Benjamin Elbaz : ",
				mail("binyam.elbaz@gmail.com"),
				"                             ",
				"Back End, Jules Delamare : ",
				mail("pro.julesd@gmail.com"),
				"                             "};
		JOptionPane.showMessageDialog(null,message,"Contact",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("image/email.png")); 
	}

	//list Ports nouvelle fenetre avec le tabelau
	/**
	 * lsitePorts
	 */
	public void listePorts() {
		JFrame frame = new JFrame("Port");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(480,380);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		JScrollPane scrollPane;
		GridBagConstraints gridbag;
		String colone[]={"  ","Port"};
		Object [][]valeurs= {}; 
		JTable table;
		JLabel titre;
		JButton retour;
		frame.setLayout(new GridBagLayout());
		gridbag = new GridBagConstraints();
		table = new JTable();
		table.setModel(new DefaultTableModel(valeurs, colone));

		gridbag.gridx=0;
		//titre
		gridbag.insets = new Insets(-60,0,0,0);
		titre = new JLabel("Liste des Ports");
		frame.add(titre,gridbag);
		//modifier le modèle du composant
		table.setModel(new DefaultTableModel(valeurs, colone));
		table.setFocusable(false);
		table.setDefaultEditor(Object.class, null);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		custom.setHorizontalAlignment(JLabel.CENTER);
		for (int i=0 ; i < table.getColumnCount() ; i++)
			table.getColumnModel().getColumn(i).setCellRenderer(custom); 
		frame.add(table,gridbag);
		// Ajouter le composant dans un JScrollPane
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400,206));
		Font bigFont = new Font("sansserif", Font.PLAIN, 14);
		table.getTableHeader().setFont(bigFont);
		table.setRowHeight(30);
		gridbag.insets = new Insets(0,0,0,0);
		frame.add(scrollPane, gridbag);

		//bouton retour
		gridbag.insets = new Insets(0,0,-60,0);
		retour= new JButton("Retour");
		frame.add(retour,gridbag);
		retour.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				retour.setBackground(Color.ORANGE);
			}
			public void mouseExited(MouseEvent evt) {
				retour.setBackground(null);
			}
			public void mousePressed(MouseEvent evt) {
				frame.dispose();
			}	
		});
		//ajout
		for (int i=0;i<ports.length;i++) {
			((DefaultTableModel)table.getModel()).addRow(new Object[]{i+1, ports[i] });
		}
	}
	
	/**
	 * mail
	 * @param address
	 * @return
	 */
	public JLabel mail(String address) {
		JLabel label = new JLabel("<html><br><font size=3><a href=#>" + address + "</a></font></html>");
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().mail(new URI("mailto:" + address + "?subject=Projet-Java-Horaires-des-Marées-2021"));
				} catch (URISyntaxException | IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		return label;
	}
}
