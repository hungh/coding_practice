/*
(Skiena- Algo Design and Manual)
4-34. [5] Suppose that you are given a sorted sequence of distinct integers {a1, a2, . . . , an}, 
drawn from 1 to m where n < m. Give an O(lg n) algorithm to find an integer ≤ m that 
is not present in a. For full credit, find the smallest such integer.
*/

package ds.sortsearch.others;

import ds.Common;

public class Problem4D34 {
	public static void main(String[] args){
		// int[] a = {1, 3, 8, 9, 12}; // returns 2
		// int[] a = {1, 2, 4, 8, 22, 100}; // -> 3
		// int[] a = {1, 2, 3, 4, 6, 7, 8, 9, 12}; // -> 5
		int[] a = {1,  2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 19, 22, 25, 26, 27, 28, 30, 33, 36, 37, 44, 45, 88};
		// int[] a = {1, 2, 3, 5, 100};
		// int[] a = {1, 2, 99}; // -> 3	
		int r = findSmallestM(a, 101);
		Common.log("Result:" + r);

	}


	public static int findSmallestM(int[] a, int m){

		if( a == null ) return Integer.MIN_VALUE; // NOT FOUND

		int begin = 0, middle, end = a.length - 1;
		int endValue = -1;
		int loop = 0;

		if(a[begin] > 1) return 1;
		if(a[end] < m) { endValue = a[end] + 1; };

		while(begin < end){			
			middle = (begin + end)/2; 

			if(middle + 1 == a[middle]){
				begin = middle + 1;

			}else if (middle + 1 < a[middle]){
				end = middle;
			} 			
			loop++;
		}
		// Common.log("begin=" + begin + ";end=" + end);
		Common.log("#loop=" + loop + ";arr.length=" + a.length);

		if(begin > 0 && begin < a.length) {
			return a[begin - 1] + 1;
		}
		if(endValue > 0) return endValue;
		return Integer.MIN_VALUE; // NOT FOUND
	}
}