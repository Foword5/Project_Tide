package vu;
import javax.swing.JButton;

import modele.Date;

public class BoutonDate extends JButton {

	/**
	 *Serial Id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *Date date 
	 */
	private Date date;
	/**
	 * BoutonDate qui prends la dare et la change en Integer
	 * @param date
	 */
	public BoutonDate(Date date) {
		super (Integer.toString(date.getJour()));
		this.date= date;
	}
	/**
	 * renvoi la dates
	 * @return
	 */
	public Date getDate() {		 
		return date;
	}

}
