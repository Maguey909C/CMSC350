/*
 * File: CMIS350PRJ1RenickC.java
 * Author: Chase Renick
 * Date: Jan 25, 2021
 * Purpose: Syntax class is to handle cases 
 * where and error message should be generated
 */

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class SyntaxError extends Exception {
	//No need for a constructor in this class
	public void showError() {
		JOptionPane.showMessageDialog(null, "Invalid input, please enter correct input data", "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
