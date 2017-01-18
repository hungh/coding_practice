/*
4-24. [5] Let A[1..n] be an array such that the first n − √n elements are already sorted
(though we know nothing about the remaining elements). Give an algorithm that
sorts A in substantially better than n log n steps
*/
package ds.sortsearch.others;

import ds.Common;
import ds.list.QuickSort;


public class SortRemaining {
	public static void main(String[] args){
		int[] a = {3, 6, 8, 12, 15, 19, 22, 34, 65, 87, 99, 100, 7, 4, 30, 112};
		Common.printArray(a);
		int[] r =remaingingSort(a, 11);
		Common.printArray(r);
	}

	// end: the last index of the sorted part
	// the operation takes O(2n) = O(sqrt(n) * log (sqrt(n)) + n))
	public static int[] remaingingSort(int[] a, int end){
		// create a new returning arrays
		int[] r = new int[a.length];
		int i = 0, j = end + 1, k = 0;

		// sort the sqrt(n) unsorted array, take O( sqrt(n) * log (sqrt(n)) )
		QuickSort.quick_sort(a, end + 1, a.length - 1);
		// then merge the 2 parts
		while((i <= end) && (j < a.length)){
			if(a[i] < a[j]){
				r[k++] = a[i++];
			}else{
				r[k++] = a[j++];
			}
		}
		// the rest
		while(i <= end){
			r[k++] = a[i++];
		}
		while(j < a.length){
			r[k++] = a[j++];
		}

		return r;
	}

}
