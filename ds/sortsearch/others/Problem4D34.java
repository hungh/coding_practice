/*
(Skiena- Algo Design and Manual)
4-34. [5] Suppose that you are given a sorted sequence of distinct integers {a1, a2, . . . , an}, 
drawn from 1 to m where n < m. Give an O(lg n) algorithm to find an integer ≤ m that 
is not present in a. For full credit, find the smallest such integer.
*/

package ds.sortsearch.others;

public class Problem4D34 {
	public static void main(String[] args){
		// n = 5, m = 12
		int[] a = {1, 3, 8, 9, 12}; // returns 2
		// int[] a = {1, 2, 3, 4, 6, 7, 8, 9, 12};
		// will return 5

	}


	public static int findSmallestM(int[] a, int m){
		int begin = 0, middle, end = a.length - 1;
		while(begin <= end){
			middle = (begin + end)/2;
			
		}
	}
}