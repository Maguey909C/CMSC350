/*
 * CMSC350 Project 2: Polynomial Strong and Weak checks
 * Author: Chase Renick
 * Date: Feb 9, 2021
 * Purpose: 
 */

import java.util.ArrayList;
import java.util.Comparator;

public class OrderedList {
	
	//First checkSorted builds off the second overloaded function
	static boolean checkSorted(ArrayList<Polynomial> aList) {
		return weakCheck(aList, Polynomial::compareTo);
	}
	
	static boolean checkSorted2(ArrayList<Polynomial> aList) {
		return strongCheck(aList, Polynomial::compareTwo);
	}
	
	// Checksorted1 compares one to another to see if previous is greater than current
	static boolean weakCheck(ArrayList<Polynomial> aList, Comparator<Polynomial> compare) {
		var iter = aList.iterator();
		Polynomial curr = null;
		Polynomial prev = iter.next();
		while (iter.hasNext()) {
			curr = iter.next();
			if (compare.compare(prev, curr) > 0) {
				return false;
			} prev = curr;
		}
		return true;
	}
	
	static boolean strongCheck(ArrayList<Polynomial> aList, Comparator<Polynomial> compare) {
		var iter = aList.iterator();
		Polynomial curr = null;
		Polynomial prev = iter.next();
		while (iter.hasNext()) {
			curr = iter.next();
			if (compare.compare(prev, curr) > 0) {
				return false;
			} prev = curr;
		}
		return true;
	}
	
	
}