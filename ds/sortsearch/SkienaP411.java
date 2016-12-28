/* (Skiena)
4-11. [6 ] Design an O(n) algorithm that, given a list of n elements, finds all the elements
that appear more than n/2 times in the list. Then, design an O(n) algorithm that,
given a list of n elements, finds all the elements that appear more than n/4 times.
*/
package ds.sortsearch;

import ds.sortsearch.LinearSelection;
import ds.Common;

public class SkienaP411 {
	public static void main(String[] args){
		int[] a = {1, 2, 4, 4, 3, 4, 5, 4, 4};
		int e = findRepeatedElememnts(a);
		Common.log(e);

		int[] a_0 = {1, 2, 6 ,4, 3, 4, 5, 4, 4};
		int e2 = findRepeatedElememnts(a_0);
		Common.log(e2);		
	}

	/*
	If the element appears more than n/2 times in the array, 
	it has to be somewhere in the middle of the array. This leads to finding the 
	n/2 smallest element of the array and find out how many times it is repeated in the array 
	By using LinearSelection (which is O(n) in time) and scanning the array to find duplicates also take O(n)
	*/
	public static int findRepeatedElememnts (int[] a){
		int median_rank = (int)Math.ceil((a.length/2));
		int median_index = LinearSelection.select(a, median_rank, 0, a.length -1);
		int median_value = a[median_index];
		int t = 0;

		for(int i = 0; i < a.length; i++){
			if(median_value == a[i]) t++;
		}

		if( t > a.length/2) 
			return median_value;
		else
			return Integer.MIN_VALUE;// not found
	}
}
