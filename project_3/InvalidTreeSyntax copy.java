
/*
 * File: CMSC320PRJ3.java
 * Author: Chase Renick
 * Date: Feb 23, 2021
 * Purpose: Syntax class is to handle cases 
 * where and error message should be generated
 */

import javax.swing.JOptionPane;

@SuppressWarnings("serial")

public class InvalidTreeSyntax extends Exception {
	//No need for a constructor in this class
		public void showError() {
			JOptionPane.showMessageDialog(null, "Invalid string supplied.  Unequal number of parentheses or entry does not begin or "
					+ "end with parentheses. Unable to form tree.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

}
