/* (Skiena)
4-10. [5] Given a set S of n integers and an integer T, give an O( (n^(k−1)) * log n) algorithm
to test whether k of the integers in S add up to T.
*/
package ds.sortsearch;

import ds.list.QuickSort;
import ds.sortsearch.SumToX;
import ds.sortsearch.binary.BinarySearch;
import ds.Common;

public class ElementsAddedToSum {
	public static void main(String[] args){
		int[] a = {4, 3, 1, 2, 6, 5, 7};
		QuickSort.quick_sort(a, 0, a.length - 1);
		boolean r = findElements(a, Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println(r);
	}

	public static boolean findElements(int[] a, int k, int t){
		if(k < 2) throw new IllegalArgumentException("k must be at least 2");
		// base case k = 2
		if(k == 2){
			return SumToX.findSum3Bool(a, t);
		}
		// k >= 3
		int[] arest = new int[a.length -1];
		for(int i = 0; i < a.length; i++){
			// init arest with everything except a[i]
			for(int j = 0, f = 0; j < a.length; j++){
				if(j != i)	arest[f++] = a[j];
			}

			if( findElements(a, k -1, t - a[i]) ) return true;
		}
		return false;
	}

}



