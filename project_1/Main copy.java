/*
 * File: CMIS350PRJ1RenickC.java
 * Author: Chase Renick
 * Date: Jan 25, 2021
 * Purpose: This file contains the Main class and creates \
 * an instance of it to run the program. GUI contains two
 * JTextFields for entry and results, and two buttons for
 * Prefix algo and Postfix algo.
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main extends JFrame {
	/**
	 * Main class
	 */
	private static final long serialVersionUID = 1L;
	// When we make an instance of the class Main these are the variables that are
	// available to us
	JTextField expressionField; // The values that are used to manipulate the data of the array
	JTextField resultsTextfield;
	JLabel entry1, entry2; // References to aid the user in the program

	public Main() {
		// No need for constructors with this class

		// Making layout GridBagLayout ()
		setLayout(new GridBagLayout());

		// Instance of the GUI
		GridBagConstraints window = new GridBagConstraints();

		// Setting the grad layout
		window.insets = new Insets(3, 3, 3, 3);

		// Aligns and adds the expression label next to textField
		entry1 = new JLabel("Enter Expression: ");
		entry1.setHorizontalAlignment(JLabel.CENTER);
		window.fill = GridBagConstraints.VERTICAL;
		window.gridx = 1;
		window.gridy = 0;
		window.gridwidth = 1;
		add(entry1, window);

		// Aligns and adds the results label next to results box, i.e Textfield1
		entry2 = new JLabel("Results: ");
		entry2.setHorizontalAlignment(JLabel.CENTER);
		window.fill = GridBagConstraints.VERTICAL;
		window.gridx = 1;
		window.gridy = 3;
		window.gridwidth = 1;
		add(entry2, window);

		// Texfield next to expression
		expressionField = new JTextField(12);
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 2;
		window.gridy = 0;
		window.gridwidth = 1;
		add(expressionField, window);

		// Textfield next to results
		resultsTextfield = new JTextField(12);
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 2;
		window.gridy = 3;
		window.gridwidth = 1;
		resultsTextfield.setEditable(false);
		add(resultsTextfield, window);

		// JButton to move Prefix to Postfix
		JButton btnCompute = new JButton("Prefix to Postfix");
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 1;
		window.gridy = 2;
		window.gridwidth = 1;
		add(btnCompute, window);

		// JButton to move Postfix to Prefix
		JButton btnCompute2 = new JButton("Postfix to Prefix");
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 2;
		window.gridy = 2;
		window.gridwidth = 1;
		add(btnCompute2, window);

		// Make instances of events in the program
		PrePost listener = new PrePost(expressionField, resultsTextfield);

		// Both buttons for prefix and postfix attached to a listener
		btnCompute.addActionListener(listener);
		btnCompute2.addActionListener(listener);

		// Get basic parameters of a GUI
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JFrame frame = this;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				// Pops up a listener, and when the user selects the listener it will close
				// program if they select yes
				var option = JOptionPane.showConfirmDialog(frame, "Do you want to close this program?",
						"Please confirm", JOptionPane.YES_NO_OPTION);

				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			} // End method windowClosing()
		});
		pack();
		setTitle("Expression Converter");
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {

		new Main(); // Creates an instance of the Main class

	} // End main()
}
