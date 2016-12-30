/*
sort an array with log(n) distinct numbers in n * (log logn)
*/
package ds.sortsearch.others;

import ds.list.QuickSort;
import ds.Common;
import java.util.Map;
import java.util.HashMap;

public class KDistinctSort {
	public static void main(String[] args){
		int[] a = {3, 5, 6, 1, 3, 5, 6, 1, 4, 4, 3, 1, 5, 3, 1, 6, 5};
		kdSort(a);
		Common.printArray(a);
	}

	public static void kdSort(int[] a){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// the map will have k items,
		// if this is a balanced binary search tree, if insert will take logk time
		// after the for loop it will be O(n*logk)
		Integer count;
		// O(n) time and space
		for(int i = 0; i < a.length; i++){
			count = map.get(a[i]);
			if(count == null){
				map.put(a[i], 1);
			}else{
				map.put(a[i], map.get(a[i]) + 1);
			}
		}
		// search the map keys take O(logk)
		// skip this step if it is a balanced binary search tree
		// since we have do an in-order traversal of the tree to obtain
		// a sorted order of data
		int[] keys = Common.toArray(map.keySet());
		QuickSort.quick_sort(keys, 0, keys.length - 1); // take O(logk)
		int k = 0;
		// for map O(n)
		// for balanced bin tree : O(k) by in-order traversal
		for(int e: keys){
			count = map.get(e);
			for(int j = 0; j < count; j++, k++) a[k] = e;
		}

	}
}