/*
 * CMSC350 Project 4: Graphs and DFS
 * Author: Chase Renick
 * Date: March 4, 2021
 * Purpose: Parenthesized list extends Hierarchy class and
 * by extension inherits the DFSActions through it (since
 * ParenthesizedList is a subclass of Hierarchy. I could have 
 * put implements DFS actions, but it seemed redundant so I chose
 * to extend the class. Main difference is that this builds string
 * with parentheses. Everything else the same as Hierarchy.
 */

import java.util.*;

public class ParenthesizedList<T> extends Hierarchy<T> {
		    
	 public ParenthesizedList(Map<T, List<T>> map)
	 {
	    super(map); 
	 };
	
	//Building out the tree with parentheses as opposed to newline breaks
	//in Hierarchy.  Parentheses are not correct
	@Override
	public void descendAscend(Object root, Map visited, StringBuilder sb, String prefix, String subPrefix) {
		visited.put(root,1);
	      
	    
	    sb.append(prefix);
	    sb.append(root);
	
	      //Iterating over the adjacent vertices 
	    Iterator<T> it = super.map.get(root).iterator();
	    while (it.hasNext()) {
	        String newKey = it.next().toString();
	        boolean hasCycle = cycleDetected(visited, newKey, root.toString(), 1);
	        if (!hasCycle)
	        	descendAscend(newKey, visited, sb, subPrefix + " ( ", subPrefix + "  " );
	        else {
	        	sb.append("*");
	        }
	        sb.append(" ) ");
	    }
	} //end descendAscend

}
