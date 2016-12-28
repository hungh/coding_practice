package ds.sortsearch;

/*
Find an algorithm to find the ith smallest element in a list 
Time complexity : O(n)
*/

import ds.list.QuickSort; // we are going to use partition in quick sort: O(n)
import ds.Common;

public class LinearSelection {
	public static void main(String[] args){
		int[] a = {5, 14, 21, 10, 33, 63, 17};
		int k = Integer.parseInt(args[0]);
		if(k > a.length) throw new IllegalArgumentException("k is too big");
		int r = findNSmallest(a, k, 0, a.length - 1);
		Common.log(r);

		int r2_index = select(a, k, 0, a.length -1);
		Common.log(a[r2_index]);

	}

	// this one O(nlogn) in average case but O(n^2) in worst case.
	// see another solution below: median of medians
	public static int findNSmallest(int[] a, int k, int begin, int end){	
		
		int pivot_index = QuickSort.partition(a, begin, end);
		int l = pivot_index - begin;
		if(l == k - 1){
			return a[pivot_index];
		}else if(l > k - 1){
			return findNSmallest(a, k, begin, pivot_index - 1);
		}else{
			// we subtract 1 (k-l-1) because we move 1 at pivot index (pivot_index + 1)
			return findNSmallest(a, k - l - 1, pivot_index + 1, end);
		}
		
	}
	// median of medians - O(n) time complexity
	public static int select(int[] a, int k, int begin, int end){
		int pivot_index;
		while(true){
			if(begin == end) return begin;
			pivot_index = pivot(a, begin, end);
			pivot_index = QuickSort.partition(a, begin, end, pivot_index);
			if(pivot_index == k){
				return pivot_index;
			} else if (pivot_index > k) {
				end = pivot_index - 1;
			} else {
				begin = pivot_index + 1;
			}
		}
	}

	public static int pivot (int[] a, int begin, int end){
		if(end - begin <  5) {
			return partition5(a, begin, end);
		}
		int subEnd, eachMedian5;
		for(int i = begin; i < end; i+= 5){
			subEnd = i + 4;
			if(subEnd > end) subEnd = end;

			eachMedian5 = partition5(a, i, subEnd);
			// begin, m1, m2, m3....
			QuickSort.swap(a, eachMedian5, begin  + (i - begin)/5);
		}
		// take median of all mi(s)
		// the length of mi(s) = ceil ((end -begin)/5 ) - 1
		// the middle of mi(s) = ((end - begin)/5)/2
		return  select(a, begin, begin + (int)Math.ceil((end - begin)/5) - 1, (end - begin)/10); 
	}

	// partition the array of 5
	public static int partition5(int[] a, int begin, int end){
		if(end - begin > 5) throw new IllegalArgumentException("Invalid range. Must be <= 5");
		if(end - begin == 1) {
			if(a[begin] > a[end]){
				QuickSort.swap(a, begin, end);
			}
			return begin; // size 2
		}
		QuickSort.quick_sort(a, begin, end);
		return (begin + end)/2;
	}
}