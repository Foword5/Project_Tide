package vu;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import constantes.Constantes;
import modele.MareeJour;

public class PanelPleineMer extends JPanel implements Constantes{
	/**
	 *  Serial Id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	GridBagConstraints gridbag = new GridBagConstraints();
	/**
	 * GridBagConstraints gridbag
	 */
	JLabel jourMois = new JLabel();
	/**
	 *JLabel jourMois
	 */
	JLabel rien;
	/**
	 * JLabel rien
	 */
	String[] titre= {"","Heure", "Hauteur", "Coeficient"};
	/**
	 * String[] 
	 */
	JTable table = new JTable();
	/**
	 * JTable
	 */
	JScrollPane scrollPane = new JScrollPane();
	/**
	 * JScrollPane
	 */

	/**
	 * PanelPleineMer qui affiche dans un onglet une JTable
	 */
	public PanelPleineMer() {

		//titre
		jourMois = new JLabel("Choisisser un jour et un port");
		jourMois.setFont(new java.awt.Font("Dialog", 2, 14));
		add(jourMois);
		jourMois.setFont(new java.awt.Font("Dialog", 1, 13));
		//rien espace pour faire plus jolie entre le JLabel titre et la JTable
		rien = new JLabel("                                                                                                    ");
		add(rien);
		add(rien);

		//JTABLE
		setValue(new MareeJour());
		
		table.setFocusable(false);
		table.setDefaultEditor(Object.class, null);
		table.setRowSelectionAllowed(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Ajouter le composant dans un JScrollPane
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(319,293));
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, -1,0));
		scrollPane.setOpaque(false);
		add(scrollPane);
		//appuyer sur les entetes des tables
		table.getTableHeader().setOpaque(false);
		table.setRowHeight(60);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(),50));
	}
	/**
	 * 
	 */
	public Insets getInsets() {
		return new Insets(10,0,0,0);
	}
	/**
	 * 
	 * @param maree
	 */
	public void setValue(MareeJour maree) {
		Object[][] valeurs = {
				{"BM",maree.getHeureBasseMer(1),maree.getHauteurBasseMer(1),"---"},
				{"PM",maree.getHeurePleineMer(1),maree.getHauteurPleineMer(1),maree.getCoef(1)},
				{"BM",maree.getHeureBasseMer(2),maree.getHauteurBasseMer(2),"---"},
				{"PM",maree.getHeurePleineMer(2),maree.getHauteurPleineMer(2),maree.getCoef(2)}
		};
		
		table.setModel(new DefaultTableModel(valeurs, titre));

		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		custom.setHorizontalAlignment(JLabel.CENTER);
		for (int i=0 ; i < table.getColumnCount() ; i++)
			table.getColumnModel().getColumn(i).setCellRenderer(custom); 
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getJourMois() {
		return jourMois;
	}

}