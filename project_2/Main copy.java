/*
 * CMSC350 Project 2: Polynomial Strong and Weak checks
 * Author: Chase Renick
 * Date: Feb 9, 2021
 * Purpose: The Main class which allows users to select a file,
 * reads in lines from text file, creates polynomial objects, places them into an ArrayList,
 * and checks for weak and strong ordering, outputs results
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener {
	
	/**
	 * Creates the initial GUI that will allow users to select a file
	 */
	private static final long serialVersionUID = 1L;
	//Label to show which file a user selects
	private JLabel filename;
	private JButton button1 = new JButton("Open");
	private JFileChooser newFile = new JFileChooser();
	private JTextArea resultsTextfield;

	
	public Main() {
		JLabel entry1;
		
		// Making layout GridBagLayout ()
		setLayout(new GridBagLayout());

		// Instance of the GUI
		GridBagConstraints window = new GridBagConstraints();

		// Setting the grid layout
		window.insets = new Insets(3, 3, 3, 3);

		// Overall label
		entry1 = new JLabel("Select a file to process the polynomials to verify order.");
		entry1.setHorizontalAlignment(JLabel.CENTER);
		window.fill = GridBagConstraints.VERTICAL;
		window.gridx = 1;
		window.gridy = 1;
		window.gridwidth = 3;
		add(entry1, window);
		
		// JButton to allow users to select a file from user directory
		JButton btnFind = new JButton("Select a File to Compare Ordering");
		btnFind.setPreferredSize(new Dimension(300, 50));
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 1;
		window.gridy = 2;
		window.gridwidth = 3;
		add(btnFind, window);
		
		// Textfield next to results
		resultsTextfield = new JTextArea();
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 1;
		window.gridy = 3;
		window.gridwidth = 3;
		resultsTextfield.setEditable(false);
		add(resultsTextfield, window);
		
		//Still need to integrate when user selects button that action listener 
		//pops open the JFile

		btnFind.addActionListener(this);
		
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
		setTitle("Polynomial Order Verifier");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private ArrayList<String> checkOrder(File f) {
		String fileLine;
		ArrayList<String> output = new ArrayList<>();
		ArrayList<Polynomial> filePolynomials = new ArrayList<Polynomial>();
		
		//try block reads in lines from file into Polynomial objects and puts 
		//them into an ArrayList of Polynomials as well as string output
		try(BufferedReader inputStream = new BufferedReader(new FileReader(f))) {
	        while ((fileLine = inputStream.readLine()) != null) {
	        	Polynomial poly = new Polynomial(fileLine);
	        	output.add(poly.toString());
	        	filePolynomials.add(poly);
	        	} // end while
	        } 
		catch (IOException io) 
			{
		        System.out.println("File IO exception" + io.getMessage());
	        } 
		//Adds to output ArrayList and performs ordering check 
			output.add(String.format("Weak Order: %b", OrderedList.checkSorted(filePolynomials)));
			output.add(String.format("Strong Order: %b", OrderedList.checkSorted2(filePolynomials)));
			return output;
	}
	
	//Driving Code
	public static void main(String[] args) {
		
		new Main();
		} // end main      
	
	//Actions to perform once user clicks on button, selects file, and wants to see 
	//results
	@Override
	public void actionPerformed(ActionEvent e) {
		String userSelection = e.getActionCommand();
		if (userSelection.equals("Select a File to Compare Ordering")) {
			newFile.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = newFile.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = newFile.getSelectedFile();
			    String res = String.join("\n", checkOrder(selectedFile));
			    resultsTextfield.setText(res);
			}
		}
	}
} // End Main

