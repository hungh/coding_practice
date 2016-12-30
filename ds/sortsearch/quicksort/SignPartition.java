/*
0. [3] Give an efficient algorithm to rearrange an array of n keys so that all the
negative keys precede all the nonnegative keys. Your algorithm must be in-place,
meaning you cannot allocate another array to temporarily hold the items. How fast
is your algorithm?
*/

package ds.sortsearch.quicksort;

import ds.Common;
import ds.list.QuickSort;

public class SignPartition {
	public static void main(String[] args){
		int[] a = {2, -4, 0, -12, 8, 44, -90, 43, -5, 12, 0, 7};
		Common.printArray(a);
		partition(a);
		Common.printArray(a);
	}

	public static void partition(int[] a){
		int e = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] < 0) {
				QuickSort.swap(a, e, i);
				e++;
			}
		}
	}
}