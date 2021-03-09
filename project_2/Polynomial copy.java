
/*
 * CMSC350 Project 2: Polynomial Strong and Weak checks
 * Author: Chase Renick
 * Date: Feb 9, 2021
 * Purpose: Polynomial class takes in txt file input as a string,
 * examines input for special characters || letters throws errors,
 * methods related to making it string, comparing, indexing found here.
 */

import java.util.Iterator; 

class List<T> implements Iterable<T> { 
    Node<T> head, tail; 
    private int size;
      
    // add new Element at tail of the linked list in O(1) 
    public void add(T data) 
    { 
        Node<T> node = new Node<>(data, null); 
        if (head == null) 
            tail = head = node; 
        else { 
            tail.setNext(node); 
            tail = node; 
        }
        size ++;
    } 
      
    // return Head 
    public Node<T> getHead() 
    { 
        return head; 
    } 
      
    // return Tail 
    public Node<T> getTail() 
    { 
    	return tail;
    } 
    
    public int getSize()
    {
    	return size;
    }
    
    // return data at a given index
    public T get(int index) {
    	int i = 0;
    	var iter = iterator();
    	T data = null;
    	while (iter.hasNext()) {
    		data = iter.next();
    		if (i == index) {
    			return data;
    		}
    		i++;
    	}
    	return null;
    }
    // return Iterator instance 
    public Iterator<T> iterator() 
    { 
        return new ListIterator<T>(this); 
    } 
} 
  
class ListIterator<T> implements Iterator<T> { 
    Node<T> current; 
      
    // initialize pointer to head of the list for iteration 
    public ListIterator(List<T> list) 
    { 
        current = list.getHead(); 
    } 
      
    // returns false if next element does not exist 
    public boolean hasNext() 
    { 
        return current != null; 
    } 
      
    // return current data and update pointer 
    public T next() 
    { 
        T data = current.getData(); 
        current = current.getNext(); 
        return data; 
    } 
} 
  
// Constituent Node of Linked List 
class Node<T> { 
    T data; 
    Node<T> next; 
    public Node(T data, Node<T> next) 
    { 
        this.data = data; 
        this.next = next; 
    } 
      
    // Setter getter methods for Data and Next Pointer 
    public void setData(T data) 
    { 
        this.data = data; 
    } 
      
    public void setNext(Node<T> next) 
    { 
        this.next = next; 
    } 
      
    public T getData() 
    { 
        return data; 
    } 
      
    public Node<T> getNext() 
    { 
        return next; 
    }

	public boolean hasNext() {
		return next != null; 
	} 
} 

public class Polynomial implements Iterable<Double>, Comparable<Polynomial> {
	
	//Instance Variables
	public String userInput;
	public String [] numbers;
	private List<Double> values = new List<>();

	//Constructor that throws errors if special character or non descending order
	public Polynomial(String userInput) {
		this.userInput = userInput;
		this.numbers = userInput.split(" ");
		
		try { 
			for (int i = 0; i < numbers.length; i++) {
			values.add(Double.parseDouble(this.numbers[i]));
			} 
		} catch (Exception e) {
			throw new InvalidPolynomialSyntax("Special characters or letters are not permitted inside polynomial.");
		}
		if (!checkDescending()) {
			throw new InvalidPolynomialSyntax("Polynomial exponents are not in descending order.");
		}
	}
	
	//Examines array holding exponent values and selects highest one
	public int getHighestExponent() {
		return values.get(1).intValue();
	}
	
	//Checks to see if polynomial has exponents in descending order
	boolean checkDescending() {
		int exponents [] = new int [numbers.length/2];
		
		for (int i=1; i<numbers.length; i+=2) {
			exponents[(i)/2] = values.get(i).intValue();
		}
		for (int x=0; x<exponents.length-1; x++) {
			if (exponents[x+1]>exponents[x])
				return false;
			}
		return true;
	}// end checkDescending
	
	//Takes an exponent and determines what string should be associated with it for polynomial
	public String selectPower(int exponent) {
		if (exponent == 0) {
			return "";
		} else if (exponent == 1) {
			return "x";
		} else {
			return "x^" + exponent; 
		}
	} // end selectPower
	
	//Takes a polynomial and then turns it to a string with associated values
	@Override //this is a method we are overloading from the above class
	public String toString() {
		String [] terms = new String [this.numbers.length/2];
		for (int i=0; i <numbers.length; i+=2) {
			int index = i/2;
			double coefficient = values.get(i);
			int exponent = values.get(i+1).intValue();
			
			terms[index] = coefficient + selectPower(exponent); 
		}
		return String.join(" + ", terms);
	}

	//Iterating over the exponent values
	@Override
	public Iterator<Double> iterator() {
		return values.iterator();
	}

	//Comparing two polynomials for weak and strong ordering (incomplete)
	@Override
	public int compareTo(Polynomial o) {

		//Weak Order Test
		for (int i = 1; i<Math.min(values.getSize(), o.values.getSize()); i+=2) {
			int a = values.get(i).intValue();
			int b = o.values.get(i).intValue();
			
			if (a>b) {
				return 1;
			} else if (b>a) {
				return -1;
			} else {
				continue;
			}
			
		} return 0;
	}
	
	public int compareTwo(Polynomial o) {
			
//		//Strong Order Test
		for (int i = 1; i<Math.min(values.getSize(), o.values.getSize()); i += i % 2 == 0 ? 3 : -1) {
			double c = values.get(i);
			double d = o.values.get(i);
			
			if (c>d) {
				return 1;
			} else if (d>c) {
				return -1;
			} else {
				continue;
			}
			
		} return 0;
			
	}
}
	
