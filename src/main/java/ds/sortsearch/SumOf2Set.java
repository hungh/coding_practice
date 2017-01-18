/* (Skiena)
4-6. [3] Given two sets S1 and S2 (each of size n), and a number x, describe an O(n log n)
algorithm for finding whether there exists a pair of elements, one from S1 and one
from S2, that add up to x. (For partial credit, give a Θ(n2) algorithm for this
problem.)
*/

package ds.sortsearch;

import ds.sortsearch.binary.BinarySearch;
import ds.list.QuickSort;
import ds.Common;

public class SumOf2Set {
	public static void main(String[] args){
		int[] a = {3, 5, 1, 2, 3, 8, 6, 4, 10};
		int[] b = {5, 3, 1, 8, 5, 3, 2,1, 12, 14, 32, 6};
		boolean r = isSumAvailable(a, b, 18);
		System.out.println(r);

		Common.log(isSumAvailable2(a, b, 18));
	}

	public static boolean isSumAvailable(int[] a, int[] b, int x){
		QuickSort.quick_sort(b, 0, b.length - 1);
		Common.printArray(b);
		int j;
		for(int i = 0; i < a.length; i++){
			if((j = BinarySearch.search(b, (x - a[i]), 0, b.length - 1)) >= 0) {
				Common.log("Found " + a[i] + " " + b[j]);
				return true;
			}
		}
		return false;
	}

	public static boolean isSumAvailable2(int[] a, int[] b, int x){
		QuickSort.quick_sort(a, 0, a.length - 1);
		QuickSort.quick_sort(b, 0, b.length - 1);
		int s = 0, e = b.length - 1;
		while(s < a.length && e >= 0){
			if(a[s] + b[e] > x) e--; 
			else if(a[s] + b[e] < x) s++;
			else {
				Common.log("Found " + a[s] + " " + b[e]);
				return true;
			}
		}
		return false;
	}
}