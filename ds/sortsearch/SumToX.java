/*
4-8. [4 ] Given a set of S containing n real numbers, and a real number x. We seek an
algorithm to determine whether two elements of S exist whose sum is exactly x.
*/

package ds.sortsearch;

import ds.list.QuickSort;
import ds.sortsearch.binary.BinarySearch;
import ds.Common;
import java.util.Map;
import java.util.HashMap;

public class SumToX {
	public static void main(String[] args){
		int[] a = {3 ,4, 1, 9 ,10 ,6 ,3 ,1, 20, 8};
		String r = findSum(a, Integer.parseInt(args[0]));
		System.out.println(r);

		String r2 = findSum2(a, Integer.parseInt(args[0]));
		System.out.println(r2);

		// sorted array
		int[] a3 = {3, 5, 6, 8, 9, 12, 13, 15, 16, 18, 20};
		String r3 = findSum3(a3, Integer.parseInt(args[0]));
		System.out.println(r3);
	}

	// this solution uses binary search 
	public static String findSum(int[] a, int x){
		QuickSort.quick_sort(a, 0, a.length -1);
		int delta, binR;
		for(int i = 0; i < a.length - 1; i++){
			delta = x - a[i];
			// we don't want to search from the beginning of the array,
			// since we should have found it earlier
			binR = BinarySearch.search(a, delta, i + 1, a.length - 1);
			if(binR >= 0) {
				return delta +  " " + a[i];
			}
		}
		return "Not Found";
	}

	// assume that the array is sorted, find in O(n)
	public static String findSum3(int[] a, int x){
		// scan the array, 
		int begin = 0, end = a.length -1, sum;
		while(begin < a.length -1 && (end >= 0)){
			sum = a[begin] + a[end];
			if(sum < x){
				begin++;
			}else if(sum > x){
				end--;
			}else {
				return a[begin] + " " + a[end];
			}
		}
		return "Not Found";
	}

	public static String findSum2(int[] a, int x){
		// this map holds the array's element and its delta to x
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
		int delta;
		for(int i = 0; i < a.length; i++) {
			delta = x - a[i];
			if(cache.get(delta) != null) {
				return delta + " " + a[i];
			}else{
				cache.put(a[i], delta);
			}
		}
		return "Not Found";
	}
}