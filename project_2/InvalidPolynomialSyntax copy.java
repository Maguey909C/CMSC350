/*
 * CMSC350 Project 2: Polynomial Strong and Weak checks
 * Author: Chase Renick
 * Date: Feb 9, 2021
 * Purpose: Error class for an unchecked exception, 
 * throws JOption pane with a user specified error in window
 */

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class InvalidPolynomialSyntax extends RuntimeException {
	
	public InvalidPolynomialSyntax (String message) {
		JOptionPane.showMessageDialog(null, message, "Error",
				JOptionPane.ERROR_MESSAGE);
		
	}
	
	
}
