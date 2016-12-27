/* 4.2.c
(c) Let S be an unsorted array of n integers. Give an algorithm that finds the pair
x, y ∈ S that minimizes |x − y|, for x != y. Your algorithm must run in O(n log n)
worst-case time.


*/

package ds.sortsearch;

import ds.list.QuickSort;
import ds.Common;

public class FindMinDiff {
	public static void main(String[] args){
		int[] a = {2, 5, 6, 9, 12, 14};
		int[] p = findMinPair(a);
		System.out.println(p[0] + " " + p[1]);
	}


	public static int[] findMinPair(int[] a){
		if(a == null || a.length < 2) return null;

		QuickSort.quick_sort(a, 0, a.length - 1);
		Common.printArray(a);

		int min = Integer.MAX_VALUE, l = a[0], h = a[1];
		int temp;
		int[] ret = new int[2];

		for(int i = 0; i < a.length - 1; i++){
			temp = Math.abs(a[i+ 1] - a[i]);
			if(temp < min) {	
				min = temp;
				l = a[i];
				h = a[i+1];
			}
		}

		ret[0] = l; ret[1] = h;
		return ret;
	}
}