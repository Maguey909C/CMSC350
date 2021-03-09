/*
 * CMSC350 Project 4: Graphs and DFS
 * Author: Chase Renick
 * Date: March 4, 2021
 * Purpose: The DirectedGraph class houses the methods to build the graph
 * add the vertices, edges, and other functions related to examining the graph.
 */

import java.util.*; 
  

class DirectedGraph<T> { 
	
    // Hashmap to store generic type Key and list of values
    private Map<T, List<T> > map = new HashMap<>(); 
  
    // to add a new vertex input the source and construct new linkedlist 
    public void addVertex(T s) 
    { 
        map.put(s, new LinkedList<T>()); 
    } 
  
    // Building an edge between key and value
    public void addEdge(T key, 
                        T value)
    { 
  
        if (!map.containsKey(key)) 
            addVertex(key); 
  
        if (!map.containsKey(value)) 
            addVertex(value); 
        
        //Get the key and then add the value (destination
        map.get(key).add(value); 
    } 
    
    // This function gives the count of vertices 
    public int getVertexCount() 
    { //Take the size of the keyset, i.e. dictionary
      
      return map.keySet().size(); 
    } 
  
    // This function gives the count of edges 
    public int getEdgesCount() 
    { 
        int count = 0; 
        for (T v : map.keySet()) { 
            count += map.get(v).size(); 
        } 
        return count;
    } 
  
    // Vertex present? Yes or no 
    public boolean hasVertex(T s) 
    { 
        if (map.containsKey(s)) { 
        	return true; 
        } 
        else { 
            return false;
        } 
    } 
  
    // This function gives whether an edge is present or not. 
    public boolean hasEdge(T s, T d) 
    { 
        if (map.get(s).contains(d)) { 
            return true;
        } 
        else { 
            return false;
        } 
    } 
    
    public Map<T, List<T>> getMap() {
    	return map;
    }
    
} // end DirectedGraph
        