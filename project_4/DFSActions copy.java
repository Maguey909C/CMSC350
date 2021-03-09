/*
 * CMSC350 Project 4: Graphs and DFS
 * Author: Chase Renick
 * Date: March 4, 2021
 * Purpose: The interface which has three methods related
 * to the tree traversal that will show up in Hierarchy 
 * and Parenthesized list class.
 */

import java.util.*;

public interface DFSActions<K, V> {
	
	
	//Starts the traversal process
	public StringBuilder processVertex (ArrayList<String> uniqueClasses);
	
	//Performs the tree traversal through recursion
	public void descendAscend(Object root, Map<K, V> visited, StringBuilder sb, String prefix, String subPrefix);
	
	//Signals if two classes have a cyclical dependency
	public boolean cycleDetected(Map<K, V> visited, String newKey, String key, Integer i);



}
