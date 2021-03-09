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
import java.util.*;


public class GUIMain extends JFrame implements ActionListener {
	
	/**
	 * Creates the initial GUI that will allow users to select a file
	 */
	private static final long serialVersionUID = 1L;
	//Label to show which file a user selects
	private JFileChooser newFile = new JFileChooser();
	private JTextArea outputTextArea;
	

	public GUIMain() {
		JLabel entry1;
		
		// Making layout GridBagLayout ()
		setLayout(new GridBagLayout());

		// Instance of the GUI
		GridBagConstraints window = new GridBagConstraints();

		// Setting the grid layout
		window.insets = new Insets(3, 3, 3, 3);

		// Overall label
		entry1 = new JLabel("Graph Builder with perform DFS once file is selected");
		entry1.setHorizontalAlignment(JLabel.CENTER);
		window.fill = GridBagConstraints.VERTICAL;
		window.gridx = 1;
		window.gridy = 1;
		window.gridwidth = 3;
		add(entry1, window);
		
		// JButton to allow users to select a file from user directory
		JButton btnFind = new JButton("Select File to Build the Graph");
		btnFind.setPreferredSize(new Dimension(300, 50));
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 1;
		window.gridy = 2;
		window.gridwidth = 3;
		add(btnFind, window);
		
		// Textfield next to results
		outputTextArea = new JTextArea();
		window.fill = GridBagConstraints.CENTER;
		window.gridx = 1;
		window.gridy = 3;
		window.gridwidth = 3;
		outputTextArea.setEditable(false);
		add(outputTextArea, window);
		
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
		setTitle("Graph Builder from File");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	//Method takes array list of all vertices and
	//set of vertices from the tree traversal, 
	//then shows vertices were not traversed
	public static String unreachableClasses(ArrayList<String> uniqueClasses, String traversalString) {
		
		ArrayList<String> unreachables = new ArrayList<String>();
		
		String[] splitTraversal = traversalString.replace("(","").replace(")", "").replace("*", "").replaceAll("^ +| +$|( )+", "$1").split(" ");
		Set<String> setTraversal= new HashSet<String>(Arrays.asList(splitTraversal));
		
		uniqueClasses.removeAll(setTraversal);
//		
		for (String ele : uniqueClasses) {
			unreachables.add(ele + " is unreachable");
		}
		
		return unreachables.toString();
	}
	
	//
	public static String fileHandling (File f) {
		String fileLine;
		DirectedGraph<String> dGraph = new DirectedGraph<String>();
		ArrayList<String> uniqueClasses = new ArrayList<>();
		String treeOutput = null;
		String parenthesesOutput = null;
		String unreachables=null;
		
		//Try block reads in the file and then builds the graph
		try(BufferedReader inputStream = new BufferedReader(new FileReader(f))) {
	        while ((fileLine = inputStream.readLine()) != null) {
	        	String[] splitString = fileLine.toString().split(" ");
	        	for (int i = 1; i<splitString.length; i++) {
	        		dGraph.addEdge(splitString[0], splitString[i]);
	        	}
	        	//Adding a unique set of all classes while preserving order
	        	for (int i = 0; i<splitString.length; i++) {
	        		if(!uniqueClasses.contains(splitString[i])) {
	        		uniqueClasses.add(splitString[i]);
	        		}
	        	} // end for loop
	        }
	       
	       //Passing in the map constructed from DirectedGraph to Hierarchy and ParenList
	       Hierarchy<String> hierGraph = new Hierarchy<String>(dGraph.getMap());
	       Hierarchy<String> parenGraph = new ParenthesizedList<String>(dGraph.getMap());
	       
	       //Perform DFS with with two outputs as strings
	       //First: Hierarchical Tree Format
	       //Second: Parenthesized Format
	       treeOutput = hierGraph.processVertex(uniqueClasses).toString();
	       parenthesesOutput = parenGraph.processVertex(uniqueClasses).toString();
	       
	       //Determine unreachable classes by taking set of visited nodes
	       //differenced with unique classes
	       unreachables = unreachableClasses(uniqueClasses, parenthesesOutput);
	       
		} catch (IOException io) 
	        {
		        System.out.println("File IO exception" + io.getMessage());
	        } 
		//Take all these string outputs and send back to GUI with line breaks
		 return treeOutput + "\n" + parenthesesOutput + "\n" + unreachables;
	}
	
	//Driving Code
	public static void main(String[] args) {
		
		new GUIMain();
		} // end main      
	
	//Actions to perform once user clicks on button, selects file, and wants to see 
	//results
	@Override
	public void actionPerformed(ActionEvent e) {
		String userSelection = e.getActionCommand();
		if (userSelection.equals("Select File to Build the Graph")) {
			newFile.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = newFile.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = newFile.getSelectedFile();
			    String res = fileHandling(selectedFile);
			    outputTextArea.setText(res);
			}
		}
	}
} // End Main

