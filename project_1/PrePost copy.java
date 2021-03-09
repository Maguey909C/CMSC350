/*
 * File: CMIS350PRJ1RenickC.java
 * Author: Chase Renick
 * Date: Jan 25, 2021
 * Purpose: The PrePost class handles the actual computation of the algorithm.
 * Various functions look for the operator while other go through each line 
 * and push or pop things from the stack. Final method actually controls 
 * relations between buttons and what is executed.
 * 
 */

import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;

public class PrePost implements ActionListener {

	private static Stack<String> prepost = new Stack<>(); // Prefix to postfix stack
	private static Stack<String> postpre = new Stack<>(); // Postfix to prefix stack
	private JTextField resultsTextfield;
	private JTextField expressionField;

	public PrePost(JTextField expressionField, JTextField resultsTextfield) {
		this.expressionField = expressionField;
		this.resultsTextfield = resultsTextfield;
	} // End constructor PrePost

	// Function checks whether or not a user's input is an operator or not
	private boolean isOperator(char x) {
		switch (x) {
		case '-':
		case '*':
		case '/':
		case '+':
			return true;
		}
		return false;
	} // End method isOperator

	// Method stores what is at top of stack and pops it off stack
	private String peekAndPop(Stack<String> stackInQuesiton) {
		String operand = stackInQuesiton.peek();
		stackInQuesiton.pop();
		return operand;
	} // End method peekAndPop

	// Method concatenates two operands + the operator in a string
	private String concatOperands(String op_one, String op_two, char operator, boolean preToPost) {
		// If preTopost is true then concatenate with operator at end
		if (preToPost) {
			return op_one + op_two + operator;

		} else {
			return operator + op_two + op_one;
		}
	} // End method concatOperands

	// Method is designed to handle both prefixtopostfix and postfixtoprefix algos
	private void bothPreAndPost(String userInput, int userInputLength, boolean preToPo) throws SyntaxError {
		try {
			for (int i = 0; i <= userInputLength - 1; i++) {
				int actual = i;

				// If preToPost boolean is passed as compute Prepost algo
				if (preToPo) {
					actual = userInputLength - 1 - i;
					if (isOperator(userInput.charAt(actual))) {
						String op_one = peekAndPop(prepost);
						String op_two = peekAndPop(prepost);
						String op_final = concatOperands(op_one, op_two, userInput.charAt(actual), true);
						prepost.push(op_final);
					} else {
						prepost.push(userInput.charAt(actual) + "");
					}
					resultsTextfield.setText(String.join(" ", prepost.toString().split("")));

				} else {
					if (isOperator(userInput.charAt(i))) {
						String op_three = peekAndPop(postpre);
						String op_four = peekAndPop(postpre);
						String op_final2 = concatOperands(op_three, op_four, userInput.charAt(i), false);
						postpre.push(op_final2);
					} else {
						postpre.push(userInput.charAt(i) + "");
					}
					resultsTextfield.setText(String.join(" ", postpre.toString().split("")));
				}
//				
			} // end for loop

		} catch (Exception ex) {
			throw new SyntaxError();
		}
	} // End method postToPre

	@Override
	public void actionPerformed(ActionEvent commands) {
		try {
			String userInput = expressionField.getText(); // Get the Text
			String operators = commands.getActionCommand(); // Finding particular command
			int userInputLength = userInput.length();

			if (operators.equals("Prefix to Postfix")) {
				try {
					// Calls method that handles both but with true parameter for pre to post
					bothPreAndPost(userInput, userInputLength, true);
				} catch (Exception ex) {
					// If user has typed in consecutive integers and not space is found
					throw new SyntaxError();
				}
			} // End Prefix to Postfix
			else if (operators.equals("Postfix to Prefix")) {

				// Calls method that handles both but with false parameter for post to pre
				bothPreAndPost(userInput, userInputLength, false);

			} // End Postfix to Prefix
			else {
				throw new SyntaxError();
			} // End Else
		} catch (SyntaxError e) {
			e.showError();
		}
	} // End actionPerformed

} // End PrePost