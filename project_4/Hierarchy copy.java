/*
 * CMSC350 Project 4: Graphs and DFS
 * Author: Chase Renick
 * Date: March 4, 2021
 * Purpose: The Hierarchy implements the DFS actions 
 * and has builds a string based on tree traversal.
 * Hierarchy takes a map to perform methods in DFSActions
 */

import java.util.*;

public class Hierarchy<T> implements DFSActions {
	
//	// Hashmap to store generic type Key and list of values
    protected Map<T, List<T> > map;
    
    public Hierarchy(Map<T, List<T>> map)
    {
    	this.map= map; 
    };
    
    //Invokes the tree traversal function which outputs a stringbuilder
    //so that all vertices can be displayed as a return to the GUI
	@Override
	public StringBuilder processVertex(ArrayList uniqueClasses) {
		StringBuilder sb = new StringBuilder("");
        String prefix = "";
        String subPrefix = "";
        Map<String, Integer> visited = new HashMap<String, Integer>(); 
        descendAscend(uniqueClasses.get(0), visited, sb, prefix, subPrefix);
        return sb;
	}
	
	//Performs the actual traversal of the graph by looking for dependencies
	@Override
	public void descendAscend(Object root, Map visited, StringBuilder sb, String prefix, String subPrefix) {
		visited.put(root,1);
	      
	      //Building out the tree with parentheses
	    sb.append(prefix);
	    sb.append(root);
	    sb.append('\n');
	      //Iterating over the adjacent vertices 
	    Iterator<T> it = map.get(root).iterator();
	    while (it.hasNext()) {
	        String newKey = it.next().toString();
	        boolean hasCycle = cycleDetected(visited, newKey, root.toString(), 1);
	        if (!hasCycle)
	        	descendAscend(newKey, visited, sb, subPrefix + "    ", subPrefix + "          " );
	        else {
	        	sb.append("*");
	        }
	    }
	} //end descendAscend
	
	//If two classes have a dependency then this will signal by boolean
	@Override
	public boolean cycleDetected(Map visited, String newKey, String key, Integer i) {
		return (visited.containsKey(newKey) && 
        		visited.get(newKey)==i &&
        		map.get(newKey).contains(key));
	}
	
}
