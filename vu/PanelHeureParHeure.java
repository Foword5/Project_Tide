package vu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import modele.MareeJour;

public class PanelHeureParHeure extends JPanel {
	/**
	 *  Serial Id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *gridbag 
	 */
	GridBagConstraints gridbag = new GridBagConstraints();
	/**
	 * JLabel jourMois
	 */
	JLabel jourMois = new JLabel();
	/**
	 * JLabel rien
	 */
	JLabel rien;
	/**
	 *String[]
	 */
	String[] titre= {" "," "," "," "," "," "};
	/**
	 *JTable 
	 */
	JTable table = new JTable();
	
	/**
	 *PanelHeureParHeure qui affiche une Jtable dans l'onglet 
	 */
	public PanelHeureParHeure() {

		//titre
		jourMois = new JLabel("Choisisser un jour et un port ");
		jourMois.setFont(new java.awt.Font("Dialog", 2, 14));
		add(jourMois);
		jourMois.setFont(new java.awt.Font("Dialog", 1, 13));
		//rien : espace pour faire plus jolie entre le JLabel titre et la JTable
		rien = new JLabel("                                                                                                    ");
		add(rien);

		//JTABLE
		setValue(new MareeJour());
		table.setFocusable(false);
		table.setDefaultEditor(Object.class, null);
		table.setRowSelectionAllowed(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredSize(new Dimension(350,360));
		table.setRowHeight(45);
		add(table);


	}
	/**
	 * 
	 * @author binya
	 *
	 */
	public class CellObjectRenderer extends JLabel implements TableCellRenderer{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CellObjectRenderer() {
			super();
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int column){
			setText((String) value);
			if ((row%2) != 0) setBackground(null);
			else setBackground(new Color(220,220,220));

			if((isSelected) &&  (row%2) != 0) setBackground(new Color(184,207,229));

			return this;
		}
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
				{"00","01","02","03","04","05"},
				{maree.getHauteur(0),maree.getHauteur(1),maree.getHauteur(2),maree.getHauteur(3),maree.getHauteur(4),maree.getHauteur(5)},
				{"06","07","08","09","10","11"},
				{maree.getHauteur(6),maree.getHauteur(7),maree.getHauteur(8),maree.getHauteur(9),maree.getHauteur(10),maree.getHauteur(10)},
				{"12","13","14","15","16","17"},
				{maree.getHauteur(12),maree.getHauteur(13),maree.getHauteur(14),maree.getHauteur(15),maree.getHauteur(16),maree.getHauteur(17)},
				{"18","19","20","21","22","23"},
				{maree.getHauteur(18),maree.getHauteur(19),maree.getHauteur(20),maree.getHauteur(21),maree.getHauteur(22),maree.getHauteur(23)},
		};

		table.setModel(new DefaultTableModel(valeurs, titre));

		CellObjectRenderer objRender = new CellObjectRenderer(); 
		table.setDefaultRenderer(java.lang.Object.class,objRender);
		objRender.setHorizontalAlignment(JLabel.CENTER);
		for (int i=0 ; i < table.getColumnCount() ; i++)
			table.getColumnModel().getColumn(i).setCellRenderer(objRender); 
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getJourMois() {
		return jourMois;
	}

}