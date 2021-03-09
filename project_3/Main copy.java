/*
 * CMSC350 Project 3: Binary Tree
 * Author: Chase Renick
 * Date: Feb 20, 2021
 * Purpose: The Main Class is supposed to generate the GUI allow users to build a tree,
 * check the nodes contents, height of tree, etc.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Main extends JFrame implements ActionListener {
	/**
	 * Main class
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton openButton = new JButton("Make Tree");
	private JButton balancedButton = new JButton("Is Balanced?");
	private JButton isFullButton = new JButton("Is Full?");
	private JButton isProperButton = new JButton("Is Proper?");
	private JButton heightButton = new JButton("Height");
	private JButton nodesButton = new JButton("Nodes");
	private JButton inOrderButton = new JButton("InOrder");
	private JLabel entry1, entry2;
	private JTextField entryTextfield, outputTextField;
	

	public Main() {
		// No need for constructors with this class

		// Making layout GridBagLayout ()
		setLayout(new GridBagLayout());

		// Instance of the GUI
		GridBagConstraints window = new GridBagConstraints();

		// Setting the grid layout
		window.insets = new Insets(3, 3, 3, 3);

		// Label adjacent to Textfield1
		entry1 = new JLabel("Enter Tree:");
		entry1.setHorizontalAlignment(JLabel.LEFT);
		window.fill = GridBagConstraints.HORIZONTAL;
		window.gridx = 1;
		window.gridy = 1;
		window.gridwidth = 1;
		add(entry1, window);
		
		// Label adjacent to TextField2
		entry2 = new JLabel("Output:");
		entry2.setHorizontalAlignment(JLabel.LEFT);
		window.fill = GridBagConstraints.HORIZONTAL;
		window.gridx = 1;
		window.gridy = 3;
		window.gridwidth = 1;
		add(entry2, window);
		
		// Entry textfield that is editable
		entryTextfield = new JTextField(40);
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 2;
		window.gridy = 1;
		window.gridwidth = 2;
		add(entryTextfield, window);
		
		// Output textfield that is not editable
		outputTextField = new JTextField(40);
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 2;
		window.gridy = 3;
		window.gridwidth = 1;
		outputTextField.setEditable(false);
		add(outputTextField, window);
		
		// Open Button does ...
		openButton.getPreferredSize();
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 1;
		window.gridy = 2;
		window.gridwidth = 1;
		add(openButton, window);
		
		// Balanced Button Button does ...
		balancedButton.getPreferredSize();
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 2;
		window.gridy = 2;
		window.gridwidth = 1;
		add(balancedButton, window);
		
		// isFullButton Button does ...
		isFullButton.getPreferredSize();
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 3;
		window.gridy = 2;
		window.gridwidth = 1;
		add(isFullButton, window);
		
		// IsProper Button Button does ...
		isProperButton.getPreferredSize();
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 4;
		window.gridy = 2;
		window.gridwidth = 1;
		add(isProperButton, window);
		
		// Height Button does ...
		heightButton.getPreferredSize();
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 5;
		window.gridy = 2;
		window.gridwidth = 1;
		add(heightButton, window);
		
		// Nodes Button does ...
		nodesButton.getPreferredSize();
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 6;
		window.gridy = 2;
		window.gridwidth = 1;
		add(nodesButton, window);
		
		// InOrder Button does ...
		inOrderButton.getPreferredSize();
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 7;
		window.gridy = 2;
		window.gridwidth = 1;
		add(inOrderButton, window);

		// Make instances of events in the program
//		BinaryTree listener = new BinaryTree(entryTextfield, outputTextField);
		
		// All Buttons related to actions to be performed on entryTextField
		openButton.addActionListener(this);
		balancedButton.addActionListener(this);
		isFullButton.addActionListener(this);
		isProperButton.addActionListener(this);
		heightButton.addActionListener(this);
		nodesButton.addActionListener(this);
		inOrderButton.addActionListener(this);
		
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
		setTitle("Binary Tree Categorizer");
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {

		new Main(); // Creates an instance of the Main class

	} // End main()

	@Override
	public void actionPerformed(ActionEvent e) {
		String userInput = entryTextfield.getText();
		String userSelection = e.getActionCommand();
		BinaryTree root=null;
		try {
		//Actions performed with the open button
			if (userSelection.equals("Make Tree")) { 
				try {
					if (BinaryTree.checkParentheses(userInput)) {
						root = BinaryTree.stringToTree(userInput, 1, userInput.length()-1);
						BinaryTree.showTree(root);
					} else {
						throw new Exception();
						}
			
				} catch (Exception ex) {
					throw new InvalidTreeSyntax();
				}
			}
			
			else if (userSelection.equals("Is Balanced?")) {
				try {
					if (BinaryTree.checkParentheses(userInput)) {
					root = BinaryTree.stringToTree(userInput, 1, userInput.length()-1);
					outputTextField.setText(String.valueOf(BinaryTree.treeBalanced(root)));
					} else {
						throw new Exception();
						}
				} catch (Exception ex) {
					throw new InvalidTreeSyntax();
				}
			}
			//Checks if the tree if full
			else if (userSelection.equals("Is Full?")) {
				try {
					if (BinaryTree.checkParentheses(userInput)) {

					root = BinaryTree.stringToTree(userInput, 1, userInput.length()-1);
					outputTextField.setText(String.valueOf(BinaryTree.treeFull(root)));
					} else {
						throw new Exception();
						}
				} catch (Exception ex) {
					throw new InvalidTreeSyntax();
				}
			}		
			//Checks is proper
			else if (userSelection.equals("Is Proper?")) {
				try {
					if (BinaryTree.checkParentheses(userInput)) {

					root = BinaryTree.stringToTree(userInput, 1, userInput.length()-1);
					outputTextField.setText(String.valueOf(BinaryTree.treeProper(root)));
					} else {
						throw new Exception();
						}
				} catch (Exception ex) {
					throw new InvalidTreeSyntax();
				}
			}
			
			//Checks the height of the tree
		if (userSelection.equals("Height")) {
				try {
					if (BinaryTree.checkParentheses(userInput)) {

					root = BinaryTree.stringToTree(userInput, 1, userInput.length()-1);
					outputTextField.setText(String.valueOf(BinaryTree.treeHeight(root)));
					} else {
						throw new Exception();
						}
				} catch (Exception ex) {
					throw new InvalidTreeSyntax();
				}
				
			}
			//Checks how many nodes are on the tree
			else if (userSelection.equals("Nodes")) {
				try {
					if (BinaryTree.checkParentheses(userInput)) {

					root = BinaryTree.stringToTree(userInput, 1, userInput.length()-1);
					outputTextField.setText(String.valueOf(BinaryTree.cntNodes(root)));
					} else {
						throw new Exception();
						}
				} catch (Exception ex) {
					throw new InvalidTreeSyntax();
				}
			}
			
			//Prints inorder of the tree
			else if (userSelection.equals("InOrder")) {
				try {
					if (BinaryTree.checkParentheses(userInput)) {

					root = BinaryTree.stringToTree(userInput, 1, userInput.length()-1);
					StringBuilder traversal = new StringBuilder();
					BinaryTree.inOrderTraversal(root, traversal);
					outputTextField.setText(String.valueOf(traversal));
					} else {
						throw new Exception();
						}
				} catch (Exception ex) {
					throw new InvalidTreeSyntax();
				}
			}
				
		} catch (InvalidTreeSyntax ex) {
		ex.showError();
			} //end invalid tree syntax

	} //End actionPerformed
		
}
