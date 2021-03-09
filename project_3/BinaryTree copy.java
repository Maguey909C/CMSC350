/*
 * File: CMIS350PRJ1RenickC.java
 * Author: Chase Renick
 * Date: Feb 20, 2021
 * Purpose: The BinatryTree class constructs a binary tree from
 * a string, and then performs various methods on the tree.
 */

import java.util.*;

public class BinaryTree {
	
//	private JTextField expressionField;
//	private JTextField resultsTextfield;
	
	char value;
	BinaryTree left, right;
	
	//Really should be called the node class
	//but homework specified that it was BinaryTree class
	public BinaryTree (char value)
	{
		this.value = value;
		left = right = null;
	}
	
	static class BTree 
	{
		// Root of Binary Tree
		BinaryTree root;
		
		// Constructors 
		BTree(char value) {
			root = new BinaryTree(value);
		}
		
		BTree() {
			root = null;
		}
	}
	
	static int findIndex(String s, int initialIndex, int terminalIndex) {
			
			//base case
			if (initialIndex > terminalIndex) {
				return -1;
			}
			
			Stack<Character> theStack = new Stack<>();
			
			//searching for characters "(" and ")" and their respective indices to stack
			for (int i = initialIndex; i <= terminalIndex; i++) {
				if (s.charAt(i) == '(') {
					theStack.push(s.charAt(i));
				} else if (s.charAt(i)==')') {
					if (theStack.peek() == '(') {
						theStack.pop();
						if (theStack.empty()) {
							return i;
						}
					}
				}
			} // end for loop
			// if not found return -1
			return -1;
		}

	static boolean checkParentheses(String s) {
		  int leftParen = 0;
		  int rightParen = 0;
		  for (int i = 0; i < s.length(); i++) {
		    if (s.charAt(i) == '(') {
		      leftParen++;
		    } else if (s.charAt(i) == ')') {
		      rightParen++;
		    } else {
		      continue;
		    }
		  } // end for loop
		  if (leftParen == rightParen && s.charAt(0)=='(' && s.charAt(s.length()-1)==')') {
		    return true;
		  } else {
		    return false;
		    }
		}
	
	static BinaryTree stringToTree(String s, int initialIndex, int terminalIndex) {
		// Base case 
	    if (initialIndex > terminalIndex) 
	        return null; 
	    
	    BTree tree = new BTree();
	  
	    // making a new root
	    tree.root = new BinaryTree(s.charAt(initialIndex));
	    int index = -1; 
	  
	    // looking for the next close bracket based on open bracket 
	    if (initialIndex + 1 <= terminalIndex && s.charAt(initialIndex+ 1) == '(') 
	        index = findIndex(s, initialIndex + 1, terminalIndex); 
	  
	    // if index found of bracket
	    if (index != -1) { 
	  
	        // call the left subtree 
	        tree.root.left = stringToTree(s, initialIndex + 2, index - 1); 
	  
	        // call the right subtree 
	        tree.root.right = stringToTree(s, index + 2, terminalIndex - 1); 
	    } 
	    return tree.root; 
	}
	
	static void showTree(BinaryTree n) {
		//Function is just to help visualize cases once tree is initialized
		//Base case
		if (n==null) {
			return;
		}
		System.out.println(n.value);
		showTree(n.left);
		showTree(n.right);
	}
	
	static int cntNodes(BinaryTree n) {
		//Base case
		if (n==null) {
			return 0;
		}
		//Recursively adding 1 each time it steps through each nodes and then
		//sums them up all at the end
		return 1 + cntNodes(n.left) + cntNodes(n.right);
	}
	
	static boolean treeFull(BinaryTree n) {
		double height = treeHeight(n);
		double totalNodes = cntNodes(n);
		
		//The relationship between height and nodes is 2^(n)-1
		double maxHeight = (Math.pow(2.0,height))-1;
		//If the maxHeight is equal to the total nodes then it is
		//a full tree otherwise no
		if (maxHeight == totalNodes) {
			return true;
		} return false;
	}
	
	static boolean treeProper(BinaryTree n) {
		
		//Base case if node to left and right are empty return true
		if (n.left==null && n.right==null) {
			return true;
		}
		
		//All other cases when children are not null then return left and right nodes
		if ((n.left!=null) && (n.right!=null)) {
			return (treeProper(n.left) && treeProper(n.right));
		}
		//otherwise it is not a proper tree
		return false;
	}
	
	static int treeHeight(BinaryTree n) {
		
		//base case
		if (n==null) {
			return 0;
		}
		//Other cases
		int leftSide = 1+treeHeight(n.left);
		int rightSide = 1 + treeHeight(n.right);
		if (leftSide > rightSide) {
			return leftSide;
		} else if (rightSide > leftSide) {
			return rightSide;
		} else {
			return leftSide;
		}
	}
	
	static boolean treeBalanced(BinaryTree n) {
		//base case
		if (n==null) {
			return true;
		}
		//Other cases
		int leftSide = 1+treeHeight(n.left);
		int rightSide = 1 + treeHeight(n.right);

		if (Math.abs(rightSide - leftSide)<=1) {
			return true;
		} else {
			return false;
		}
	}
	
	static void inOrderTraversal(BinaryTree n, StringBuilder sb) {
		//base case
		if (n == null) {
			return;
		}
		sb.append("(");
		inOrderTraversal(n.left, sb);
		sb.append(n.value);
		inOrderTraversal(n.right, sb);
		sb.append(")");
	}
	
} //End BinaryTree class


