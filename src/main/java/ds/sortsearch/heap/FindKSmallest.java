/*
4-12. [3] Devise an algorithm for finding the k smallest elements of an unsorted set of n
integers in O(n + k log n).
*/
package ds.sortsearch.heap;

import ds.sortsearch.Heap;
import ds.Common;
import java.util.List;
import java.util.ArrayList;

public class FindKSmallest {
	public static void main(String[] args){
		int[] a = {4, 2, 7, 23, 11, 0, 5, 7, 9, 32, 15, 7, 9,7};
		int[] r = findKSmallest(a, Integer.parseInt(args[0]));
		Common.printArray(r);
	}

	public static int[] findKSmallest(int[] a, int k){
		List<Integer> ret = new ArrayList<Integer>();
		Heap heap = new Heap(a);
		for(int i = 0; i < k; i++){
			ret.add (heap.extract_min());
		}
		return Common.toArray(ret);	
	}
}